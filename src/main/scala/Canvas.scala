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

case class Value2(cell: String, value: Long)
case class Value3(cell: String, value: Long, time: Long)

class Cell extends Actor {
  def receive: Receive = {
    case Value2(cell, value) =>
      Canvas.Sheet += cell -> (value, System.currentTimeMillis) // todo: allow override
    case Value3(cell, value, time) =>
      Canvas.Sheet += cell -> (value, time)
  }
}