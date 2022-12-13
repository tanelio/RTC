import akka.actor.{Actor, ActorSystem, Props}
import akka.event.Logging
import akka.actor.ReceiveTimeout

import scala.concurrent.duration.{Duration, DurationInt}
import scala.language.postfixOps

import RTC.RTC

package rsyslog {

  import scala.collection.mutable
  import java.net.Inet4Address

  class Sensor extends Actor {
    val log = Logging(RTC.system, this)
    var InitialTimeout = 60
    context.setReceiveTimeout(InitialTimeout seconds)
    val OffendingIP = new mutable.HashMap[Inet4Address, (Inet4Address, String)]()
    def receive = {
      case "text" => log.info("received text")
      case ReceiveTimeout => log.info("Timeout")
        context.setReceiveTimeout(Duration.Undefined) // Turn off the receiveTimeout...
      case _ => log.info("received unknown message")
    }
  }
}