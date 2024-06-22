package lectures.basics

import lectures.oop.Counter

import scala.annotation.tailrec

object Recursion extends App {

  def factorial(n: Int): Int = {
    if (n <= 0) 1
    else {
      println("Computing factorial of " + n + " - I first need factorial of " + (n - 1))
      val result = n * factorial(n - 1)
      println("Computed factorial of " + n)
      result
    }
  }

  println(factorial(10))
  // println(factorial(50000)) // give bigger number to crash the JVM

  def anotherFactorial(n: Int): BigInt = {
    @tailrec
    def factorialUtil(a: Int, result: BigInt): BigInt = {
      if (a <= 1) result
      else factorialUtil(a - 1, a * result)
    }

    factorialUtil(n, 1) // Tail recursion read: https://www.codingame.com/playgrounds/58737/fp-module-2---101-scala/function-tail-recursion
  }

  /*
      Breakdown:

      anotherFactorial(10) = factHelper(10, 1)
      = factHelper(9, 10 * 1)
      = factHelper(8, 9 * 10 * 1)
      = factHelper(7, 8 * 9 * 10 * 1)
      = ...
      = factHelper(2, 3 * 4 * ... * 10 * 1)
      = factHelper(1, 1 * 2 * 3 * 4 * ... * 10)
      = 1 * 2 * 3 * 4 * ... * 10
     */
  println(anotherFactorial(10))
  println(anotherFactorial(5000))
  // println(anotherFactorial(50000)) // it print a huge value

  // WHEN YOU NEED LOOPS, USE _TAIL_ RECURSION.


  /*
    Exercises: Implement using tail recursion
    1.  Concatenate a string n times
    2.  IsPrime function tail recursive
    3.  Fibonacci function, tail recursive.
   */
  // 1.  Concatenate a string n times
  @tailrec
  def concatenateStr(aString: String, n: Int, result: String = ""): String = {
    if (n < 1) result
    else concatenateStr(aString, n - 1, aString + result)
  }

  println(concatenateStr("hello ", 5))

  // 2.  IsPrime function tail recursive
  def isPrime(n: Int): Boolean = {
    @tailrec
    def isPrimeUtil(t: Int, flag: Boolean): Boolean = {
      if (t <= 1) flag
      else isPrimeUtil(t - 1, n % t != 0 && flag)
    }

    isPrimeUtil(n / 2, true)
  }

  println(isPrime(37))
  println(isPrime(2003))
  println(isPrime(37 * 17))

  // 3.  Fibonacci function, tail recursive.
  def fibonacci(n: Int): Int = {
    @tailrec
    def fibonacciHelper(counter: Int, last: Int, beforeLast: Int): Int =
      if (counter >= n) last
      else fibonacciHelper(counter + 1, last + beforeLast, last)

    fibonacciHelper(2, 1, 1)
  }

  println(fibonacci(2))
  println(fibonacci(3))
  println(fibonacci(4))
  println(fibonacci(5))
  println(fibonacci(6))
  println(fibonacci(8)) // 1 1 2 3 5 8 13, 21
}
