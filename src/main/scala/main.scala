import scala.language.postfixOps
import akka.actor.{Actor, ActorSystem, Props}
import akka.event.Logging
import akka.actor.ReceiveTimeout

import scala.collection.mutable
import scala.concurrent.duration._

import java.net._

import rsyslog.Sensor
import Data.DB
//val localhost: InetAddress = InetAddress.getLocalHost
//val localIpAddress: String = localhost.getHostAddress

// Sensor types: rsyslog, telnet, dhcpclient,
// Change into actor class w/timeout

//val OffendingIP = new mutable.HashMap[Inet4Address, (Inet4Address, String)]()
// Enumerate offenses (Set?), Add Timestamp

// Need Sensors in two levels, i.e.  top level Sensor for rsyslog; then second level for log-in attempt with origin & credentials...
// Use Marker to verify you're receiving rsyslog

package RTC {

  class LoadSensors(refresh: false) {
    if (refresh) {

    }
  }

  object RTC {
    val system = ActorSystem("MySystem")
    val Sensor = system.actorOf(Props[Sensor], name = "Sensor")

    // todo add default set of rules
      if (args.size == 0)
        println("Hello, you")
      else
        println("Hello, " + args(0))
      val Sense = new LoadSensors(false)
    }

  }

}