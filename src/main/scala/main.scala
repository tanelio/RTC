import scala.language.postfixOps
import akka.actor.{Actor, ActorSystem, Props}
import akka.event.Logging
import akka.actor.ReceiveTimeout

import scala.collection.mutable
import scala.concurrent.duration._

import java.net._

import rsyslog._
import Data.DB
//val localhost: InetAddress = InetAddress.getLocalHost
//val localIpAddress: String = localhost.getHostAddress

// Sensor types: rsyslog, telnet, dhcpclient,
// Change into actor class w/timeout

//val OffendingIP = new mutable.HashMap[Inet4Address, (Inet4Address, String)]()
// Enumerate offenses (Set?), Add Timestamp

// Need Sensors in two levels, i.e.  top level Sensor for rsyslog; then second level for log-in attempt with origin & credentials...
// Use Marker to verify you're receiving rsyslog

// todo Add Camel

package RTC {

  import slick.lifted.TableQuery

  import scala.sys.process.stringSeqToProcess

  class LoadSensors(refresh: false) {
    if (refresh) {

    }
  }

  object RTC extends App {

    import Data.DB._
    import slick.jdbc.meta.MTable

    val system = ActorSystem("MySystem")
    val Sensor = system.actorOf(Props[Sensor], name = "Sensor")



    // todo add default set of rules
    if (args.length == 0)
      println("Hello, you")
    else
      println("Hello, " + args(0))

    println(s"creating tab;es/schema")
    //    val system = ActorSystem("scanner")

    private val sudoprog = "/usr/bin/sudo"
    private val whichprog = "/usr/bin/which"
    private val nmapprog = findprog("/usr/bin/nmap")
    private val tracerouteprog = findprog("/usr/sbin/traceroute")
    private val whoisprog = findprog("/usr/bin/whois")
    private val iptablesprog = findprog("/sbin/iptables")

    println(nmapprog, tracerouteprog, whoisprog, iptablesprog)

    val Sense = new LoadSensors(false)

    import java.io.File

    def findprog(prog: String): String = {
      if (new File(prog).exists)
        prog
      else
        Seq(whichprog, prog.split("/").last).!!.trim
    }


    val running = true
    while (running)
      Thread.sleep(1000000)

  }
}