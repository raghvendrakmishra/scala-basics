package practice.exercises

import scala.annotation.targetName
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

  // higher-order function
  def map[B](transformer: A => B): MyList[B]
  def flatMap[B](transformer: A => MyList[B]): MyList[B]
  def filter(predicate: A => Boolean): MyList[A]

  def ++[B >: A](list: MyList[B]): MyList[B]

  // Higher order functions
  def forEach(f: A => Unit): Unit
  def sort(compare: (A, A) => Int): MyList[A]
  def zipWith[B, C] (list: MyList[B], zip: (A, B) => C): MyList[C]
  def fold[B] (start: B) (operator: (B, A) => B): B
}

case object MyEmptyList extends MyList[Nothing] {
  def head: Nothing = throw new NoSuchElementException
  def tail: MyList[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[B >: Nothing](element: B): MyList[B] = new MyNonEmptyList(element, MyEmptyList)
  def printElement: String = ""

  def map[B](transformer: Nothing => B): MyList[B] = MyEmptyList
  def flatMap[B](transformer: Nothing => MyList[B]): MyList[B] = MyEmptyList
  def filter(predicate: Nothing => Boolean): MyList[Nothing] = MyEmptyList

  def ++[B >: Nothing](list: MyList[B]): MyList[B] = list

  // Higher order functions
  def forEach(f: Nothing => Unit): Unit = ()
  def sort(compare: (Nothing, Nothing) => Int) = MyEmptyList
  def zipWith[B, C] (list: MyList[B], zip: (Nothing, B) => C): MyList[C] = {
    if(!list.isEmpty) throw new RuntimeException("The list do not have same length")
    else MyEmptyList
  }
  def fold[B] (start: B) (operator: (B, Nothing) => B): B = start
}

case class MyNonEmptyList[+A](h: A, t: MyList[A]) extends MyList[A] {
  def head: A = h
  def tail: MyList[A] = t
  def isEmpty: Boolean = false
  def add[B >: A](element: B): MyList[B] = new MyNonEmptyList(element, t)
  def printElement: String = if(t.isEmpty) head.toString else s"$head, ${t.printElement}"

  /**
   * [1,2,3].filter(n % 2 == 0)
   * = [2,3].filter(n % 2 == 0)
   * = new MyNonEmptyList(2, [3].filter(n % 2 == 0))
   * = new MyNonEmptyList(2, MyEmptyList.filter(n % 2 == 0))
   *  = new MyNonEmptyList(2, MyEmptyList)
   */
  def filter(predicate: A => Boolean): MyList[A] = {
    if (predicate(h)) new MyNonEmptyList(h, t.filter(predicate))
    else t.filter(predicate)
  }

  /**
   * [1,2,3].map(n * 2)
   * = new MyNonEmptyList(2, [2,3).map(n * 2)
   * = new MyNonEmptyList(2, new MyNonEmptyList(4, [3].map(n * 2)]
   * = new MyNonEmptyList(2, new MyNonEmptyList(4, new MyNonEmptyList(6, MyEmptyList.map(n * 2)
   */
  def map[B](transformer: A => B): MyList[B] = new MyNonEmptyList(transformer(h), t.map(transformer))

  /**
   * [1,2] ++ [3,4,5]
   * = new MyNonEmptyList(1, [2] ++ [3,4,5])
   * = new MyNonEmptyList(1, new MyNonEmptyList(2, Empty ++ [3,4,5])
   * = new MyNonEmptyList(1, new MyNonEmptyList(2, [3,4,5])
   */
  def ++[B >: A](list: MyList[B]): MyList[B] = new MyNonEmptyList(h, t ++ list)
  /**
   * [1,2].flatMap(n => (n, n+1))
   *  = [1,2] ++ [2].flatMap(n => (n, n+1))
   *  = [1,2] ++ [2,3] ++ MyEmptyList.flatMap(n => (n, n+1))
   *  = [1,2) ++ [2,3]
   */
  def flatMap[B](transformer: A => MyList[B]): MyList[B] = transformer(h) ++ t.flatMap(transformer)

  // Higher order functions
  def forEach(f: A => Unit): Unit = {
    f(h)
    t.forEach(f)
  }

  def sort(compare: (A, A) => Int): MyList[A] = {
    def insert(x: A, sortedList: MyList[A]): MyList[A] = {
      if(sortedList.isEmpty) new MyNonEmptyList(x, MyEmptyList)
      else if(compare(x, sortedList.head) <= 0) new MyNonEmptyList(x, sortedList)
      else new MyNonEmptyList(sortedList.head, insert(x, sortedList.tail))
    }

    val sortedTail = t.sort(compare)
    insert(h, sortedTail)
  }

  def zipWith[B, C] (list: MyList[B], zip: (A, B) => C): MyList[C] = {
    if (list.isEmpty) throw new RuntimeException("The list do not have same length")
    else new MyNonEmptyList[C](zip(h, list.head), t.zipWith(list.tail, zip))
  }

  /**
   * [1,2,3].fold(0)(+) =
   * [2,3].fold(1)(+) =
   * [3].fold(3)(+) =
   * [].fold(6)(+) =
   * 6
   */
  def fold[B] (start: B) (operator: (B, A) => B): B = {
    t.fold(operator(start, h)) (operator)
  }
}

// Testing the list
object ListTest extends App {
  private val list = new MyNonEmptyList(1, new MyNonEmptyList(2, new MyNonEmptyList(3, MyEmptyList)))
  println(list.head) // 1
  println(list.tail.tail.head) // 3
  println(list.add(4).head) // 4
  println(list.isEmpty) // false
  println(list.toString) // 1,2,3
  // val listOfIntegers: MyList[Int] = MyEmptyList
  // val listOfStrings: MyList[String] = MyEmptyList
  private val listOfIntegers: MyList[Int] = MyNonEmptyList(4, new MyNonEmptyList[Int](5, new MyNonEmptyList[Int](6, MyEmptyList)))
  private val clonseListOfIntegers: MyList[Int] = MyNonEmptyList(4, new MyNonEmptyList[Int](5, new MyNonEmptyList[Int](6, MyEmptyList)))

  private val listOfStrings: MyList[String] = MyNonEmptyList("Raghu", new MyNonEmptyList[String]("Gaurav", new MyNonEmptyList[String]("Murali", MyEmptyList)))
  println(listOfIntegers.toString)
  println(listOfStrings.toString)

  println(listOfIntegers.map(_ * 2).toString)
  println(listOfIntegers.filter(_ % 2 == 0).toString)
  private val anotherListOfIntegers = new MyNonEmptyList(4, new MyNonEmptyList(5, MyEmptyList))
  println((listOfIntegers ++ anotherListOfIntegers).toString)

  println(anotherListOfIntegers
    .flatMap(element => new MyNonEmptyList(element, new MyNonEmptyList(element + 1, MyEmptyList))).toString)
  // _ notation don't work here as we have used it 2 times above. Each _ stands for different parameter

  println(listOfIntegers == clonseListOfIntegers) // true, since case object got OOTB implementation of equals and hashcode

  listOfIntegers.forEach(element => print(element + " "))
  println
  println(listOfIntegers.sort((x, y) => y - x))
  println(listOfIntegers.zipWith(listOfStrings, _ + "-" + _))
  println(listOfIntegers.fold(0)((b, a) => b + a))
}
