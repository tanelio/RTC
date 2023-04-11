import akka.actor.Actor

import scala.Predef.->
import scala.collection.mutable

case class Node(LU: Long, name: String)

object Canvas {
//  type Time = Long;
//  type Node = (LastUpdate: Long, Name: String)
//  var Sheet = new mutable.HashMap[String, (Long, Long)]()
//  var Sheet = new mutable.HashMap[String, (Char, Long, Long, Int, Char)]()
  var Sheet = new mutable.HashMap[String, (Char, Long, Int, AnyVal)]()
  // Init/Type, TS, Index
  // type: Initial value, Double, String, Bigdecimal
//  var floats = Vector[Double]()
//  var text = Vector[String]()
//  var bd = Vector[BigDecimal]()
// Historian -- create some structure of each type, for historical storage
// Use messages to fill/update?
}

// Cell ID
//  A1    What if dimensions change
//  typ   char  Numeric, String
//  time  Long    Last update received
//  valF  Double  Value
//  valS  String
//  Quality Late, Early, wrong Source, Non secure
//  Name  Through HashMap search

case class Value2(cell: String, value: Long)
case class Value3(cell: String, value: Long, time: Long)
case class Create(cell: String, typ: Char) // Create Cell + Set Initial values (non-values)
case class Remove(cell: String) // Remove Cell
//case class Update(cell: String, AnyVal)

class Cell extends Actor {
  import Canvas.Sheet
  def receive: Receive = {
    case Value2(cell, value) =>
//      Sheet.put(cell, (value, System.currentTimeMillis))
    case Value3(cell, value, time) =>
//      Sheet.put(cell, (value, time))
    case Create(cell, typ) =>
      typ match {
        case 'D' =>
      }
//      Sheet += cell -> ('I', 0, 0, 0)
    // Initial value
    case Remove(cell) =>
      Sheet.remove(cell)
//    case Update(cell, v) =>
//      Sheet.put(cell -> (v))
  }
}