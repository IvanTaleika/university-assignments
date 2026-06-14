import org.scalatest.FlatSpec

class Task1Tests extends FlatSpec {
  val source = List(1, -4, 2, 5, 2, 1, 7, -9, 3, 4)
  "Task1's functions" should "return an element which have given index" in {
    assert(Task1.recursive(source, 0) === 1)
    assert(Task1.recursive(source, 5) === 1)
    assert(Task1.nonRecursive(source, 0) === 1)
    assert(Task1.nonRecursive(source, 5) === 1)
  }

  it should  "throw an IndexOutOfBoundsException when List doesn't have such index" in {
    assertThrows[IndexOutOfBoundsException] {
      Task1.nonRecursive(source, -1)
    }
    assertThrows[UnsupportedOperationException] {
      Task1.recursive(source, -1)
    }
    assertThrows[IndexOutOfBoundsException] {
      Task1.nonRecursive(source, 20)
    }
    assertThrows[UnsupportedOperationException] {
      Task1.recursive(source, 20)
    }

  }
}

class Task2Tests extends FlatSpec {
  "Task2's functions" should "return all elements in the List that are lower than sum of previous 2 element" in {
    val source = List(1, -4, 2, 5, 2, 1, 7, -9, 3, 4)
    assert(Task2.recursive(source) === List(2, 1, -9))
    assert(Task2.nonRecursive(source) === List(2, 1, -9))
  }

  it should "return empty List on empty List import" in {
    val source = List[Int]()
    assert(Task2.recursive(source).isEmpty === true)
    assert(Task2.nonRecursive(source).isEmpty === true)
  }
}
