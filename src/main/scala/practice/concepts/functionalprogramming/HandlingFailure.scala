package practice.concepts.functionalprogramming

import scala.util.{Failure, Random, Success, Try}

object HandlingFailure extends App {

  // create success and failure
  val aSuccess = Success[Int](3) // We no need to create it as Try.apply() do it
  val aFailure = Failure(new RuntimeException("Super Failure"))

  println(aSuccess)
  println(aFailure)

  def unsafeMethod(): String = throw new RuntimeException("No string for you..")

  // Try object via apply method
  val potentialFailure = Try(unsafeMethod())
  println(potentialFailure)

  // syntax sugar
  val anotherPotentialFailure = Try {
    // code that might throw
  }

  // utilities
  println(potentialFailure.isFailure)
  println(potentialFailure.isSuccess)

  // orElse
  def backupMethod(): String = "A valid result"

  val fallbackTry = Try(unsafeMethod()).orElse(Try(backupMethod()))
  println(fallbackTry)

  // Better design API
  def betterUnsafeMethod(): Try[String] = Failure(new RuntimeException)
  def betterBackupMethod(): Try[String] = Success("A valid result")

  val betterFallback = betterUnsafeMethod() orElse betterBackupMethod()
  println(betterFallback)

  // map, flatMap, filter
  println(aSuccess.map(_ * 10))
  println(aSuccess.flatMap(x => Success(x * 100)))
  println(aSuccess.filter(_ > 20))

  // for comprehension

  /**
   * Exercise
   *
   * Given Connection and HttpService, print the html page if connection is success else handle the exception
   */

  val hostName = "localhost"
  val port = "8080"
  def renderHTML(page: String) = println(page)

  class Connection {
    private def get(url: String): String = {
      val random = Random(System.nanoTime())
      if(random.nextBoolean()) "<html> Hello World </html>"
      else throw new RuntimeException("connection interrupted")
    }

    def getSafe(url: String): Try[String] = Try(get(url))
  }

  object HttpService {
    private def getConnection(host: String, port: String): Connection = {
      val random = Random(System.nanoTime())
      if (random.nextBoolean()) new Connection
      else throw new RuntimeException(s"port $port is already in use")
    }

    def getSafeConnection(host: String, port: String): Try[Connection] = Try(getConnection(host, port))
  }

  val possibleConnection = HttpService.getSafeConnection(hostName, port)
  val possibleHtml = possibleConnection.flatMap(connection => connection.getSafe("/home"))
  possibleHtml.foreach(renderHTML)

  // shorthand for above
  HttpService.getSafeConnection(hostName, port).flatMap(connection => connection.getSafe("/home"))
    .foreach(renderHTML)

  // for comprehension
  for {
    connection <- HttpService.getSafeConnection(hostName, port)
    html <- connection.getSafe("www.google.com")
  } yield renderHTML(html)

  // below is equivalent in java
  /*
      try {
        connection = HttpService.getConnection(host, port)
        try {
          page = connection.get("/home")
          renderHTML(page)
        } catch (some other exception) {

        }
      } catch (exception) {

      }
     */




}
