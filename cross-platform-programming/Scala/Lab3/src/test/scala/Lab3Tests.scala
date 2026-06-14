import org.scalatest.FlatSpec

import scala.collection.immutable.Stack

class Task1Tests extends FlatSpec {
  val list: List[Int] = Nil
  val seq = Seq(-4, -2, -10)
  val stack = Stack(1, 4, -4, -2, 4, 3, -10)
  val array = Array(1, 2, 5, 12)
  "Task1 function" should
    "Summarizes all positive numbers and multiplies all negative numbers in the given collection" in {
    assert(Task1.sumPosMulNeg(stack) === (Option(12), Option(-80)))
  }
  it should "return `None` if elements aren't presented in the given collection" in {
    assert(Task1.sumPosMulNeg(list) === (None, None))
    assert(Task1.sumPosMulNeg(List[Int]()) === (None, None))
    assert(Task1.sumPosMulNeg(seq) === (None, Option(-80)))
    assert(Task1.sumPosMulNeg(array) === (Option(20), None))
  }
}

class Task2Tests extends FlatSpec {
  "Task2 function" should "Convert notification to a `String` depending on the notification type" in {
    assert(Task2.getNotificationString(Email("", "", "")) === "New email:\nsender: \ntitle: \n")
    assert(Task2.getNotificationString(Sms(null, "Sms")) === "Sms from null\nSms")
    assert(Task2.getNotificationString(VoiceRecording("Ivan", "link")) === "Voice recording from Ivan, follow link: link")
  }
}