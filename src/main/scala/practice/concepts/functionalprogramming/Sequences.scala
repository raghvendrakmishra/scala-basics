package practice.concepts.functionalprogramming

import scala.+:
import scala.util.Random

object Sequences extends App {

  // Seq
  private val aSequence = Seq(1,3,2,4)
  println(aSequence) // List(1, 3, 2, 4)
  println(aSequence.reverse) // List(4, 2, 3, 1)
  println(aSequence(3)) // 4 -> aSequence.get(3)
  println(aSequence ++ Seq(7,5,6)) // List(1, 3, 2, 4, 7, 5, 6)
  println(aSequence.sorted) // List(1, 2, 3, 4)

  // Ranges
  /**
   * 1.	Range Inclusion:
   * •	(1 to 10): This creates a range from 1 to 10, inclusive of 10.
   * •	(1 until 10): This creates a range from 1 to 9, exclusive of 10.
   * 2.	Output:
   * •	(1 to 10).foreach(x => println(x)) prints numbers from 1 to 10.
   * •	(1 until 10).foreach(x => println(x)) prints numbers from 1 to 9.
   */
  val aRange: Seq[Int] = 1 to 10
 // val aRange: Seq[Int] = 1 until 10
  aRange.foreach(n => print(n + ", ")) // 1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
  println

  (1 to 10).foreach(x => print("Hello, ")) // Hello, Hello, Hello, Hello, Hello, Hello, Hello, Hello, Hello, Hello,
  println

  val aRange2: Seq[Int] = 1 until 10
  // val aRange: Seq[Int] = 1 until 10
  aRange2.foreach(n => print(n + ", ")) // 1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
  println

  (1 until 10).foreach(x => print("Hello, ")) // Hello, Hello, Hello, Hello, Hello, Hello, Hello, Hello, Hello, Hello,
  println

//  // lists
  val aList = List(1,2,3)
// prepend ::, +:
// append :+
// : will be always in the side of the list
  val prepended1 = 42 +: aList :+ 89 // List(42, 1, 2, 3, 89)
  val prepended2 = (42 :: aList) :+ 89 // List(42, 1, 2, 3, 89)
  println(prepended1)
  println(println(prepended2))

  val apples5 = List.fill(5)("apple")
  println(apples5)
  println(aList.mkString("-|-"))

  // arrays
  val numbers = Array(1,2,3,4)
  val threeElements = Array.ofDim[String](3)
  numbers.foreach(x => print(x + ", "))
  println
  Array.ofDim[Int](3).foreach(x => print(x + ", "))
  println
  Array.ofDim[Double](3).foreach(x => print(x + ", "))
  println
  Array.ofDim[Boolean](3).foreach(x => print(x.toString + ", "))
  println
  threeElements.foreach(x => print(x + ", "))
  println

  // mutation
  numbers(2) = 0  // syntax sugar for numbers.update(2, 0)
  // numbers.update(2, 0)
  println(numbers.mkString(", "))

  // arrays and seq
  val numbersSeq: Seq[Int] = numbers  // implicit conversion
  println(numbersSeq)

  // vectors
  val vector: Vector[Int] = Vector(1,2,3)
  println(vector)

  // vectors vs lists
  /**
   * Here are the key differences between Scala's `Vector` and `List`:
   *
   * - **Immutability**:
   *   - Both `Vector` and `List` are immutable.
   *
   * - **Structure**:
   *   - **Vector**: Indexed sequence; elements stored in a tree structure.
   *   - **List**: Linked list; elements stored in a chain of nodes.
   *
   * - **Performance**:
   *   - **Vector**: Fast random access and updates (O(log32 n)).
   *   - **List**: Fast prepend (O(1)) and slow random access (O(n)).
   *
   * - **Use Cases**:
   *   - **Vector**: When you need efficient random access.
   *   - **List**: When you frequently add elements to the front.
   *
   * - **Memory**:
   *   - **Vector**: Generally higher memory overhead.
   *   - **List**: Lower memory overhead, but less efficient for large sizes.
   *
   * - **Traversal**:
   *   - **Vector**: Efficient for large collections.
   *   - **List**: Efficient for smaller collections or sequential access.
   */

  val maxRuns = 1000
  val maxCapacity = 1000000

  def getWriteTime(collection: Seq[Int]): Double = {
    val r = new Random
    val times = for {
      it <- 1 to maxRuns
    } yield {
      val currentTime = System.nanoTime()
      collection.updated(r.nextInt(maxCapacity), r.nextInt())
      System.nanoTime() - currentTime
    }

    times.sum * 1.0 / maxRuns
  }

  val numbersList = (1 to maxCapacity).toList                                                             
  val numbersVector = (1 to maxCapacity).toVector

  // keeps reference to tail
  // updating an element in the middle takes long
  println(getWriteTime(numbersList))
  // depth of the tree is small
  // needs to replace an entire 32-element chunk
  println(getWriteTime(numbersVector))
  
}
