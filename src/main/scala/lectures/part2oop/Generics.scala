package lectures.part2oop

object Generics extends App {

  // generic list
  class MyList[+A] {
    // use the type A
    def add[B >: A](element: B): MyList[B] = ???
    /*
      A = Cat
      B = Dog = Animal
     */
  }

  // generic map
  class MyMap[Key, Value]

  // trait also could be parametrized
  class MyMapTrait[Key, Value]

  val listOfIntegers = new MyList[Int]
  val listOfStrings = new MyList[String]

  // generic methods
  object MyList {
    def empty[A]: MyList[A] = ??? // ??? -> return nothing
  }
  //val emptyListOfIntegers = MyList.empty[Int]

  // variance problem
  class Animal
  class Dog extends Animal
  class Cat extends Animal
  // Q -> If Dog & Cat extends Animal, does List[Dog] / List[Cat] also extends List[Animal]?

  // 1. Yes. List[Cat] extends List[Animal] = COVARIANCE
  class CovariantList[+A]
  val animal = new Cat
  val animalList: CovariantList[Animal] = new CovariantList[Cat]
  // animalList.add(new Dog) ??? HARD QUESTION. => we return a list of Animal

  // 2. No. List[Cat] and List[Animal] are 2 different things = INVARIANCE
  class InvariantList[A]
  // val invariantAnimalList: InvariantList[Animal] = new InvariantList[Cat] // compile error
  val invariantAnimalList: InvariantList[Animal] = new InvariantList[Animal]

  // 3. Strongly No = CONTRAVARIANCE
  class ContravariantList[-A]
  val contraVariantCatList: ContravariantList[Cat] = new ContravariantList[Animal]
  // example
  class Trainer[-A]
  val trainer: Trainer[Cat] = new Trainer[Animal] // Trainer of an animal could be a trainer of Cat as well

  // bounded types
  // 1. upper bounded types
  class Cage[A <: Animal] (animal: A)// Cage can hold Animal or sub-type
  val cage = new Cage(new Dog)

  class Car
  // val carCage = new Cage(new Car) // compile error

  // 2. Lower bounded type
  class Cage2[A >: Dog](animal: A) // Cage can hold Animal or super-type

  val cage2 = new Cage2(new Cat)

  class Car2
  // val carCage = new Cage(new Car) // compile error
  
  // Exercise: Expand MyList to be generic
}
