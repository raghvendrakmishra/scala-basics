package practice.concepts.functionalprogramming

object MapFlatMapFilterFor extends App {

  val list = List(1,2,3)
  println(list)
  println(list.head)
  println(list.tail)

  // map
  println(list.map(_ + 1))
  println(list.map(_ + " is a number"))

  // filter
  println(list.filter(_ % 2 == 0))

  // flatMap
  val toPair = (x: Int) => List(x, x + 1)
  println(list.flatMap(toPair))

  /*
  Exercise:
  1. Print all combinations between 2 lists

   */

  // 1. Print all combinations between 2 lists
  private val chars = List('a', 'b', 'c', 'd')
  val numbers = List(1, 2, 3, 4)
  // output: List("a1", "a2", "a3", ..... "d4")
  private val output = numbers.flatMap(n => chars.map(c => c + "" + n))
  private val output2 = chars.flatMap(c => numbers.map(n => c + "" + n))
  println(output)
  println(output2)

  private val colors = List("Green", "Yellow")
  private val output3 = chars.flatMap(c => numbers.flatMap(n => colors.map(color => "" + c + n + "-" + color)))
  println(output3)

  // forEach
  list.foreach(println)

  private val output4 = chars.flatMap(c => numbers.filter(n => n % 2 == 0).flatMap(n => colors.filter(color =>
    color.equals("Yellow")).map(color => "" + c + n + "-" + color)))
  println(output4)
  // for-comprehension
  private val forComprehension = for {
    n <- numbers
    c <- chars
    color <- colors
  } yield {
    "" + c + n + "-" + color
  }

  // for-comprehension with filter
  private val forComprehension2 = for {
    n <- numbers if n % 2 == 0
    c <- chars
    color <- colors if color.equals("Yellow")
  } yield {
    "" + c + n + "-" + color
  }

  println(forComprehension2)

  // for-comprehension: print all the elements
  for {
    n <- numbers
  } println(n)

  // syntax overload
  list.map { x =>
    x * 2
  }.foreach { x =>
    print(x + ", ")
  }

  /*
  Exercise

  1. MyList supports for-comprehension?
      - map(f: A => B): MyList[B]
      - filter(p: A => Boolean): MyList[A]
      - flatMap(f: A => MyList[B]): MyList[B]
  2. Implement a small collection of at most ONE element - MayBe[+T]
      - map, flatMap, filter

   */


}
