package lectures.part2oop

import java.time.LocalDateTime

object OOBasics extends App {

  // class parameters are not fields, so we cannot access name and age outside of class without using val in the
  // constructor signature
  class Person(name: String, val age: Int = 0) { // constructor
    // class local variable
    val x = 2
    private val y = 10

    // expressions
    println(1 + 2)

    // defining functions
    def greet(name: String): Unit = println(s"${this.name} says: Hi, $name")

    def greet(): Unit = println(s"Hi I am ${this.name}")

    // def greet(): Int = 20 // compile error

    // Multiple Constructors
    def this(name: String) = this(name, 0) // Additional Constructors can only be assigned to another Constructors

    def this() = this("Unknown")
  }

  val person = Person("Raghu", 20) // adding new keyword before constructor is optional
  println(person)
  println(person.age)
  println(person.x)
  // println(person.y) // cannot access private members outside of the class
  person.greet("Stephen")
  person.greet()

  // exercise test
  val author = Writer("Charles", "Dickens", 1812) // adding new keyword before constructor is optional
  val imposter = Writer("Charles", "Dickens", 1812) // adding new keyword before constructor is optional
  val novel = Novel("Great Expectations", 1861, author) // adding new keyword before constructor is optional

  println(novel.authorAge)
  println(novel.isWrittenBy(imposter))
  println(novel.copy(2025).toString)

  val counter = new Counter // valid
  // val counter = Counter() // valid
  // val counter = Counter // invalid
  counter.increment.print
  counter.increment.increment.increment.print
  counter.increment(10000).decrement(5000).print
}


/*
  Novel and a Writer

  Writer: first name, surname, yearOfBirth
    - method fullname

  Novel: name, year of release, author
  - authorAge: on the year of release
  - isWrittenBy(author)
  - copy (new year of release) = new instance of Novel
 */

class Writer(firstName: String, surname: String, val yearOfBirth: Int) {

  def fullName() = s"$firstName $surname"
}

class Novel(name: String, yearOfRelease: Int, author: Writer) {
  def authorAge = this.yearOfRelease - author.yearOfBirth

  def isWrittenBy(author: Writer): Boolean = this.author == author

  def copy(newYearOfRelease: Int): Novel = Novel(this.name, newYearOfRelease, author) // adding new keyword before constructor is optional
}

/*
    Counter class
      - receives an int value
      - method current count
      - method to increment/decrement => new Counter
      - overload inc/dec to receive an amount
   */
class Counter(count: Int = 0) {

  // Constructor
  //def this() = this(0)

  def currentCount = count
  def increment = {
    println("incrementing by 1")
    new Counter(count + 1)  // immutability
  }

  def increment(n: Int) = {
    println(s"incrementing by $n")
    new Counter(count + n)
  }
  def decrement = {
    println("decrementing by 1")
    new Counter(count - 1)
  }
  def decrement(n: Int) = {
    println(s"decrementing by $n")
    new Counter(count - n)
  }
  def print = println(count)
}
