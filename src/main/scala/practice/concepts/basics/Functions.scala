package practice.concepts.basics

import scala.annotation.tailrec

object Functions extends App {
  def aFunction(a: String, b: Int) = { // compiler figure out the return type of non-recursive function
    a + " " + b
  }

  println(aFunction("Hello", 3))

  def aParameterlessFunction(): Int = 33
  // println(aParameterlessFunction) // Compile Error: method aParameterlessFunction in object Functions must be called with () argument
  println(aParameterlessFunction())

  def aRepeatedFunction(aString: String, n: Int): String = { // compiler cannot figure out the return type of recursive function
    if (n <= 1) aString
    else aString + aRepeatedFunction(aString, n - 1) // Recursive method aRepeatedFunction needs result type, if return type removed
  }

  println(aRepeatedFunction("Scala ", 5))

  // WHEN YOU NEED LOOP, USE RECURSION

  def aFunctionWithSideEffects(aString: String): Unit = println(aString)

  // We can write an auxiliary function inside another function
  def aBigFunction(n: Int): Int = {
    def aSmallerFunction(a: Int, b: Int): Int = a + b

    aSmallerFunction(n, n - 1)
  }

  println(aBigFunction(2))

  /*
      Exercises:
      1.  A greeting function (name, age) => "Hi, my name is $name and I am $age years old."
      2.  Factorial function 1 * 2 * 3 * .. * n
      3.  A Fibonacci function
          f(1) = 1
          f(2) = 1
          f(n) = f(n - 1) + f(n - 2)
      4.  Tests if a number is prime.
  */

  def greeting(name: String, age: Int) = "Hi, my name is " + name + " and I'm " + age + " years old."

  println(greeting("Raghu", 500))

  def factorial(n: Int): Int = {
    if (n <= 0) 1
    else n * factorial(n - 1)
  }

  println("factorial of 3 is " + factorial(5))
  // println("factorial of 3 is " + factorial(Int.MaxValue))

  def fibonacci(n: Int): Int = {
    if (n <= 2) 1
    else fibonacci(n - 1) + fibonacci(n - 2)
  }

  println(fibonacci(5))
  println(fibonacci(8))

  def isPrime(n: Int) = {
    def isPrimeUtil(t: Int): Boolean = {
      if (t <= 1) true
      else n % t != 0 && isPrimeUtil(t - 1)
    }

    isPrimeUtil(n / 2)
  }

  println(isPrime(37))
  println(isPrime(2003))
  println(isPrime(37 * 17))
}
