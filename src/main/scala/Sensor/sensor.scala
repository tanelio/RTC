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
  var name: String
}

class Cat(var name: String) extends Pet
class Dog(var name: String) extends Pet

val dog = new Dog("Harry")
val cat = new Cat("Sally")

val animals = ArrayBuffer.empty[Pet]
animals.append(dog)
animals.append(cat)
animals.foreach(pet => println(pet.name))  // Prints Harry Sally

println(animals(1).name)
