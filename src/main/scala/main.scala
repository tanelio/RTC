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

    //    val attacks = TableQuery[Attacks]
    //    val scans = TableQuery[Scans]
    //    val whois = TableQuery[Whois]

    println(s"creating tab;es/schema")
    //    val system = ActorSystem("scanner")

    val sudoprog = "/usr/bin/sudo"
    val whichprog = "/usr/bin/which"
    val nmapprog = findprog("/usr/bin/nmap")
    val tracerouteprog = findprog("/usr/sbin/traceroute")
    val whoisprog = findprog("/usr/bin/whois")
    val iptablesprog = findprog("/sbin/iptables")

    println(nmapprog, tracerouteprog, whoisprog, iptablesprog)

    val Sense = new LoadSensors(false)

    import java.io.File

    def findprog(prog: String): String = {
      if (new File(prog).exists)
        prog
      else
        Seq(whichprog, prog.split("/").last).!!.trim
    }

    // todo add cleanup of iptables, remove 0 instances on all chains
    // val i = Seq("iptables", "-vnL", "--line-numbers").!!.split("\n")
    // i(2).split("\\s+")
    // Array(1, 0, 0, DROP, all, --, *, *, 77.105.198.240, 0.0.0.0/0)
    // Chain INPUT (policy ACCEPT 347 packets, 37495 bytes)
    // num   pkts bytes target     prot opt in     out     source               destination
    // 1        0     0 DROP       all  --  *      *       77.105.198.240       0.0.0.0/0
    // 2        0     0 DROP       all  --  *      *       101.42.5.163         0.0.0.0/0
    // 0line, 1pkts, 2bytes, 3target, 4prot(all), 5in(*), 6out(*), 7source, 8dest
    //  todo Take the list, if zero the whack it (w/chain)
    // if zero for last of the last
    // if



    val running = true
    while (running)
      Thread.sleep(1000000)

  }
}