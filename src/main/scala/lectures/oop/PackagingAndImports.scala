package lectures.oop

import playground.{Dummy1, JavaPlayGround, Dummy1 as Mock}

import java.util.Date // Best practice to import only required classes like this
// import playground._ // import all from package, Use it only if you need it

import java.util.Date
import java.sql.{Date => SqlDate}

object PackagingAndImports extends App {

  // Package members are accessible by their simple name
  val writer = new Writer("Raghu", "M", 2000)

  // Importing package
  JavaPlayGround.main(null)

  // Packages are in hierarchy

  // Package objects
  sayHello()
  println(SPEED_OF_LIGHT)

  // Imports
  val dummy1 = playground.Dummy1
  val dummy2 = Mock // use alias if importing same name class from different packages

  val date = new Date
  val sqlDate1 = new java.sql.Date(2024, 6, 22) // Use fully qualified name
  val sqlDate2 = new SqlDate(2024, 6, 22) // use alias

  // Default imports
  /**
   * In Scala, a number of packages and their classes are imported by default. This means you can use them without
   * explicitly importing them in your code. Here are the default imports in Scala:
   *
   * 1. **`java.lang`** package:
   *     - `Object`
   *     - `Class`
   *     - `System`
   *     - `String`
   *     - `Throwable`
   *     - `Exception`
   *     - `RuntimeException`
   *     - `Error`
   *     - `Runtime`
   *     - and others
   *
   * 2. **`scala`** package:
   *     - `Any`
   *     - `AnyRef`
   *     - `AnyVal`
   *     - `Unit`
   *     - `Boolean`
   *     - `Byte`
   *     - `Short`
   *     - `Int`
   *     - `Long`
   *     - `Float`
   *     - `Double`
   *     - `Char`
   *     - `String`
   *     - `Symbol`
   *     - `Option`
   *     - `Some`
   *     - `None`
   *     - `List`
   *     - `Nil`
   *     - `Seq`
   *     - `IndexedSeq`
   *     - `Iterable`
   *     - `Iterator`
   *     - `BigInt`
   *     - `BigDecimal`
   *     - `Either`
   *     - `Left`
   *     - `Right`
   *     - `PartialFunction`
   *     - `Function`
   *     - `Tuple`
   *     - `Product`
   *     - `Range`
   *     - `Math`
   *     - `Equiv`
   *     - `Integral`
   *     - `Numeric`
   *     - `Ordering`
   *     - `Ordered`
   *     - `Manifest`
   *     - `ClassManifest`
   *     - `reflect.ManifestFactory`
   *     - `Specializable`
   *     - and others
   *
   * 3. **`scala.Predef`** object:
   *     - `println`
   *     - `print`
   *     - `printf`
   *     - `assert`
   *     - `require`
   *     - `identity`
   *     - `implicitly`
   *     - `locally`
   *     - `???` (a method to mark unimplemented code)
   *     - `StringAdd`
   *     - `ArrowAssoc`
   *     - `Ensuring`
   *     - `augmentString`
   *     - `ArrayOps`
   *     - and others
   *
   * Additionally, the following package is imported automatically when using the Scala REPL (Read-Eval-Print Loop):
   *
   * 4. **`scala.reflect`** package in the REPL (not in standard scripts):
   *     - `ClassTag`
   *     - `TypeTag`
   *     - and others
   *
   * These default imports provide a wide range of fundamental classes and methods that are commonly used in Scala
   * programming. This allows developers to write concise and expressive code without needing to include numerous import
   * statements.
   */


}
