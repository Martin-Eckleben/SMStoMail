package ecke.smstomail.app

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

import kotlinx.android.synthetic.main.activity_main.host as host_input
import kotlinx.android.synthetic.main.activity_main.username as username_input
import kotlinx.android.synthetic.main.activity_main.password as password_input
import kotlinx.android.synthetic.main.activity_main.port as port_input

class MainActivity : AppCompatActivity() {

    private lateinit var host: String
    private lateinit var username: String
    private lateinit var password: String
    private lateinit var port: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        val sharedPref = getSharedPreferences("smtpconfig", Context.MODE_PRIVATE) ?: return
        this.host = sharedPref.getString("host", null).toString();
        this.username = sharedPref.getString("username", null).toString();
        this.password = sharedPref.getString("password", null).toString();
        this.port = sharedPref.getString("port", null).toString();

        host_input.setText(this.host);
        username_input.setText(this.username);
        password_input.setText(this.password);
        port_input.setText(this.port);

        ActivityCompat.requestPermissions(
            this,
            arrayOf("android.permission.RECEIVE_SMS", "android.permission.READ_SMS"),
            1
        );
    }

    fun SaveSettings(view: View) {

        val sharedPref = getSharedPreferences("smtpconfig", Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putString("host", host_input.text.toString())
            putString("username", username_input.text.toString())
            putString("password", password_input.text.toString())
            putString("port", port_input.text.toString())
            apply()
        }
    }
}