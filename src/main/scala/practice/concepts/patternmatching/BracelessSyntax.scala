package practice.concepts.patternmatching

object BracelessSyntax {

  // if expression
  val anIfExpression = if(2 > 3) "Bigger" else "Smaller"

  // Java style
  val anIfExpression_v2 =
  if(2 > 3) {
    "Bigger"
  } else {
    "Smaller"
  }

    // compact
    val anIfExpression_v3 =
      if(2 > 3) "Bigger"
      else "Smaller"

    // scala 3
    val anIfExpression_v4 =
      if 2 > 3 then
        "Bigger"
      else
        "Smaller"

    val anIfExpression_v5 =
      if 2 > 3 then
        val result = "Bigger"
        result
      else
        val result = "Smaller"
        result

    // scala 3 one liner
    val anIfExpression_v6 = if 2 > 3 then "Bigger" else "Smaller"

    // for comprehension
    val aForComprehension = for {
      n <- List(1,2,3)
      s <- List("Black", "White")
    } yield s"$n$s"

    // scala 3
    val aForComprehension_v2 =
      for
        n <- List(1, 2, 3)
        s <- List("Black", "White")
      yield s"$n$s"

  // pattern matching
  val meaningOfLife = 42
  val aPatternMatch = meaningOfLife match {
    case 1 => "the one"
    case 2 => "double or nothing"
    case _ => "something else"
  }

  // scala 3
  val aPatternMatch_v2 =
    meaningOfLife match
      case 1 => "the one"
      case 2 => "double or nothing"
      case _ => "something else"

  // methods without braces
  def computeMeaningOfLife(arg: Int): Int = // significant indentation starts here - think of it like a phantom code block
    val partialResult = 40


    partialResult + 2 // still part of the method implementation!

  // class definition with significant indentation (same for traits, objects, enums etc)
  class Animal: // compiler expects the body of Animal
    def eat(): Unit =
      println("I'm eating")
    end eat

    def grow(): Unit =
      println("I'm growing")

    // 3000 more lines of code
  end Animal // if, match, for, methods, classes, traits, enums, objects

  // anonymous classes
  val aSpecialAnimal = new Animal:
    override def eat(): Unit = println("I'm special")

  // indentation = strictly larger indentation
  // 3 spaces + 2 tabs > 2 spaces + 2 tabs
  // 3 spaces + 2 tabs > 3 spaces + 1 tab
  // 3 tabs + 2 spaces ??? 2 tabs + 3 spaces

  def main(args: Array[String]): Unit = {
    println(anIfExpression)
    println(anIfExpression_v2)
    println(anIfExpression_v3)
    println(anIfExpression_v4)
    println(anIfExpression_v5)
    println(anIfExpression_v6)

    println(aForComprehension)
    println(aForComprehension_v2)

    println(aPatternMatch)
    println(aPatternMatch_v2)

    println(computeMeaningOfLife(20))

  }

}
