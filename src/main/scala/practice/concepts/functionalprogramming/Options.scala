package practice.concepts.functionalprogramming

import scala.util.Random

/**
 * - **Purpose**: Represents optional values; can be `Some(value)` or `None`.
 * - **Avoids Null**: Helps prevent `NullPointerException`.
 * - **Pattern Matching**: Supports pattern matching for extraction.
 * - **Methods**: Provides useful methods like `getOrElse`, `map`, `flatMap`, `filter`.
 * - **Monad**: Used in for-comprehensions for cleaner code.
 * - **Type Safety**: Ensures type safety by handling absence of value.
 * - **Common Uses**: Often used for safe retrieval from collections or function results.
 */
object Options extends App {

  val myFirstOption: Option[Int] = Some(10)
  val noOption: Option[Int] = None

  println(myFirstOption) // Some(10)
  println(noOption) // None

  // Work with unsafe API
  val unsafeMethod: Option[String] = null
  // val result = Some(unsafeMethod) // Do not serve the purpose of Option
  // val result = Some(null) // Do not serve the purpose of Option
  val result = Option(unsafeMethod) // Some or None, depends on whether there is value or not
  println(result) // None

  // chained methods
  def backupMethod(): String = "A valid result"
  val chainedResult = Option(unsafeMethod).orElse(Option(backupMethod())) // Some(A valid result)
  println(chainedResult)

  // Design unsafe API
  def betterUnsafeMethod(): Option[String] = None
  def betterBackupMethod(): Option[String] = Some("A valid result")

  val betterChainedResult = betterUnsafeMethod() orElse betterBackupMethod()
  println(betterChainedResult) // Some(A valid result)

  // Functions on Option
  println(myFirstOption.isEmpty) // false
  println(myFirstOption.get) // 10 => do not use .get like this
  /**
   * Exception in thread "main" java.lang.ExceptionInInitializerError
   * at practice.concepts.functionalprogramming.Options.main(Options.scala)
   * Caused by: java.util.NoSuchElementException: None.get
   */
  // println(noOption.get)

  // map, flatMap, filter
  println(myFirstOption.map(_ * 2)) // Some(20)
  println(myFirstOption.filter( _ > 100)) // None
  println(myFirstOption.flatMap(x => Option(x * 100))) // Some(1000)

  /**
   * Exercise
   *
   * - config => Map[String, String] : config fetch from somewhere else. May or may not have value
   * - Connection
   *     - connect
   *     - try to establish a connection, if so - invoke the connect method
   */
  val config: Map[String, String] = Map(
    "host" -> "176.45.36.1",
    "port" -> "80"
  )

  class Connection {
    def connect = "connected"
  }

  object Connection {
    val random = Random(System.nanoTime())

    def apply(host: String, port: String): Option[Connection] = {
      if(random.nextBoolean()) Some(new Connection)
      else None
    }
  }

  val host = config.get("host")
  val port = config.get("port")
  /**
   * if(h != null) {
   *    if(p != null) {
   *      return Connection(h, p)
   *    }
   *   } else {
   *    return null
   *   }
   * }
   */
  val connection: Option[Connection] = host.flatMap(h => port.flatMap(p => Connection.apply(h, p)))
  /**
   * if(c != null)
   *    return c.connect
   * else
   *    return null
   */
  val connectionStatus = connection.map(c => c.connect)
  // if(connectionStatus == null) println(None) else println(Some(c.connect))
  println(connectionStatus)
  // if(status != null) println(status)
  connectionStatus.foreach(println)

  // chained call
  config.get("host")
    .flatMap(host => config.get("port")
      .flatMap(port => Connection.apply(host, port))
      .map(connection => connection.connect))
    .foreach(println)

  // for comprehensive
  val forConnectionStatus = for {
    host <- config.get("host")
    port <- config.get("port")
    connection <- Connection.apply(host, port)
  } yield connection.connect
  forConnectionStatus.foreach(println)
}
