package practice.concepts.functionalprogramming

import scala.annotation.tailrec

object TuplesAndMaps extends App {

  // tuples = finite ordered "lists"
  /**
   * Here are some important points about Tuples in Scala:
   *
   * - **Definition**: Tuples are immutable collections of fixed-size, heterogeneous elements.
   * - **Creation**: Can be created using parentheses, e.g., `(1, "Scala", true)`.
   * - **Indexing**: Elements accessed by position, e.g., `_1`, `_2`, etc.
   * - **Arity**: Supports up to 22 elements (Tuple1 to Tuple22).
   * - **Use Case**: Useful for returning multiple values from a method.
   * - **Pattern Matching**: Can be destructured using pattern matching.
   * - **Immutability**: Once created, elements cannot be modified.
   * - **Type Safety**: Types of elements are preserved and enforced.
   */
  val aTuple1 = new Tuple2(2, "hello, Scala") // same as below
  val aTuple2 = (2, "hello, Scala")  // Tuple2[Int, String] = (Int, String)

  println(aTuple2._1)  // 2
  println(aTuple2._2)  // hello, Scala
  println(aTuple2.copy(_2 = "goodbye Java"))
  println(aTuple2.swap)  // ("hello, Scala", 2)

  // Maps - keys -> values
  val aMap: Map[String, Int] = Map()

  val phonebook = Map(("Rishabh", 555), "Pappu" -> 789, ("RISHABH", 9000)).withDefaultValue(-1)
  // a -> b is sugar for (a, b)
  println(phonebook)

  // map ops
  println(phonebook.contains("Rishabh"))
  println(phonebook("Rishabh")) // phonebook("Rishabh") == phonebook.get("Rishabh").get
  // println(phonebook.get("Rishabh").get)
  println(phonebook("Tanuja"))

  // add a pairing
  val newPairing = "Tanuja" -> 678
  val newPhonebook = phonebook + newPairing
  println(newPhonebook)

  // functionals on maps
  // map, flatMap, filter
    println(phonebook.map(pair => pair._1.toLowerCase -> pair._2)) // Map(rishabh -> 9000, pappu -> 789)

  // filterKeys
    println(phonebook.view.filterKeys(x => x.startsWith("R")).toMap)
  // mapValues
  println(phonebook.view.mapValues(number => "+91-" + number).toMap)

  // conversions to other collections
  println(phonebook.toList)
  println(List(("Pappu", 555)).toMap)
  val names = List("Ramesh", "Aditya", "Ayush", "Atul", "Pappu", "Rishabh")
  println(names.groupBy(name => name.charAt(0)))
  println(names.groupBy(name => name.charAt(0) == 'A'))

  /*
  Exercise:

    1.  What would happen if I had two original entries "Rishabh" -> 555 and "RISHABH" -> 900
        and invoke println(phonebook.map(pair => pair._1.toLowerCase -> pair._2))

        !!! careful with mapping keys.

    2.  Overly simplified social network based on maps
        Person = String
        - add a person to the network
        - remove a person from the network
        - friend (mutual)
        - unfriend

        - number of friends of a person
        - person with most friends
        - how many people have NO friends
        - if there is a social connection between two people (direct or not)
   */
  // add a new person to the social network database
  def addToNetwork(network: Map[String, Set[String]], person: String): Map[String, Set[String]] = {
    network + (person -> Set())
  }

  // friend (mutual)
  def friend(network: Map[String, Set[String]], person1: String, person2: String): Map[String, Set[String]] = {
    val friendListOfPerson1 = network(person1)
    val friendListOfPerson2 = network(person2)

    network + (person1 -> (friendListOfPerson1 + person2)) + (person2 -> (friendListOfPerson2 + person1))
  }

  // Remove each other from their friend list
  def unFriend(network: Map[String, Set[String]], person1: String, person2: String): Map[String, Set[String]] = {
    val friendListOfPerson1 = network(person1)
    val friendListOfPerson2 = network(person2)

    network + (person1 -> (friendListOfPerson1 - person2)) + (person2 -> (friendListOfPerson2 - person1))
  }

  // remove a person from the network
  def remove(network: Map[String, Set[String]], person: String): Map[String, Set[String]] = {
    // Step 1: Remove this person from other person's friend list
    // Step 2: Remove the person from the Map Key and return the network
    // network.view.mapValues(friendList => friendList - person).toMap - person

    // using unFriend()
    def removeFromNetworkHelper(updatedNetwork: Map[String, Set[String]], friends: Set[String]): Map[String, Set[String]] = {
      if(friends.isEmpty) updatedNetwork
      else removeFromNetworkHelper(unFriend(updatedNetwork, person, friends.head), friends.tail)
    }

    // Remove mutual friends from network recursively
    val unFriended = removeFromNetworkHelper(network, network(person))

    // remove person key from the map and return the map
    unFriended - person
  }

  // number of friends of a person
  def nFrieds(network: Map[String, Set[String]], person: String): Int = {
    if(network.isEmpty || !network.contains(person)) 0
    else
    network(person).size
  }

  // return the person name who have max number of friends
  def mostFriends(network: Map[String, Set[String]]): String = {
    // approach 1
    /*var pair = "" -> 0
    network.view.foreach(p => {
      if(p._2.size > pair._2) pair =  p._1 -> p._2.size
    })
    pair._1*/

    // better approach
    network.maxBy(pair => pair._2.size)._1
  }

  // how many people have no friends
  def countPeopleWithoutFriends(network: Map[String, Set[String]]): Int = {
    // network.view.filter(pair => pair._2.isEmpty).size
    // network.view.filterKeys(k => network(k).isEmpty).size
   // network.count(pair => pair._2.isEmpty)
   network.count(_._2.isEmpty)
  }

  // if 2 people have network connections
  def haveMutualFriends(network: Map[String, Set[String]], person1: String, person2: String): Boolean = {
    @tailrec
    def bfs(target: String, consideredPeople: Set[String], discoveredPeople: Set[String]): Boolean = {
      if(discoveredPeople.isEmpty) false
      else { 
        val person = discoveredPeople.head
        if(target == person) true
        else if(consideredPeople.contains(person)) bfs(target, consideredPeople, discoveredPeople.tail)
        else bfs(target, consideredPeople + person, discoveredPeople.tail ++ network(person))
      } 
    }

    bfs(person2, Set(), network(person1) + person1)
  }

  val empty: Map[String, Set[String]] = Map()
  val network = addToNetwork(addToNetwork(empty, "Ramesh"), "Tanuja")
  println(network)

  println(friend(network, "Ramesh", "Tanuja"))
  println(unFriend(friend(network, "Ramesh", "Tanuja"), "Ramesh", "Tanuja"))
  println(remove(friend(network, "Ramesh", "Tanuja"), "Ramesh"))

  // Add 3 people in network Mohit, Raghu, Suresh
  val people = addToNetwork(addToNetwork(addToNetwork(empty, "Mohit"), "Raghu"), "Suresh")
  val mohitRaghu = friend(people, "Mohit", "Raghu")
  val testNetwork = friend(mohitRaghu, "Mohit", "Suresh")

  println(testNetwork)
  println(nFrieds(empty, "Mohit"))
  println(nFrieds(testNetwork, "Mohit"))
  println(mostFriends(testNetwork))
  println(countPeopleWithoutFriends(addToNetwork(testNetwork, "Tushar")))
  println(haveMutualFriends(testNetwork, "Mohit", "Raghu"))
  println(haveMutualFriends(testNetwork, "Suresh", "Raghu"))
  println(haveMutualFriends(addToNetwork(testNetwork, "Tushar"), "Suresh", "Tushar"))
  println(haveMutualFriends(network, "Ramesh", "Tanuja"))
}
