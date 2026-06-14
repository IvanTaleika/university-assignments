import org.scalatest.FlatSpec

class Task1Tests extends FlatSpec {
  val source = List(1, -20, 5, 13, -5, 0, 8)
  "Task1 function" should "sum up positive numbers in a List" in {
    assert(Task1.sumPositive(source) === 1 + 5 + 13 + 8)
  }
  it should "return 0 on an empty List" in {
    assert(Task1.sumPositive(List[Int]()) === 0)
  }
}

class Task2Tests extends FlatSpec {
  val source = List(1, -20, 5, 13, -5, 0, 8)
  val answer = List(8, 0, -5, 13, 5, -20, 1)
  "Task2 function" should "reverse a List" in {
    assert(Task2.reverse(source) === answer)
    assert(Task2.reverse(List[Int]()) === List[Int]())
  }
}

class Task3Tests extends FlatSpec {
  "Task3 function" should "gets keys of elements that are even numbers" in {
    val source = Map("three" -> -3, "four" -> 4, "one" -> 1,
      "zero" -> 0, "minus one" -> -1, "minus two" -> -2)
    val answer = List("four", "zero", "minus two")
    val result = Task3.evenKeyMap(source)
    for (i <- answer) {
      assert(result.contains(i))
    }
  }
  it should "returns an empty List on empty source argument" in {
    assert(Task3.evenKeyMap(Map[String, Int]()) === List[String]())
  }
}
