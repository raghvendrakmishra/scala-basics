package practice.concepts.oops

/**
 * 1. **Exception Handling Mechanism**:
 *    - Scala uses the `try`, `catch`, and `finally` blocks to handle exceptions, similar to Java.
 *    - The `try` block contains code that might throw an exception.
 *    - The `catch` block handles exceptions, and it can catch multiple types of exceptions using case patterns.
 *    - The `finally` block contains code that will execute regardless of whether an exception is thrown or not,
 *    typically used for cleanup actions.
 *
 * 2. **Exception Types**:
 *    - Scala supports all the standard Java exceptions since it runs on the JVM.
 *    - Scala also provides its own exceptions like `NoSuchElementException` and `MatchError`.
 *
 * 3. **Checked vs. Unchecked Exceptions**:
 *    - Unlike Java, Scala does not distinguish between checked and unchecked exceptions.
 *    - All exceptions in Scala are unchecked, meaning the compiler does not require methods to declare the exceptions
 *    they might throw.
 *
 * 4. **Pattern Matching in Catch Blocks**:
 *    - The `catch` block in Scala uses pattern matching, making it more powerful and flexible than Java's catch blocks.
 *    - Multiple exceptions can be handled in a single `catch` block using case patterns.
 *
 * 5. **Throwing Exceptions**:
 *    - Exceptions are thrown using the `throw` keyword.
 *    - A throw expression has a type of `Nothing`, which is a bottom type in Scala’s type hierarchy. This is useful
 *    for type inference.
 *
 * 6. **Custom Exceptions**:
 *    - You can define custom exceptions by extending `Exception` or any of its subclasses.
 *    - Custom exceptions can have additional fields and methods for more detailed error information.
 *
 * 7. **Try, Success, and Failure**:
 *    - Scala provides the `Try` monad, which represents a computation that may either result in a value (`Success`) or
 *    an exception (`Failure`).
 *    - This provides a more functional approach to handling exceptions compared to traditional try-catch blocks.
 *
 * 8. **Using Option for Error Handling**:
 *    - For cases where an error condition is not exceptional but rather a common case (e.g., element not found),
 *    Scala’s `Option` type is often used.
 *    - `Option` can be `Some(value)` if there is a value, or `None` if there isn’t, avoiding the use of exceptions for
 *    flow control.
 *
 * 9. **Resources Management**:
 *    - Scala’s `Using` object provides a concise way to manage resources that need to be closed after use.
 *    - This helps avoid resource leaks and is similar to Java's try-with-resources.
 *
 * 10. **Best Practices**:
 *     - Use exceptions for exceptional conditions, not for regular control flow.
 *     - Prefer `Try`, `Option`, or other functional constructs for error handling where applicable.
 *     - Ensure that `finally` blocks do not contain code that might throw exceptions to avoid masking the original
 *     exception.
 *
 * By understanding these points, you can effectively manage and handle exceptions in Scala, leveraging both traditional
 * and functional programming paradigms.
 */
object Exceptions extends App {

  val x: String = null
  //println(x.length) // crash with NullPointerException

  // 1. Throwing exception

  // val npeValue: Nothing = throw new NullPointerException
  // val npeValue2: String = throw new NullPointerException // Using String in place of Nothing is valid

  // throwable classes extend the Throwable class
  // Exception and Error are subtypes of Throwable

  // 2. Catching exception
  private def getInt(withException: Boolean): Int = {
    if(withException) throw new RuntimeException("No int due to exception")
    42
  }

  val potentialFail = try {
    // code that might fail
    getInt(true)
    // getInt(false)
  } catch {
    case e: RuntimeException => 43
    // case e: RuntimeException => println("Caught a runtime exception")
    // case e: NullPointerException => println("Caught a NullPointerException")
  } finally {
    // Always get executed whether exception thrown or not
    // return type if optional and does not influence return type of this expression
    // use finally only for side effects
    println("finally block")
  }

  println(potentialFail)

  // 3. Define custom exception
  class MyException extends Exception
  val exception = new MyException

  //throw exception

  /**
   * Exercise:
   *
   * 1. Crash your program with OutOfMemoryError
   * 2. Crash your program with StackOverFlowError
   * 3. PocketCalculator
   *    - add(x, y)
   *    - subtract(x, y)
   *    - multiply(x, y)
   *    - divide(x, y)
   *
   *    Throw
   *      - OverflowException if add(x, y) exceeds Int.MAX_VALUE
   *      - UnderflowException if subtract(x, y) exceeds Int.MIN_VALUE
   *      - MathCalculationException for divide by zero
   */

  // 1. Crash your program with OutOfMemoryError
  // val array = Array.ofDim[Int](Int.MaxValue)

  // 2. Crash your program with StackOverFlowError
  def infiniteRecursive(num: Int): Int = {
    1 + infiniteRecursive(num)
  }
  //println(infiniteRecursive(10))

  // Pocket Calculator
  // Custom exception classes
  class OverflowException extends ArithmeticException

  class UnderflowException extends ArithmeticException

  class MathCalculationException extends ArithmeticException
  
  object PocketCalculator {
    
    def add(x: Int, y: Int) = {
      val result = x + y

      if(x > 0 && y > 0 && result < 0) throw new OverflowException // 2 positive numbers overflow to -ve number
      else if(x < 0 && y < 0 && result > 0) throw new UnderflowException // 2 negative numbers underflow to +ve number
      else result
    }

    def subtract(x: Int, y: Int) = {
      val result = x - y

      if(x > 0 && y < 0 && result < 0) throw new OverflowException //
      else if(x < 0 && y > 0 && result > 0) throw new UnderflowException
      else result
    }

    def multiply(x: Int, y: Int): Int = {
      val result = x * y

      if((x > 0 && y > 0 && result < 0) || (x < 0 && y < 0 && result < 0)) throw new OverflowException
      else if((x < 0 || y < 0) && result > 0) throw new UnderflowException
      else result
    }
    
    def divide(x: Int, y: Int): Int = {
      if(y == 0) throw new MathCalculationException
      else x / y
    }
  }
  
  println(PocketCalculator.add(Int.MaxValue, 10))
  //println(PocketCalculator.divide(10, 0))
}
