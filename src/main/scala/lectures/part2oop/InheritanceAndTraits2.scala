package lectures.part2oop

/**
 * What is trait?
 * What is sealed keyword?
 * What is case keyword?
 */
object InheritanceAndTraits2 extends App {
  /** 
   * In Scala, a trait is similar to an interface in other programming languages, but it can also contain concrete 
   * method implementations. Here's a breakdown of traits in Scala:
   *
   * Definition: A trait is defined using the trait keyword followed by the trait name and optionally a parameter list. 
   * Traits can have abstract methods (methods without implementations) as well as concrete 
   * methods (methods with implementations).
   *
   * Mixin Composition: One of the key features of traits in Scala is mixin composition. This means that a class can 
   * inherit behavior from multiple traits by mixing them in using the with keyword. This allows for code reuse and 
   * promotes modular design.
   *
   * Abstract and Concrete Members: Traits can contain both abstract and concrete members. Abstract members are declared 
   * without an implementation and must be implemented by classes that mix in the trait. Concrete members have 
   * implementations and can be used directly by classes.
   *
   * No Constructors: Traits cannot have constructor parameters. They are not directly instantiable, but they can be 
   * mixed into classes.
   *
   * Extending Traits: Traits can extend other traits, allowing for hierarchical trait composition. A class that mixes 
   * in a trait also inherits any traits extended by that trait.
   *
   * Diamond Inheritance Problem: Traits help avoid the diamond inheritance problem by allowing multiple traits to be 
   * mixed into a class. If two traits define conflicting members, the class mixing them in must provide an 
   * implementation or explicitly choose one.
   *
   * Stateless by Default: Traits are stateless by default, meaning they do not have mutable state associated with them. 
   * They are typically used to define behavior rather than store data.
   *
   * Used for Composition: Traits are commonly used to compose behavior and define mixins that can be reused across 
   * different classes. They promote code reuse and maintainability by encapsulating common functionality.
   *
   * Overall, traits in Scala provide a powerful mechanism for defining reusable components, promoting modular design, 
   * and enabling mixin composition. They are a key feature of the language's support for both object-oriented and 
   * functional programming paradigms.
   * 
   * In Scala the `sealed` keyword is used to define sealed traits and sealed abstract classes.Here is a short
   * description of how it works :
   * 1. ** Sealed Traits **: When you declare a trait as sealed, it means that all its subtypes (classes or traits) must
   * be declared in the same file where the sealed trait is defined, or in a file nested within the same package.This
   * restriction ensures that all possible subtypes of the sealed trait are known at compile - time. It allows pattern
   * matching exhaustiveness checks to be performed by the compiler, providing additional safety and enabling
   * optimizations. Example:
   */
  sealed trait Shape

  case class Circle(radius: Double) extends Shape

  case class Square(side: Double) extends Shape
/**
 * In above example, `Shape` is a sealed trait, and `Circle` and `Square` are its subtypes. Since `Shape` is sealed,
 * any additional subtypes must be declared within the same file or package.
 *
 * 2. ** Sealed Abstract Classes **: Similarly, when you declare an abstract class as sealed, it imposes the same
 * restriction on its subclasses. All subclasses must be in the same file or package as the sealed abstract class.
 * Example: */
  sealed abstract class Vehicle {
    def drive(): Unit
  }

  class Car extends Vehicle {
    override def drive(): Unit = println("Driving car")
  }

  class Bike extends Vehicle {
    override def drive(): Unit = println("Riding bike")
  }

  /** In this example, `Vehicle` is a sealed abstract class, and `Car` and `Bike` are its subclasses. Overall, the
   * `sealed` keyword in Scala helps enforce a restricted hierarchy, ensuring that all possible subclasses are known and
   * enabling better compiler checks and optimizations.
   *
   * In Scala, the case keyword is used in several contexts, but its primary usage is with case classes and case
   * objects. Here's an explanation of how it works:
   *
   * Case Classes: When you declare a class with the case keyword, it creates a number of additional functionalities
   * automatically, including:
   * 1. Automatic generation of a companion object with apply() method, which allows you to create instances of the
   * class without using the new keyword.
   * 2. Automatic generation of accessor methods (getters) for constructor parameters.
   * 3. Automatic generation of equals, hashCode, and toString methods based on the constructor parameters.
   * 4. Support for pattern matching in match expressions.
   *
   * Case Objects: Similar to case classes, case objects are used to define singleton objects with some additional
   * functionalities. When you declare an object with the case keyword, it automatically extends the scala.Product trait
   * and the scala.Serializable trait, and it provides a default toString method.
   *
   * Overall, the case keyword in Scala is primarily used with case classes and case objects to provide additional
   * functionalities and support for pattern matching.
   */
}
