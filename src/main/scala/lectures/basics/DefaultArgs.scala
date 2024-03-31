package lectures.basics

object DefaultArgs extends App {

  def factorial(n: Int, accumulator: Int = 1): Int = {
    if(n <= 0) accumulator
    else factorial(n - 1, n * accumulator)
  }

  val fact10 = factorial(10, 2) // pro

  def savePicture(format: String = "jpg", width: Int = 1920, height: Int = 1080): Unit = println("Saving picture")
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
