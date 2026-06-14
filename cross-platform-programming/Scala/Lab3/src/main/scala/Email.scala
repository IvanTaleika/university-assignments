/**
  * Represents email notification
  *
  * @param sender email sender
  * @param title  email title
  * @param body   email body
  */
case class Email(sender: String, title: String, body: String) extends Notification {
}
