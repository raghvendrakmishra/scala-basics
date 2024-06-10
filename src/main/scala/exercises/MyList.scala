package exercises

import scala.reflect.ClassManifestFactory.{Any, Nothing, Null}

/**
 * Write your own immutable list
 */
abstract class MyList[+A] {
  /**
   * Methods:
   * head = first element of the list
   * tail = remainder of the list
   * isEmpty = is this list empty
   * add(int) => new list with this element added
   * toString => a string representation of the list
   */
  def head: A
  def tail: MyList[A]
  def isEmpty: Boolean
  def add[B >: A](element: B): MyList[B]
  def printElement: String
  override def toString: String = s"[ $printElement ]"
}

object MyEmptyList extends MyList[Nothing] {
  def head: Nothing = throw new NoSuchElementException
  def tail: MyList[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[B >: Nothing](element: B): MyList[B] = new MyNonEmptyList(element, MyEmptyList)
  def printElement: String = ""
}

class MyNonEmptyList[+A](h: A, t: MyList[A]) extends MyList[A] {
  def head: A = h
  def tail: MyList[A] = t
  def isEmpty: Boolean = false
  def add[B >: A](element: B): MyList[B] = new MyNonEmptyList(element, t)
  def printElement: String = if(t.isEmpty) head.toString else s"$head, ${t.printElement}"
}

// Testing the list
object ListTest extends App {
  val list = new MyNonEmptyList(1, new MyNonEmptyList(2, new MyNonEmptyList(3, MyEmptyList)))
  println(list.head) // 1
  println(list.tail.tail.head) // 3
  println(list.add(4).head) // 4
  println(list.isEmpty) // false
  println(list.toString) // 1,2,3
  // val listOfIntegers: MyList[Int] = MyEmptyList
  // val listOfStrings: MyList[String] = MyEmptyList
  val listOfIntegers: MyList[Int] = MyNonEmptyList(4, new MyNonEmptyList[Int](5, new MyNonEmptyList[Int](6, MyEmptyList)))
  val listOfStrings: MyList[String] = MyNonEmptyList("Raghu", new MyNonEmptyList[String]("Gaurav", new MyNonEmptyList[String]("Murali", MyEmptyList)))
  println(listOfIntegers.toString)
  println(listOfStrings.toString)

}