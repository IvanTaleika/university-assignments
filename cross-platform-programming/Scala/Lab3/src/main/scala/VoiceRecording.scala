/**
  * Represents voice recording notification
  *
  * @param contactName contact that sends a notification
  * @param link        link to voice recording
  */
case class VoiceRecording(contactName: String, link: String) extends Notification {
}
