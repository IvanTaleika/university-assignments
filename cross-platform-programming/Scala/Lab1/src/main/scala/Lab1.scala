import scala.annotation.tailrec
import scala.util.Random

/**
  * Object that represents functions for 1st task in laboratory work:
  */
object Task1 {
  /**
    * recursive way to selects an element by its index in the `List`
    *
    * @param list source List
    * @param n    element index
    * @return element
    */
  @tailrec
  def recursive(list: List[Any], n: Int): Any = {
    if (n == 0) {
      list.head
    } else {
      recursive(list.tail, n - 1)
    }
  }

  /**
    * Non recursive way to selects an element by its index in the `List`
    *
    * @param list source List
    * @param n    element index
    * @return element
    */
  def nonRecursive(list: List[Any], n: Int): Any = {
    list(n)
  }
}

/**
  * Object that represents functions for 2st task in laboratory work:
  *
  */
object Task2 {
  /**
    * recursive way to selects all elements in the `List`
    * that are lower than sum of previous 2 elements
    *
    * @param source source list
    * @return converted list
    */
  def recursive(source: List[Int]): List[Int] = {
    @tailrec
    def getElements(source: List[Int], dest: List[Int]): List[Int] = {
      def isLower: List[Int] = {
        if ((source(2) - source(1) - source.head) < 0) {
          dest :+ source(2)
        } else {
          dest
        }
      }

      if (source.length < 3) {
        dest
      } else {
        getElements(source.tail, isLower)
      }
    }

    getElements(source, List[Int]())
  }

  //  def nonRecursive[T](list: List[T])(implicit n: Numeric[T]): List[T] = {
  //    import n._
  //    for (i <- 0 until (list.length - 2) if n.minus(list(i + 2), n.plus(list(i + 1), list(i)))
  //      < 0.asInstanceOf[T]) yield list(i + 2)
  //  }.toList

  /**
    * Non recursive way to selects all elements in the `List`
    * that are lower than sum of previous 2 elements
    *
    * @param list source list
    * @return converted list
    */
  def nonRecursive(list: List[Int]): List[Int] = {
    for (i <- 0 until (list.length - 2) if (list(i + 2) - list(i + 1) - list(i)) < 0) yield list(i + 2)
  }.toList
}

/**
  * Application entry object
  * Call all functions from laboratory work and output results
  */
object Source extends App {
  val source: List[Int] = Seq.fill(20)(Random.nextInt(10)).toList
  println("Source array:")
  println(source.mkString(" "))

  println("list(n)")
  print("Non recursive: ")
  println(Task1.nonRecursive(source, 5))
  print("recursive: ")
  println(Task1.recursive(source, 5))

  println("Converted list:")
  println("Non recursive:")
  println(Task2.nonRecursive(source).mkString(" "))
  println("recursive: ")
  println(Task2.recursive(source).mkString(" "))
}