package practice.concepts.functionalprogramming

/**
 * Use function as first class elements
 *
 * Treating all scala functions as objects
 *
 */
object WhatIsFunction extends App {

  private val doubler = new MyFunction[Int, Int] {
    override def apply(element: Int): Int = element * 2
  }

  println(doubler(5)) // Calling doubler as a function since MyFunction define apply() method

  /**
   * Scala provide OOTB Function to use
   *
   * Function in scala = Function1, Function2, ................Function22
   * Function1 = Function1[A, B]
   * Function2 = Function2[A, B, C]
   *
   * Here FunctionN means a function with apply method which take N-1 inputs and 1 output
   *
   */
  val stringToIntConverter = new Function1[String, Int] {
    override def apply(element: String): Int = element.toInt
  }

  println(stringToIntConverter("100") + 5)

  // V1
  /*val adder: Function2[Int, Int, Int] = new Function2[Int, Int, Int] {
    override def apply(element1: Int, element2: Int): Int = element1 + element2
  }*/

  // V2
  /*val adder: (Int, Int) => Int = new Function2[Int, Int, Int] {
    override def apply(v1: Int, v2: Int): Int = v1 + v2
  }*/

  // V3
  // val adder: (Int, Int) => Int = (v1: Int, v2: Int) => v1 + v2

  // V4
  val adder: (Int, Int) => Int = (v1, v2) => v1 + v2
  // Function type Function2[A, B, R] = (A, B) => R

  println(adder(20, 30))

  /**
   * Exercise
   *
   * 1. Write a function which take 2 strings and concatenate them
   * 2. Transform MyPredicate and MyTransformer into Function types in MyList implementation
   * 3. Define a function which take and int and return another function which take an int and return an int
   *    - Define what is the type of function
   *
   */
  // 1. Write a function which take 2 strings and concatenate them
  val concatenate: (String, String) => String = (s1, s2) => s1 + s2
  println(concatenate("Ramesh", "Y"))
  // 2. Define a function which take and int and return another function which take an int and return an int
  // Function1[Int, Function1[Int, Int]]
  val doubleAdder: Function1[Int, Function1[Int, Int]] = new Function1[Int, Function1[Int, Int]] {
    override def apply(v1: Int): Function1[Int, Int] = new Function1[Int, Int] {
      override def apply(v2: Int): Int = v1 + v2
    }
  }
  
  val adder3 = doubleAdder(10)
  println(adder3(20))
  println(doubleAdder(10)(20)) // curried function


}

trait MyFunction[A, B] {
  def apply(element: A): B
}
