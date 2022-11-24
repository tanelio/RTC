import akka.actor.{Actor, ActorSystem, Props}
import akka.event.Logging
import akka.actor.ReceiveTimeout

import scala.concurrent.duration.Duration

class Sensor extends Actor
{
  val log = Logging(RTC.system, this)
  var InitialTimeout = 60
  context.setReceiveTimeout(InitialTimeout seconds)
  def receive = {
    case "text" => log.info("received text")
    case ReceiveTimeout => log.info("Timeout")
      context.setReceiveTimeout(Duration.Undefined) // Turn off the receiveTimeout...
    case _      => log.info("received unknown message")
  }
}
