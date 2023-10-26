package Sensor
/*
object sensor {

}

class Historian(val len: Int, val t: Char ) {
  t match {
    case 'I' =>
    case 'L' =>
    case 'D' =>
    case 'S' =>
  }
  val Hist = new Array(len)
}
*/

import java.sql.Timestamp
import scala.collection.mutable.ArrayBuffer

object Historian {

  trait Hist {
    val name: String
  }

  class Hist2[A](val name: String) extends Hist {
    val vals = Array[(Timestamp, A, Char)]() // TS, Typ(Int,Long,Double, String), Quality
  }

  class Dog(val name: String) extends Hist
  class Cat(val name: String) extends Hist

  val dog: Dog = new Dog("Harry")
  val cat: Cat = new Cat("Sally")

  val animals = ArrayBuffer.empty[Hist]
//  animals.append(dog)
//  animals.append(cat)
//  animals.foreach(pet => println(pet.name)) // Prints Harry Sally

  println(animals(1).name)

}
