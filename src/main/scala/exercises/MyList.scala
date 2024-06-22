package exercises

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

  def map[B](transformer: MyTransformer[A, B]): MyList[B]
  def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B]
  def filter(predicate: MyPredicate[A]): MyList[A]

  def ++[B >: A](list: MyList[B]): MyList[B]
}

object MyEmptyList extends MyList[Nothing] {
  def head: Nothing = throw new NoSuchElementException
  def tail: MyList[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[B >: Nothing](element: B): MyList[B] = new MyNonEmptyList(element, MyEmptyList)
  def printElement: String = ""

  def map[B](transformer: MyTransformer[Nothing, B]): MyList[B] = MyEmptyList
  def flatMap[B](transformer: MyTransformer[Nothing, MyList[B]]): MyList[B] = MyEmptyList
  def filter(predicate: MyPredicate[Nothing]): MyList[Nothing] = MyEmptyList

  def ++[B >: Nothing](list: MyList[B]): MyList[B] = list
}

class MyNonEmptyList[+A](h: A, t: MyList[A]) extends MyList[A] {
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
  def filter(predicate: MyPredicate[A]): MyList[A] = {
    if (predicate.test(h)) new MyNonEmptyList(h, t.filter(predicate))
    else t.filter(predicate)
  }

  /**
   * [1,2,3].map(n * 2)
   * = new MyNonEmptyList(2, [2,3).map(n * 2)
   * = new MyNonEmptyList(2, new MyNonEmptyList(4, [3].map(n * 2)]
   * = new MyNonEmptyList(2, new MyNonEmptyList(4, new MyNonEmptyList(6, MyEmptyList.map(n * 2)
   */
  def map[B](transformer: MyTransformer[A, B]): MyList[B] = new MyNonEmptyList(transformer.transform(h), t.map(transformer))

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
  def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B] = transformer.transform(h) ++ t.flatMap(transformer)
}

trait MyPredicate[-T] {
  def test(t: T): Boolean
}

trait MyTransformer[-A, B] {
  def transform(a: A): B
}

object EvenPredicate extends MyPredicate[Int] {
  def test(t: Int): Boolean = t % 2 == 0
}

object StringToIntTransformer extends MyTransformer[String, Int] {
  def transform(a: String): Int = Integer.parseInt(a)
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

  println(listOfIntegers.map(new MyTransformer[Int, Int] {
    override def transform(a: Int): Int = a * 2
  }).toString)
  println(listOfIntegers.filter(new MyPredicate[Int] {
    override def test(a: Int): Boolean = a % 2 == 0
  }).toString)
  val anotherListOfIntegers = new MyNonEmptyList(4, new MyNonEmptyList(5, MyEmptyList))
  println((listOfIntegers ++ anotherListOfIntegers).toString)

  println(anotherListOfIntegers.flatMap(
    new MyTransformer[Int, MyList[Int]] {
      override def transform(a: Int): MyList[Int] = new MyNonEmptyList(a, new MyNonEmptyList(a + 1, MyEmptyList))
    }
  ).toString)
}
