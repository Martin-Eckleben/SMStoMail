package ecke.smstomail.app

import android.content.Context

import android.util.Log
import android.widget.Toast
import co.nedim.maildroidx.MaildroidX
import co.nedim.maildroidx.MaildroidXType
import ecke.smstomail.model.SMS
import ecke.smstomail.model.SMTPConfig

class GmailSender {

    companion object {
        fun send(sms: SMS, context: Context) {

            SMTPConfig.read(context)
            if(!SMTPConfig.smtpConfigComplete()){
                Toast.makeText(
                    context,
                    "smtp config is not complete",
                    Toast.LENGTH_SHORT
                ).show()
                return
            }

            MaildroidX.Builder()
                .smtp(SMTPConfig.host?:"")
                .smtpUsername(SMTPConfig.username?:"")
                .smtpPassword(SMTPConfig.password?:"")
                .port(SMTPConfig.port?:"")
                .type(MaildroidXType.HTML)
                .to(SMTPConfig.receiver?:"")
                .from(sms.sender)
                .subject("SMS from :"+sms.sender)
                .body(sms.message)
                .onCompleteCallback(object : MaildroidX.onCompleteCallback {
                    override val timeout: Long = 3000
                    override fun onSuccess() {
                        Toast.makeText(
                            context,
                        "smtp mail sending success",
                            Toast.LENGTH_SHORT
                        ).show()
                        Log.d("MaildroidX", "SUCCESS")
                    }

                    override fun onFail(errorMessage: String) {
                        Toast.makeText(
                            context,
                            "smtp mail sending failed: $errorMessage",
                            Toast.LENGTH_LONG
                        ).show()
                        Log.d("MaildroidX", "FAIL")
                    }
                })
                .mail()
        }
    }
}