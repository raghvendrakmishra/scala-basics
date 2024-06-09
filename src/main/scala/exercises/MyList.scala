package exercises

import scala.reflect.ClassManifestFactory.{Any, Nothing, Null}

/**
 * Write your own immutable list
 */
abstract class MyList {
  /**
   * Methods:
   * head = first element of the list
   * tail = remainder of the list
   * isEmpty = is this list empty
   * add(int) => new list with this element added
   * toString => a string representation of the list
   */
  def head: Int
  def tail: MyList
  def isEmpty: Boolean
  def add(element: Int): MyList
  def printElement: String
  override def toString: String = s"[ $printElement ]"
}

object MyEmptyList extends MyList {
  def head: Int = throw new NoSuchElementException
  def tail: MyList = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add(element: Int): MyList = new MyNonEmptyList(element, MyEmptyList)
  def printElement: String = ""
}

class MyNonEmptyList(h: Int, t: MyList) extends MyList {
  def head: Int = h
  def tail: MyList = t
  def isEmpty: Boolean = false
  def add(element: Int): MyList = new MyNonEmptyList(element, t)
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
}