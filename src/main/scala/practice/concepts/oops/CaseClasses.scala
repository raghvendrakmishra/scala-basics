package practice.concepts.oops

/**
 * A case class in Scala is a special type of class that is optimized for use in pattern matching and immutable data
 * structures. Here are the key points about case classes:
 *
 * 1. **Immutable by Default**: All fields in a case class are val (immutable), unless explicitly declared as var.
 *
 * 2. **Automatic Implementations**:
 *    - **`equals` and `hashCode`**: Automatically generated, making case classes ideal for use in collections like
 *    sets and maps.
 *    - **`toString`**: Provides a human-readable string representation of the object.
 *    - **`copy` Method**: Automatically provided to create a copy of an instance with some fields modified.
 *
 * 3. **Pattern Matching**: Case classes work seamlessly with Scala's pattern matching, allowing for concise and
 * readable extraction of data.
 *
 * 4. **Concise Syntax**: Declaration of a case class is more concise compared to regular classes, with parameters
 * automatically becoming fields.
 *
 * 5. **Companion Object**: Automatically generated with apply and unapply methods, making it easy to create instances
 * without using the `new` keyword.
 *
 * 6. **Serialization**: Case classes are serializable by default, which is useful for distributed systems.
 *
 * ### Example
 *
 * ```scala
 * sealed trait Shape
 *
 * case class Circle(radius: Double) extends Shape
 * case class Rectangle(length: Double, width: Double) extends Shape
 * ```
 *
 * In this example:
 * - `Circle` and `Rectangle` are case classes extending the `Shape` trait.
 * - Instances of these classes can be created without `new` (e.g., `Circle(5.0)`).
 * - They can be used in pattern matching to easily extract their properties.
 *
 * ### Usage in Pattern Matching
 *
 * ```scala
 * def describe(shape: Shape): String = shape match {
 * case Circle(radius) => s"A circle with radius $radius"
 * case Rectangle(length, width) => s"A rectangle with length $length and width $width"
 * }
 * ```
 *
 * This function uses pattern matching to provide a description based on the type of shape.
 *
 * ### Key Points Recap
 *
 * - Immutable by default
 * - Automatic implementations of `equals`, `hashCode`, `toString`, and `copy`
 * - Optimized for pattern matching
 * - Concise syntax with automatic companion object
 * - Serializable by default
 *
 * These features make case classes a powerful tool in Scala for defining and working with immutable data structures.
 */
object CaseClasses extends App {

  case class Person(name: String, age: Int)

  // 1. class parameters are fields
  val gaurav = new Person("Gaurav", 20)
  println(gaurav.name) // Gaurav

  // 2. Sensible toString
  // println(instance) == println(instance.toString())
  println(gaurav)
  println(gaurav.toString) // Person(Gaurav,20)

  // 3. equals and hashcode implementation OOTB (out-of-the-box)
  val gaurav2 = new Person("Gaurav", 20)
  println(gaurav == gaurav2) // true
  println(gaurav.hashCode)
  println(gaurav2.hashCode)
  println(gaurav.hashCode == gaurav2.hashCode) // true

  // 4. Case-classes have OOTB copy method
  val gaurav3 = gaurav.copy(age = 45)
  println(gaurav3)

  // 5. Case-classes have companion objects
  // In practice we don't use the keyword "new" when instantiating case class
  val sonali = Person
  println(s"$sonali has hashCode: ${sonali.hashCode()}")
  // print(s"${sonali.name}") // compile error
  val arnab = Person("Arnab", 19) // companion object's apply method does the same thing as the constructor
  println(s"$arnab has hashCode: ${arnab.hashCode()}")

  // 6. Case-classes are serializable which make it useful when dealing with distributed system
  // Which means we can send the instances of case classes in through the network and in b/w JVMs
  // Useful when dealing with Akka framework: Akka send serializable message through the network

  // 7. Case-classes have extractor patterns = CCs could be used in pattern matching

  case object India {
    def name: String = "India is the greatest country in the earth"
  }

  /**
   * Exercise:
   *
   * Expand MyList to use case classes and case objects
   */


}