package lectures.oop

object AnonymousClasses extends App {

  abstract class Animal {
    def eat: Unit
  }

  // Anonymous class
  val hungryAnimal = new Animal {
    override def eat: Unit = println("Animal is eating...")
  }
  /**
   * Equivalent with:
   * class AnonymousClasses$$anon$1 extends Animal {
   *  override def eat: Unit = println("Animal is eating...")
   * }
   * val hungryAnimal = new AnonymousClasses$$anon$1
   */
  hungryAnimal.eat
  println(hungryAnimal.getClass)

  class Person(name: String) {
    def sayHi = s"$name is saying Hi..."
  }
  val swpanil = new Person("Swapnil") {
    override def sayHi = "Swapnil is saying Hello..."
  }

  /**
   * Exercise:
   *  1. Generic trait MyPredicate[-T] // -A => contra-variant
   *    test(T): Boolean
   *  2. Generic trait MyTransformer[-A, B]
   *    transform(A): B
   *  3. MyList
   *    map(MyTransformer): MyList
   *    filter(MyPredicate): MyList
   *    flatMap(MyTransformer from A to MyList[B]): MyList[B]
   *  4. EvenPredicate extends MyPredicate[Int]
   *  5. StringToIntTransformer extends MyTransformer[String, Int]
   *
   *  Examples:
   *  [1,2,3].map(n * 2) = [2,4,6]
   *  [1,2,3,4].filter(n % 2) = [2,4]
   *  [1,2,3].flatMap(n => (n, n + 1 ) = [1, 2, 2, 3, 3, 4]
   */
}
