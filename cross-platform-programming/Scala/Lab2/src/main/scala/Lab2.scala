import scala.util.Random

/**
  * Object that represents functions for 1st task in laboratory work
  */
object Task1 {
  /**
    * Sum up positive numbers in a List
    *
    * @param list source list
    * @return positive elements sum
    */
  def sumPositive(list: List[Int]): Int = {
    val takePositive: PartialFunction[Int, Int] = {
      case x: Int if x > 0 => x
    }
    list.collect(takePositive).sum
  }
}

/**
  * Object that represents functions for 2st task in laboratory work
  */
object Task2 {
  /**
    * Reverses source list
    *
    * @param list source list
    * @return reversed list
    */
  def reverse(list: List[Any]): List[Any] = {
    list.foldRight(List[Any]())((x, z) => z :+ x)
  }
}

/**
  * Object that represents functions for 3rd task in laboratory work
  */
object Task3 {
  /**
    * Gets keys of elements that are even numbers
    *
    * @param map [String, Int] map
    * @return List[String] of keys
    */
  def evenKeyMap(map: Map[String, Int]): List[String] = {
    map.filter(t => t._2 % 2 == 0).keys.toList
  }
}

/**
  * Application entry object
  * Call all functions from laboratory work and output results
  *
  */
object Source extends App {
  val lSource: List[Int] = Seq.fill(5)(Random.nextInt() % 100).toList
  val mSource = Map("three" -> -3, "four" -> 4, "one" -> 1,
    "zero" -> 0, "minus one" -> -1, "minus two" -> -2)
  println("Source list:")
  println(lSource.mkString(" "))

  print("Positive numbers sum = ")
  println(Task1.sumPositive(lSource))

  println("List reverse:")
  println(Task2.reverse(lSource).mkString(" "))

  println("Source map:")
  println(mSource.mkString(" "))

  println("Even value keys:")
  println(Task3.evenKeyMap(mSource).mkString(" "))

}
