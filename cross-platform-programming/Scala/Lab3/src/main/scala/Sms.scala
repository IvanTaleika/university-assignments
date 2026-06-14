/**
  * Represents SMS notification
  *
  * @param caller  SMS caller
  * @param message SMS message
  */
case class Sms(caller: String, message: String) extends Notification {
}
