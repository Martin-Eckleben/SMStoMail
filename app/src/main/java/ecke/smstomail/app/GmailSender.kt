package ecke.smstomail.app

import android.util.Log
import co.nedim.maildroidx.MaildroidX
import co.nedim.maildroidx.MaildroidXType

class GmailSender {
    fun send() {

        val sharedPref = getSharedPreferences("smtpconfig", Context.MODE_PRIVATE) ?: return
        this.host = sharedPref.getString("host", null).toString();
        this.username = sharedPref.getString("username", null).toString();
        this.password = sharedPref.getString("password", null).toString();
        this.port = sharedPref.getString("port", null).toString();

        MaildroidX.Builder()
            .smtp("smtp.gmail.com")
            .smtpUsername("")
            .smtpPassword("")
            .port("")
            .type(MaildroidXType.HTML)
            .to("")
            .from("")
            .subject("")
            .body("")
            .onCompleteCallback(object : MaildroidX.onCompleteCallback {
                override val timeout: Long = 3000
                override fun onSuccess() {
                    Log.d("MaildroidX", "SUCCESS")
                }

                override fun onFail(errorMessage: String) {
                    Log.d("MaildroidX", "FAIL")
                }
            })
            .mail()
    }
}