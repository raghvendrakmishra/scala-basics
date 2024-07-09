package practice.concepts.patternmatching

object PatternMatching1 extends App {

  /** ### 1.** Syntax and Basic Usage **:
    * - ** Syntax **: Uses the `match` keyword followed by `case` statements.
    * - ** Example **:
  **/
  val x: Int = 10
  x match {
    case 1 => println("One")
    case 2 => println("Two")
    case _ => println("Other")
  }

  /** ### 2. ** Match Expressions **:
   * -Returns a value, making it an expression rather than a statement.
   * - Can be assigned to variables or used directly in expressions.
   */
  val description = x match {
   case 1 => "One"
   case 2 => "Two"
   case _ => "Other"
  }
  println(description)

  /**
   * ### 3. ** Pattern Types **:
   * - ** Literal Patterns **: Match exact values.
   * - ** Variable Patterns **: Capture matched values in variables.
   * - ** Wildcard Patterns **: Use `_` to match anything
   */
  val result = x match {
   case 0 => "Zero"
   case somethingElse => s"Not zero: $somethingElse"
  }
  println(result)

  /**
   * ### 4. ** Case Classes and Tuples **:
   * -Decompose and match case classes and tuples.
   */
  case class Person(name: String, age: Int)
  val person = Person("Alice", 25)
  val personMatch = person match {
    case Person("Alice", age) => println(s"Alice is $age years old")
    case _ => println("Not Alice")
  }
  println(personMatch)

  /**
   * ### 5. ** Type Patterns **:
   * -Match based on the type of the value.
   */
  def process(input: Any): String = input match {
    case s: String => s"String: $s"
    case i: Int => s"Integer: $i"
    case _ => "Unknown"
  }
  println(process(10))

  /**
   * ### 6. ** Guards **:
   * - Additional conditions using `if` expressions.
   */
  val number2 = 5
  val numResult = number2 match {
    case x if x % 2 == 0 => "Even"
    case x if x % 2 != 0 => "Odd"
  }
  println(numResult)

  /**
   * ### 7. ** Extractor Patterns **:
   * -Use `unapply` methods to extract values.
   */
  object Even {
    def unapply(x: Int): Option[Int] = if (x % 2 == 0) Some(x) else None
  }

  val number = 4
  val numResult2 = number match {
    case Even(n) => s"$n is even"
    case _ => "Odd"
  }
  println(numResult2)

  /**
   * ### 8. ** Partial Functions **:
   * -Pattern matching within partial functions.
   */
  val partialFunc: PartialFunction[Int, String] = {
    case 1 => "One"
    case 2 => "Two"
  }

  /**
   * ### 9. ** Sealed Traits and Exhaustiveness **:
   * -Using sealed traits / classes ensures pattern matching is exhaustive
   */
  sealed trait Animal
  case class Dog(name: String) extends Animal
  case class Cat(name: String) extends Animal

  val pet: Animal = Dog("Buddy")
  val petResult = pet match {
    case Dog(name) => s"Dog named $name"
    case Cat(name) => s"Cat named $name"
  }
  println(petResult)

  /**
   * ### 10. ** Nested Patterns **:
   * -Match deeply nested structures
   */
  case class Address(city: String, zip: String)
  case class User(name: String, address: Address)

  val user = User("John", Address("New York", "10001"))
  user match {
    case User(_, Address("New York", _)) => "Lives in New York"
    case _ => "Lives elsewhere"
  }
}
