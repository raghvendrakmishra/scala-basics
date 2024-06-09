package lectures.part2oop

/**
 * trait vs abstract class?
 *
 * In Scala, both traits and abstract classes serve as blueprints for defining common behavior and functionality that
 * can be shared by multiple classes. However, there are differences between them, and each has its own use cases.
 * Here's a comparison between traits and abstract classes:
 *
 * Traits:
 * Multiple Inheritance: Traits support multiple inheritance, which means a class can mix in multiple traits using the
 * with keyword. This allows for more flexible composition of behavior.
 * Mixin Composition: Traits are commonly used for mixin composition, where common behavior can be mixed into multiple
 * classes independently. This promotes code reuse and modular design.
 * Stateless by Default: Traits are stateless by default, meaning they do not have fields or mutable state associated
 * with them. They are typically used to define behavior rather than store data.
 * Interface-like: Traits can contain both abstract and concrete methods. They are often used to define interfaces with
 * default implementations in Scala.
 * Cannot Have Constructor Parameters: Traits cannot have constructor parameters, which means they cannot have
 * constructors of their own.
 *
 * Abstract Classes:
 * Single Inheritance: Abstract classes support single inheritance, meaning a class can extend only one abstract class.
 * This limits their flexibility compared to traits.
 * Stateful: Abstract classes can have fields and mutable state associated with them. They are suitable for defining
 * common state and behavior that can be shared by subclasses.
 * Can Have Constructor Parameters: Abstract classes can have constructor parameters, which allows for more flexibility
 * in initialization and construction.
 * Partial Implementation: Abstract classes can contain both abstract and concrete methods. They are often used to
 * provide a partial implementation of a class hierarchy, with some methods left abstract for concrete subclasses to
 * implement.

 * Choosing Between Traits and Abstract Classes:
 * Use traits when you want to define interfaces or mixins that can be mixed into multiple classes independently.
 * Use abstract classes when you want to define a common base class with shared state and behavior, or when you need to
 * enforce single inheritance.
 */
object AbstractDataTypes extends App {

  // abstract; mean to implement later
  abstract class Animal(a: Int = 0) {// can have constructor parameter; abstract class = "thing"
    val creatureType: String
    def eat: Unit
  }

  // val animal = new Animal // compile error; Animal is not implemented

  class Dog extends Animal {
    override val creatureType: String = "Russian Dog"
    override def eat: Unit = print("Dog is eating")
  }

  // trait (= interface in Java)
  trait Carnivore { // cannot have constructor parameter; trait = behavior
    def eat(animal: Animal): Unit
  }

  trait ColdBlooded

  class Crocodile extends Animal with Carnivore with ColdBlooded {
    override val creatureType: String = "crocodile"
    override def eat = println("Crocodile is eating...")
    override def eat(animal: Animal): Unit = println(s"I'm a crocodile and I'm eating ${animal.creatureType}")
  }

  val dog = new Dog
  val crocodile = new Crocodile
  crocodile eat dog
  // println(crocodile.eat(dog))

}
