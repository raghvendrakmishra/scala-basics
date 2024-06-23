package practice.concepts.functionalprogramming

/**
 * In Scala a higher -order function is a function that either takes one or more functions as arguments or returns a
 * function as its result. Higher - order functions are a powerful feature of functional programming languages like
 * Scala enabling more abstract and concise code.
 *
 * ### Benefits of Higher -Order Functions
 * - ** Abstraction **: They allow you to abstract over behavior, enabling code reuse and more modular design.
 * - ** Conciseness **: Code can be more concise and expressive.
 * - ** Functional Composition **: Functions can be composed and combined in a flexible manner.
 *
 * Higher - order functions are fundamental to functional programming in Scala, providing powerful tools for abstraction
 * and code composition
 *
 * Higher-order functions:
 * map, flatMap, filter etc
 */
object HigherOrderFunctionsAndCurries extends App {
  /**
   * 1. ** Function as a Parameter **:
    * A higher -order function can take other functions as parameters. This allows you to pass in behavior to be
    * applied within the function.
    */
  def applyFunction(f: Int => Int, x: Int): Int = f(x)

  val increment: Int => Int = _ + 1
  println(applyFunction(increment, 5)) // Output: 6

  /**
   * 2. ** Function as a Return Value **:
   * A higher -order function can return a function.This can be used to create more specialized functions based on some
   * parameters.
   */

  def createAdder(y: Int): Int => Int = {
    (x: Int) => x + y
  }

  val addFive = createAdder(5)
  println(addFive(10)) // Output: 15

  /**
   * 3. ** Combination of Both **:
   * Higher - order functions can both take functions as parameters and return functions.
   */

  def combineFunctions(f: Int => Int, g: Int => Int): Int => Int = {
    (x: Int) => f(g(x))
  }

  val multiplyByTwo: Int => Int = _ * 2
  val addThree: Int => Int = _ + 3
  val combinedFunction = combineFunctions(multiplyByTwo, addThree)
  println(combinedFunction(4)) // Output: 14

  /**
   * ### Examples of Higher -Order Functions in Scala 's Standard Library
   *
   * - ** `map` **: Applies a function to each element of a collection and returns a new collection of the results
   */
  val numbers1 = List(1, 2, 3, 4)
  val squaredNumbers = numbers1.map(x => x * x)
  println(squaredNumbers) // Output: List(1, 4, 9, 16)

  /**
   * - ** `filter` **: Returns a
   * new collection containing only the elements that satisfy a given predicate function.
   */

  val numbers2 = List(1, 2, 3, 4, 5)
  val evenNumbers = numbers2.filter(_ % 2 == 0)
  println(evenNumbers) // Output: List(2, 4)

  /**
   * - ** `reduce` **: Applies a binary function to the elements of a collection, reducing them to a single result
   */
  val numbers = List(1, 2, 3, 4)
  val sum = numbers.reduce(_ + _)
  println(sum) // Output: 10

//  val complexFunction: (Int, (String, Int => Boolean) => Int) => Int => Int = ???

  // function that applies a function on given value x
  // nTimes(f, n, x)
  // nTimes(f, 3, x) = f(f(f(x))) = nTimes(f, 2, f(f(x)))
  // nTimes(f, n, x) = f(f(f(...f(x)))) = nTimes(f, n-1, f(x))
  def nTimes(f: Int => Int, n: Int, x: Int): Int = {
    if(n <= 0) x
    else nTimes(f, n-1, f(x))
  }

  private val plusOne: Int => Int = _ + 1
  println(nTimes(plusOne, 10, 1))

  // nTimeBetter(f, n) = x => f(f(f(...(x))))
  // increment10 = nTimeBetter(plusOne, 10) = plusOne(plusOne(plusOne(...(10))))
  // val y = increment10(1)
  def nTimesBetter(f: Int => Int, n: Int): Int => Int = x => {
    if(n <= 0) x
    else nTimesBetter(f, n-1) (f(x))
  }

  private val plus10 = nTimesBetter(plusOne, 10)
  println(plus10(1))

  // Curries functions
  val superAdder: Int => (Int => Int) = (x: Int) => (y: Int) => x + y
  val add3 = superAdder(3) // f = 3 + y
  println(add3(10))
  println(superAdder(3)(10))

  // functions with multiple parameter list
  def curriesFormatter(str: String) (input: Double): String = str.format(input)

  private val standardFormat: Double => String = curriesFormatter("%4.2f")
  private val precisionFormat: Double => String = curriesFormatter("%10.8f")

  println(standardFormat(Math.PI))
  println(precisionFormat(Math.PI))

  /**
   * Exercise
   * 1. Expand MyList
   *      - forEach method: A => Unit
   *        [1,2,3].forEach(x => println(x))
   *
   *      - sort method: ((A, A) => Int) => MyList
   *        [1,2,3].sort((x,y) => y = x) => [3,2,1]
   *
   *      - zipWith (list, (A, A) => B) => MyList[B]
   *        [1,2,3].zipWith([4,5,6], x*y) => [1*4, 2*5, 3*6]
   *
   *      - fold (start) (function) => a value
   *        [1,2,3].fold(0)((x,y) => x+y) = 6
   *
   * 2. toCurry(f: (Int, Int) => Int): Int => Int => Int =
   *      x => y => f(x, y)
   *    fromCurry(f: Int => Int => Int): (Int, Int) => Int
   *
   * 3. compose(f, g) => x =>  f(g(x)
   *    andThen(f, g) => x => g(f(x)
   */

  def toCurry(f: (Int, Int) => Int): Int => Int => Int =
        x => y => f(x, y)

  def fromCurry(f: Int => Int => Int): (Int, Int) => Int =
    (x, y) => f(x)(y)

  def compose[A, B, T](f: A => B, g: T => A): T => B =
    x => f(g(x))

  def andThen[A, B, C](f: A => B, g: B => C): A => C =
    x => g(f(x))

  def superAdder2: Int => Int => Int = toCurry(_ + _)
  val add4 = superAdder2(4)
  println(add4(17))

  val simpleAdder = fromCurry(superAdder)
  println(simpleAdder(4, 17))

  val add2 = (x: Int) => x + 2
  val times3 = (x: Int) => x * 3

  val composed = compose(add2, times3)
  val ordered = andThen(add2, times3)
  
  println(composed(4))
  println(ordered(4))

}
