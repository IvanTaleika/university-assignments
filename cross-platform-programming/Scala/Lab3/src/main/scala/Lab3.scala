import scala.collection.immutable.Stack

/**
  * Object that represents functions for 1st task in laboratory work
  */
object Task1 {

  //  def listSumPosMulNeg(list: List[Int]): (Int, Int) = {
  //    @tailrec
  //    def perform(list: List[Int], tuple: (Int, Int)): (Int, Int) = {
  //      list match {
  //        case Nil => tuple
  //        case x :: rest if x >= 0 => perform(rest, (tuple._1 + x, tuple._2))
  //        case x :: rest if x < 0 => perform(rest, (tuple._1, tuple._2 * x))
  //      }
  //    }
  //    perform(list, (0, 1))
  //  }
  /**
    * Summarizes all positive numbers and multiplies all negative numbers
    * in the given collection
    *
    * @param coll given collection
    * @return `Tuple(Option[Int], Option[Int])` where 1st element is a sum of positives numbers
    *         and 2nd is a composition of negative numbers
    */
  def sumPosMulNeg(coll: Iterable[Int]): (Option[Int], Option[Int]) = {
    //    coll.foldLeft((0, 1))((z, x) => {
    //      x match {
    //        case _ if x >= 0 => (z._1 + x, z._2)
    //        case _ if x < 0 => (z._1, z._2 * x)
    //      }
    //    })
    coll.foldLeft[(Option[Int], Option[Int])](None, None)((z, x) => {
      x match {
        case _ if x > 0 => z.copy(_1 = Some(z._1.getOrElse(0) + x))
        //          (Some(z._1.getOrElse(0) + x), z._2)

        //          z._1 match {
        //          case Some(y) => (Some(y + x), z._2)
        //          case None => (Some(0 + x), z._2)
        //        }
        case _ if x < 0 => z.copy(_2 = Some(z._2.getOrElse(1) * x))
        //          (z._1, Some(z._2.getOrElse(1) * x))

        //          z._2 match {
        //          case Some(y) => (z._1, Some(y * x))
        //          case None => (z._1, Some(1 * x))
        //        }
        case _ => z
      }
    })
  }
}

/**
  * Object that represents functions for 2nd task in laboratory work
  */
object Task2 {
  /**
    * Convert notification to a `String` depending on the notification type
    *
    * @param notification abstract notification
    * @return notification as a string
    */
  def getNotificationString(notification: Notification): String = {
    notification match {
      case Email(sender, title, body) => "New email:\nsender: " + sender + "\ntitle: " + title + "\n" + body
      case Sms(caller, message) => "Sms from " + caller + "\n" + message
      case VoiceRecording(contactName, link) => "Voice recording from " + contactName + ", follow link: " + link
      case _ => "Unknown notification."
    }
  }
}

/**
  * Application entry point.
  * Call all functions from laboratory work and output results
  */
object Source extends App {
  val list: List[Int] = Nil
  val seq = Seq(-4, -2, -10)
  val stack = Stack(1, 4, -4, -2, 4, 3, -10)
  val array = Array(1, 2, 5, 12)
  println("Task1:")
  println(Task1.sumPosMulNeg(seq))
  println(Task1.sumPosMulNeg(list))
  println(Task1.sumPosMulNeg(stack))
  println(Task1.sumPosMulNeg(array))

  println("Task2:")
  println(Task2.getNotificationString(Email("", "", "")))
  println(Task2.getNotificationString(Sms(null, "Sms")))
  println(Task2.getNotificationString(VoiceRecording("Ivan", "link")))
}
