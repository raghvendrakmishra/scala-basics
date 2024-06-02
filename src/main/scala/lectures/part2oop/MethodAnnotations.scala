package lectures.part2oop

import scala.annotation.targetName
import scala.language.postfixOps

/** Object: Defines a singleton object, meaning there's only one instance. It can have methods and fields but cannot 
 * have parameters. 
 * 
 * Class: Defines a blueprint for creating objects (instances). It can have parameters, methods, and fields. Multiple 
 * instances of a class can be created.
 * 
 * In summary, use class for creating multiple instances with state and behavior, and use object for defining single 
 * instances (singletons) or utility methods and constants.
*/
object MethodAnnotations extends App {

  class Person(val name: String, favoriteMovie: String,  val age: Int = 0) {

    def likes(movie: String) = favoriteMovie == movie
    @targetName("hangout")
    def +(person: Person): String = s"${this.name} is handing out with ${person.name}"
    @targetName("!Person")
    def unary_! : String = s"$name what is this?"
    def isAlive: Boolean = true
    def apply(): String = s"Hi, my name is $name and I like $favoriteMovie"
    @targetName("personWithAdvancedName")
    def +(aString: String): Person = new Person(s"$name ($aString)", favoriteMovie)
    @targetName("agePlusOne")
    def unary_+ : Person = new Person(name, favoriteMovie, age + 1)
    def apply(count: Int): String = s"$name watched $favoriteMovie $count times"
    def learns(thing: String): String = s"$name is learning $thing"
    def learnsScala = this learns "Scala"
  }

  val mary = new Person("Mary", "Magadheera")
  println(mary.likes("Magadheera"))
  // it is called, infix notation = operator notation (syntactic sugar)
  // It only works with a method with single parameter
  println(mary likes "Magadheera") // equivalent as above

  // Operators in Scala
  val tom = new Person("Tom", "Terminator")
  println(mary + tom)
  println(mary.+(tom))

  // Math operators in scala are methods too. Example
  println(1 + 2)
  println(1.+(2))

  // prefix notations
  val x = -1
  val y = 1.unary_- // both are same
  // unary_ prefix only works with - + ~ !
  println(mary.unary_!)
  println(!mary) // both are same

  // postfix operators
  println(mary.isAlive)
  println(mary isAlive) // same as above // only available with the scala.language.postfixOps import - discouraged

  // apply
  println(mary.apply())
  println(mary()) // equivalent

  /*
      1.  Overload the + operator
          mary + "the rockstar" => new person "Mary (the rockstar)"

      2.  Add an age to the Person class
          Add a unary + operator => new person with the age + 1
          +mary => mary with the age incrementer

      3.  Add a "learns" method in the Person class => "Mary learns Scala"
          Add a learnsScala method, calls learns method with "Scala".
          Use it in postfix notation.

      4.  Overload the apply method
          mary.apply(2) => "Mary watched Inception 2 times"
     */
  println((mary + "the Rockstar").apply())
  println((+mary).age)
  println(mary learnsScala)
  println(mary(10))
}
