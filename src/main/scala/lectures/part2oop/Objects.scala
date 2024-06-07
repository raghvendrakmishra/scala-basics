package lectures.part2oop

/**
 * What are objects in scala?
 * How objects defer from class in scala?
 * What is companion design pattern in scala?
 *
 * In Scala, the Companion Design Pattern refers to the practice of defining a class and its companion object in the
 * same file with the same name.
 *
 * Class: The class defines instance-specific properties and behavior. It is instantiated to create objects with state
 * and behavior.
 * Companion Object: The companion object is a singleton object associated with the class. It contains methods and
 * properties that are not tied to specific instances of the class. These methods can be called directly on the class
 * name without creating an instance of the class.
 * Scala companions can access each other's private members
 */
object Objects /* extends App */ {

  /**
   * Class levels static methods and variables defined using object
   * Scala objets are singleton instances by definition
   * SCALA DOES NOT HAVE CLASS LEVEL FUNCTIONALITY ("static"), But it has even better -> object
   * SCALA OBJECT = SINGLETON OBJECT
   */
  object Person { // TYPE OF PERSON + THERE WILL BE ONLY ONE INSTANCE
    // "static" or class level functionality
    val N_EYES = 2
    def canFly: Boolean = false
    // factory method
    def from(mother: Person, father: Person): Person = new Person("Bobbie")
    def apply(mother: Person, father: Person): Person = new Person("Bobbie")
  }
  class Person(val name: String) {
    // instance level functionality

  }

  // Scala Applications = Scala object with
  // def main(args: Array[String]): Unit
  def main(args: Array[String]): Unit = {
    println(Person.N_EYES)
    println(Person.canFly)

    // Scala object = SINGLETON INSTANCE
    val gaurav = Person
    val murali = Person
    println(gaurav == murali) // true, since both the instance refer to the same reference

    val raghu = new Person("Raghu")
    val tanuja = new Person("Tanuja")
    println(raghu == tanuja) // false, since new class level instance created

    val mary = new Person("Mary")
    val john = new Person("John")
    val bobbie1 = Person.from(mary, john)
    val bobbie2 = Person.apply(mary, john) // Person.apply() == Person()
    val bobbie3 = Person(mary, john)
  }


}
