package lectures.part2oop

object AnonymousClasses extends App {

  abstract class Animal {
    def eat: Unit
  }

  // Anonymous class
  val hungryAnimal = new Animal {
    override def eat: Unit = println("Animal is eating...")
  }
  /**
   * Equivalent with:
   * class AnonymousClasses$$anon$1 extends Animal {
   *  override def eat: Unit = println("Animal is eating...")
   * }
   * val hungryAnimal = new AnonymousClasses$$anon$1
   */
  hungryAnimal.eat
  println(hungryAnimal.getClass)
  
  class Person(name: String) {
    def sayHi = s"$name is saying Hi..."
  }
  val swpanil = new Person("Swapnil") {
    override def sayHi = "Swapnil is saying Hello..."
  }
}
