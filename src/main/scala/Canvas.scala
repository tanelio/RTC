import akka.actor.Actor

import scala.collection.mutable

case class Node(LU: Long, name: String)

object Canvas {
//  type Time = Long;
//  type Node = (LastUpdate: Long, Name: String)
  var Sheet = new mutable.HashMap[String, (Long, Long)]()
}

// Cell ID
//  A1    What if dimensions change
//  time  Long    Last update received
//  value Double|String|IP  Value
//        String
//  Quality
//  Name  Through HashMap search

case class Value2(cell: String, value: Long)
case class Value3(cell: String, value: Long, time: Long)
case class Create(cell: String)
case class Remove(cell: String)

class Cell extends Actor {
  import Canvas.Sheet
  def receive: Receive = {
    case Value2(cell, value) =>
      Sheet.put(cell, (value, System.currentTimeMillis))
    case Value3(cell, value, time) =>
      Sheet.put(cell, (value, time))
    case Create(cell) =>
      Sheet += cell -> (0, 0)
    case Remove(cell) =>
      Sheet.remove(cell)
  }
}