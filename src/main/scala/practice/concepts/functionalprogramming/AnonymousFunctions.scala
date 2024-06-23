package practice.concepts.functionalprogramming

object AnonymousFunctions extends App {
  // 1
  /*val doubler: Function1[Int, Int] = new Function[Int, Int] {
    override def apply(element: Int): Int = element * 2
  }*/

  // 2
  /*val doubler: Int => Int = new Function[Int, Int] {
    override def apply(element: Int): Int = element * 2
  }*/

  // 3
  // val doubler: Int => Int = (element: Int) => element * 2
  // val doubler: Int => Int = element => element * 2

  // 4
  // LAMBDA = Mathematical representation of functional programming
  val doubler = (element: Int) => element * 2

  // Multiple parameters in Lambda
  val adder = (element1: Int, element2: Int) => element1 + element2

  // No parameter in Lambda
  val noParamMethod: () => Int = () => 5

  println(10)
  println(adder(1, 2))
  println(noParamMethod()) // invoke method with no param print the returned value
  println(noParamMethod) // print the function

  // Curly braces with Lambda
  val stringToInt = { (str: String) =>
    str.toInt
  }
  println(stringToInt("23"))

  // MOAR syntactic sugar
  val incrementByOne: Int => Int = _ + 1 // = x => x + 1
  val add2Numbers: (Int, Int) => Int = _ + _ // = (a, b) => a + b

  /**
   * Exercise
   *
   * 1. MyList: Replace all FunctionX calls with Lambda
   * 2. Define a function which take and int and return another function which take an int and return an int
   *    - Write it as Lambda
   *
   */
  // 2. Define a function which take and int and return another function which take an int and return an int
  // Function1[Int, Function1[Int, Int]]
  // val doubleAdder: Int => Int => Int = (element1: Int) => (element2: Int) => element1 + element2
  val doubleAdder = (element1: Int) => (element2: Int) => element1 + element2
  println(doubleAdder(2)(3))
}