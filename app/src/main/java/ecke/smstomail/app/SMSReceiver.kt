package ecke.smstomail.app

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Telephony
import android.widget.Toast

class SMSReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        // Get SMS map from Intent
        if (context == null || intent == null || intent.action == null) {
            return
        }
        if (intent.action != (Telephony.Sms.Intents.SMS_RECEIVED_ACTION)) {
            return
        }
//        val contentResolver = context.contentResolver
        val smsMessages = Telephony.Sms.Intents.getMessagesFromIntent(intent)
        for (message in smsMessages) {
            Toast.makeText(
                context,
                "Message from ${message.displayOriginatingAddress} : body ${message.messageBody}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

}