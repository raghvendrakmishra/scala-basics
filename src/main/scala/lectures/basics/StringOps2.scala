package lectures.basics

/**
 * This code demonstrates various string manipulation operations in Scala
 * including concatenation
 *  substring
 *  length
 *  trimming
 *  case conversion
 *  replacing
 *  splitting
 *  joining
 *  searching
 *  formatting
 *  regular expressions
 *  and interpolation
 */
@main def stringManipulation(): Unit =

  // Concatenation
  val concatenatedString = "Hello" + " " + "World"
  println("Concatenated String: " + concatenatedString)

  // Substring
  val originalString = "Scala Programming"
  val substring1 = originalString.substring(6)
  val substring2 = originalString.substring(6, 13)
  println("Substring 1: " + substring1)
  println("Substring 2: " + substring2)

  // Length
  println("Length of the string: " + originalString.length)

  // Trimming
  val stringWithWhitespace = "   Trim Me   "
  val trimmedString = stringWithWhitespace.trim
  println("Trimmed String: " + trimmedString)

  // Case Conversion
  val lowerCaseString = "scala"
  val upperCaseString = lowerCaseString.toUpperCase
  println("Uppercase String: " + upperCaseString)

  // Replacing
  val replacedString = originalString.replace("Programming", "Language")
  println("Replaced String: " + replacedString)

  // Splitting
  val fruitsString = "Apple,Orange,Mango"
  val fruitsArray = fruitsString.split(",")
  println("Fruits: " + fruitsArray.mkString(", "))

  // Joining/Concatenating
  val numbers = Seq(1, 2, 3, 4, 5)
  val joinedString = numbers.mkString("-")
  println("Joined String: " + joinedString)

  // Searching
  val searchKeyword = "Scala"
  println("Does the string contain 'Scala'? " + originalString.contains(searchKeyword))
  println("Index of 'Programming': " + originalString.indexOf("Programming"))
  println("Does the string start with 'Scala'? " + originalString.startsWith("Scala"))
  println("Does the string end with 'Scala'? " + originalString.endsWith("Scala"))

  // Formatting
  val formattedString = "Name: %s, Age: %d".format("John", 30)
  println("Formatted String: " + formattedString)

  // Regular Expressions
  val regex = """\d+""".r
  val numbersInString = "There are 10 apples and 20 oranges"
  val matchedNumbers = regex.findAllIn(numbersInString).toList
  println("Numbers in String: " + matchedNumbers.mkString(", "))

  // Interpolation
  val name = "Alice"
  val age = 25
  val interpolatedString = s"Name: $name, Age: $age"
  println("Interpolated String: " + interpolatedString)