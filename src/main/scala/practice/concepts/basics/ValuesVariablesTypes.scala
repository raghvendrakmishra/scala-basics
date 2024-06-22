package practice.concepts.basics

object ValuesVariablesTypes extends App {
  // numbers
  val x: Int = 10
  println(x)
  //x = 20; // val are immutable
  val y = 10 // Mentioning val type is optional
  println(y)

  val sString: String = "hello"
  val anotherString = "Raghu"

  val aBoolean: Boolean = true;
  val aChar: Char = 'a'
  val anInt: Int = 100
  val aShort: Short = 123
  val aLong: Long = 348759347L
  val aFloat: Float = 4534.454F
  val aDouble: Double = 34434.4435
  
  val aBigInt: BigInt = BigInt("22222222222222222288888888888888")
  val aBigDecimal: BigDecimal = BigDecimal("2.2222222222222222288888888888888")

  // Variables
  var aVariable = 5 // var can be reassigned
  aVariable = 10 // side effects 
}
