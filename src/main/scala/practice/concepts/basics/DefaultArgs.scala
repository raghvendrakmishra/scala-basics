package practice.concepts.basics

object DefaultArgs extends App {

  // If we do not pass method parameter then default value will be automatically used
  def factorial(n: Int, accumulator: Int = 1 /* default value */): Int = {
    if(n <= 0) accumulator
    else factorial(n - 1, n * accumulator)
  }

  val fact10 = factorial(10, 2) // pro

  // If we do not pass method parameter then default value will be automatically used
  def savePicture(format: String = "jpg" /* default value */, width: Int = 1920 /* default value */, 
                  height: Int = 1080 /* default value */): Unit = println("Saving picture")
  savePicture()
  savePicture("PNG")
  savePicture("GIF", 3000)
  savePicture(height = 4000)

  /*
      1. pass in every leading argument
      2. name the arguments
     */

  savePicture(height = 600, width = 800, format = "bmp")
}
