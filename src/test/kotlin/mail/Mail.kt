package mail

import org.junit.jupiter.api.Test
import org.mockito.Mockito.*

class Mail {


    @Test
    fun sendMessage() {
        val mailer = mock(Mailer::class.java)
        val personalInfo = PersonalInfo("email")
        val client = Client(personalInfo)
        val message = "message"

        sendMessageToClient(client, message, mailer)

        verify(mailer).sendMessage("email", "message")
    }

    @Test
    fun sendMessageNull() {
        val mailer = mock(Mailer::class.java)
        val personalInfo = PersonalInfo("email")
        val client = Client(personalInfo)
        val message: String? = null

        sendMessageToClient(client, message, mailer)

//        verify(mailer,never()).sendMessage()
        verifyNoMoreInteractions(mailer)
    }
}