package lectures.oop

import lectures.oop.Enums.Permissions.READ

/**
 * Enums in Scala, introduced in Scala 3, provide a way to define a finite set of named values. They are similar to
 * enums in other programming languages like Java but come with some Scala-specific features. Here are the key points
 * about enums in Scala:
 *
 * 1. **Definition Syntax**: Enums are defined using the `enum` keyword.
 *
 * 2. **Named Values**: Enums consist of a set of named values, which are instances of the enum type.
 *
 * 3. **Parameterized Enums**: Enum values can have parameters, allowing you to associate data with each value.
 *
 * 4. **Companion Object**: Each enum automatically comes with a companion object that contains utility methods like
 * `values` and `valueOf`.
 *
 * 5. **Pattern Matching**: Enums work seamlessly with Scala's pattern matching, enabling concise and readable code.
 *
 * 6. **Extensible**: Enum values can be extended with additional methods and fields.
 *
 * 7. **Interoperability**: Scala enums can be used with Java code and vice versa, making them useful in mixed-language
 * projects.
 *
 * 8. **Typed Enum Values**: Enum values can have different types, and you can define custom types for enum values.
 *
 * 9. **Sealed**: Enums are implicitly sealed, meaning all possible values are known at compile-time, which enhances
 * exhaustiveness checking in pattern matching.
 *
 * ### Example
 *
 * ```scala
 * enum Color:
 * case Red, Green, Blue
 *
 * enum Planet(mass: Double, radius: Double):
 * case Mercury extends Planet(3.303e+23, 2.4397e6)
 * case Venus extends Planet(4.869e+24, 6.0518e6)
 * case Earth extends Planet(5.976e+24, 6.37814e6)
 * case Mars extends Planet(6.421e+23, 3.3972e6)
 *
 * object Planet:
 * def printMass(planet: Planet): Unit =
 * println(s"The mass of ${planet} is ${planet.mass}")
 *
 * @main def runExample() =
 *       val earth = Planet.Earth
 *       Planet.printMass(earth)
 *       ```
 *
 *       ### Key Points Recap
 *
 *       - **Definition Syntax**: Defined with `enum` keyword.
 *       - **Named Values**: Consist of a set of named instances.
 *       - **Parameterized Enums**: Can associate data with each enum value.
 *       - **Companion Object**: Automatically generated with utility methods.
 *       - **Pattern Matching**: Seamless integration with pattern matching.
 *       - **Extensible**: Can extend values with methods and fields.
 *       - **Interoperability**: Usable in mixed Scala-Java projects.
 *       - **Typed Enum Values**: Enum values can have different or custom types.
 *       - **Sealed**: Enums are implicitly sealed for exhaustive pattern matching.
 *
 *       These features make enums in Scala a powerful and flexible way to work with a fixed set of values, providing
 *       both type safety and expressive power.
 */
object Enums {

  enum Permissions {
    case READ, WRITE, EXECUTE, NONE

    // Add fields / methods
    def openDocument(): Unit = {
      if(this == READ) println("Opening document...")
      else println("Reading not allowed")
    }
  }

  val somePermissions: Permissions = Permissions.READ

  // constructor arguments
  enum PermissionWithBits(bits: Int) {
    case READ extends PermissionWithBits(4) // 100
    case WRITE extends PermissionWithBits(2) // 010
    case EXECUTE extends PermissionWithBits(1) // 001
    case NONE extends PermissionWithBits(0) // 000
  }

  object PermissionWithBits {
    def fromBits(bits: Int): PermissionWithBits = PermissionWithBits.NONE
  }

  // Standard API for enum
  val somePermissionsOrdinal = somePermissions.ordinal // index of defined enums, starting with zero (0)
  val allPermissions = PermissionWithBits.values // Arrays of all values of enum
  val readPermission = Permissions.valueOf("READ") // Permissions.READ, cannot be created in case of parameterized constructor


  def main(args: Array[String]): Unit = {
    somePermissions.openDocument()
    println(somePermissionsOrdinal)
  }

}
