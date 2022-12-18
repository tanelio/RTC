import akka.actor.{Actor, ActorSystem, Props}
import akka.event.Logging
import akka.actor.ReceiveTimeout

import scala.concurrent.duration.{Duration, DurationInt}
import scala.language.postfixOps

import RTC.RTC._

package rsyslog {

  import scala.collection.mutable
  import java.net.Inet4Address
  import akka.actor.{Actor, ActorRef, Props, Terminated}
  import akka.util.ByteString
//  import com.google.common.net.InetAddresses.{coerceToInteger, forString}

  import java.text.SimpleDateFormat

  object Offenses {
    // todo add offending IPs to list
    // todo when an IP offends too many times, block based on behavior
    val OffendingIP = new mutable.HashMap[Inet4Address, (Inet4Address, String)]()
  }

  case class Line(l: String, dt: Long, host: String, off: Int)
  case class Prune()
  class Sensor extends Actor {
    import akka.routing.{ActorRefRoutee, BroadcastRoutingLogic, Router}
//    import action.Actions._
    var router = {
      val routees = Vector.empty[ActorRefRoutee]
      Router(BroadcastRoutingLogic(), routees)
    }

    //    Actions.init
    val log = Logging(system, this)
    private val ISO_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
    private val OLD_SYSLOG_DATE_FORMAT = new SimpleDateFormat("MMM dd HH:mm:ss")
    val rulerref = system.actorOf(Props[Sensor])
    var InitialTimeout = 60
    context.setReceiveTimeout(InitialTimeout seconds)
    def receive = {
      // Syslog format: https://en.wikipedia.org/wiki/Syslog
      // Syslog RFC: https://tools.ietf.org/html/rfc5424
      // SDF: https://docs.oracle.com/javase/6/docs/api/java/text/SimpleDateFormat.html
      case x: ByteString => // Let's process as much as makes sense for all rules
        val str = x.utf8String.dropWhile(_ != '>').drop(1) // Take out PRI <xxx>
        println(s"Line = $str")
        val dt = OLD_SYSLOG_DATE_FORMAT.parse(str).getTime // 15+1 characters for date
        //          val now = System.currentTimeMillis()    // alternative: take current timestampt
        val host = str.drop(16).takeWhile(!_.isSpaceChar)
        router.route(Line(str, dt, host, 16 + host.length + 1), sender())
      case Terminated(a) =>
      // Todo: re-instate rule actor?
      case Prune =>
        router.route(Prune, rulerref)

        /*
      case "text" => log.info("received text")
      case ReceiveTimeout => log.info("Timeout")
        context.setReceiveTimeout(Duration.Undefined) // Turn off the receiveTimeout...
      case _ => log.info("received unknown message")
         */
    }
  }
}