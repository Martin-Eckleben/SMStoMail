package ecke.smstomail.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

public class ReceiveSMS extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        SmsMessage[] msgs = null;
        String str = "";

        if (bundle != null) {

            Object[] pdus = (Object[]) bundle.get("pdus");
            msgs = new SmsMessage[pdus.length];

            for (int i = 0; i < msgs.length; i++) {

                msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);

                str += "SMS from " + msgs[i].getOriginatingAddress()+" :";
                str += msgs[i].getMessageBody();
                str += "\n";
            }

            /*if (VarMessageBody.startsWith("START")) {
                Intent intentHome = new Intent(context, MainActivity.class);
                intentHome.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intentHome);
            }*/

        }

    }
}