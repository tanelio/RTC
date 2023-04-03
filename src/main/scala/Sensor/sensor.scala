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

import scala.collection.mutable.ArrayBuffer

trait Pet {
  val name: String
}

class Cat(val name: String) extends Pet
class Dog(val name: String) extends Pet

val dog = new Dog("Harry")
val cat = new Cat("Sally")

val animals = ArrayBuffer.empty[Pet]
animals.append(dog)
animals.append(cat)
animals.foreach(pet => println(pet.name))  // Prints Harry Sally
