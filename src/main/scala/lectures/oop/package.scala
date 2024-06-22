package lectures

/**
 * Package objects in Scala are a feature that allows you to define methods, variables, constants, and type aliases
 * that are accessible from all parts of the package. Here are some important points about Scala package objects:
 *
 * 1. **Purpose**:
 *    - Package objects are used to hold shared code and definitions that are common to all classes and objects in a
 *    package.
 *
 * 2. **Location and Naming**:
 *    - A package object is defined in a file named `package.scala` within the relevant package directory.
 *    - The package object is declared with the keyword `package object` followed by the package name.
 *
 * 3. **Content**:
 *    - You can define methods, variables, constants, type aliases, and implicit conversions within a package object.
 *    - These definitions are available to all classes and objects within the same package.
 *
 * 4. **Access Modifiers**:
 *    - Members of a package object can have access modifiers (e.g., `private`, `protected`) to control their visibility
 *    within the package.
 *
 * 5. **Inheritance**:
 *    - A package object can extend classes or traits, allowing it to inherit their methods and fields.
 *
 * 6. **Usage Example**:
 * ```scala
 * package object mypackage {
 * val constantValue = 42
 * def sharedMethod(x: Int): Int = x * constantValue
 * type AliasType = List[String]
 * }
 * ```
 *
 * 7. **Initialization**:
 *    - The code inside a package object is executed when the package object is first accessed.
 *
 * 8. **Restrictions**:
 *    - A package can have only one package object.
 *    - The package object cannot be nested within other packages or classes.
 *
 * 9. **Scoping**:
 *    - Members of a package object can be accessed without needing to import them explicitly within the same package.
 *
 * 10. **Use Cases**:
 *     - Common constants and utility methods shared across the package.
 *     - Implicit conversions and values that should be universally available within the package.
 *     - Type aliases to simplify complex type references.
 *
 * By encapsulating shared definitions in a package object, you can maintain cleaner and more modular code, facilitating
 * easier maintenance and readability.
 */
package object oop {

  val SPEED_OF_LIGHT = 299792458

  def sayHello(): Unit = println("Hello, Mayur")

}
