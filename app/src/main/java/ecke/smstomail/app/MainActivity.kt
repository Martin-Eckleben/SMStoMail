package ecke.smstomail.app

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import ecke.smstomail.model.SMTPConfig

import kotlinx.android.synthetic.main.activity_main.host as host_input
import kotlinx.android.synthetic.main.activity_main.username as username_input
import kotlinx.android.synthetic.main.activity_main.password as password_input
import kotlinx.android.synthetic.main.activity_main.port as port_input
import kotlinx.android.synthetic.main.activity_main.receiver as receiver_input

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        // if configs are set -> fill input fields
        SMTPConfig.read(this)
        SMTPConfig.host?.let {
            host_input.setText(SMTPConfig.host)
        }
        SMTPConfig.username?.let{
            username_input.setText(SMTPConfig.username)
        }
        SMTPConfig.password?.let{
            password_input.setText(SMTPConfig.password)
        }
        SMTPConfig.port?.let{
            port_input.setText(SMTPConfig.port)
        }
        SMTPConfig.receiver?.let{
            receiver_input.setText(SMTPConfig.receiver)
        }

        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                "android.permission.RECEIVE_SMS",
                "android.permission.READ_SMS",
                "android.permission.INTERNET"
            ),
            1
        )
    }

    fun saveSettings(view: View) {

        val sharedPref = getSharedPreferences("smtpconfig", Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putString("host", host_input.text.toString())
            putString("username", username_input.text.toString())
            putString("password", password_input.text.toString())
            putString("port", port_input.text.toString())
            putString("receiver", receiver_input.text.toString())
            apply()
        }

        // show as toast
        Toast.makeText(
            this,
            "saved!",
            Toast.LENGTH_SHORT
        ).show()
    }
}