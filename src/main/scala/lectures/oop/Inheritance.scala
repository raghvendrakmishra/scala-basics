package lectures.oop

/**
 * What are access modifiers?
 * Declaring constructors in Scala
 * Method overloading in Scala?
 * Variable overloading in Scala?
 * Overloading vs overriding in Scala?
 * Invoking super.members
 * Preventing override
 */
object Inheritance extends App {

  //  Single class inheritance
  class Animal {
    // Access modifiers
    // private: accessible in the class
    // protected: accessible in same class + sub class
    // default = modifier = public
    def eat = println("eating...")

    val creatureType = "Wild"
  }

  class Cat extends Animal {
    def crunch = {
      eat
      println("crunch crunch")
    }
  }

  val cat = new Cat
  cat.eat
  cat.crunch

  // Constructors
  class Person(name: String, age: Int) {
    // one argument constructor
    def this(name: String) = this(name, 0)
  }
  class Adult(name: String, age: Int, idCard: String) extends Person(name, age)

  // overriding
  class Dog(override val creatureType: String) extends Animal {
    // override val creatureType: String = "domestic" // could be overridden like this or passing parameter in constructor

    override def eat: Unit = {
      super.eat
      println("Dog is eating...")
    }
  }

  // val dog = new Dog
  val dog = new Dog("Indian")
  dog.eat
  println(dog.creatureType)

  // type substitution (polymorphism)
  val unknownAnimal: Animal = new Dog("UnknownDog")
  unknownAnimal.eat

  // preventing override
  // 1 - Use keyword final on member
  // 2 - Use keyword final on the class
  // 3 - seal the class = extend classes in THIS FILE, prevent extension in other files


}
