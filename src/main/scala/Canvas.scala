import akka.actor.Actor

import scala.collection.mutable

case class Node(LU: Long, name: String)

object Canvas {
//  type Time = Long;
//  type Node = (LastUpdate: Long, Name: String)
  var Sheet = new mutable.HashMap[String, (Long, String)]()

}

// Cell ID
//  A1    ?? What if dimensions change?
//  Name  Through HashMap search

case class Value(Id: String, Value: Int)

class Cell extends Actor {
  def receive: Receive = {
    case Value(Id, Value) =>
      Canvas.Sheet[Id]._2 = Value
      Canvas.Sheet[Id]._1 = System.currentTimeMillis()  // todo: allow override
  }
}