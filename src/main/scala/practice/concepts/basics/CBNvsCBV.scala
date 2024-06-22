package practice.concepts.basics

/**
 * Call by name vs call by value
 */
object CBNvsCBV extends App {

  private def calledByValue(x: Long): Unit = {
    println("call by value " + x)
    println("call by value " + x)
  }

  private def calledByName(x: => Long): Unit = {
    println("call by name " + x)
    println("call by name " + x)
  }

  calledByValue(System.nanoTime())
  calledByName(System.nanoTime())

  private def infinite(): Int = 1 + infinite()
  private def printFirst(x: Int, y: => Int) = x

  // println(printFirst(infinite(), 34)) // stackoverflow, as first value evaluated
  println(printFirst(34, infinite())) // dont' crash as second value infinite() is never invoked, hence not executed
}
