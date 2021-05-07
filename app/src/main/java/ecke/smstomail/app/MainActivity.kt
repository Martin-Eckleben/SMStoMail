package ecke.smstomail.app

import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        ActivityCompat.requestPermissions(
            this,
            arrayOf("android.permission.RECEIVE_SMS", "android.permission.READ_SMS"),
            1
        );

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun TEST(view: View) {

        val sms: MutableList<String> = ArrayList()
        val uriSMSURI: Uri = Uri.parse("content://sms/inbox")
        val cur: Cursor? = contentResolver.query(uriSMSURI, null, null, null, null)

        while (cur != null && cur.moveToNext()) {
            val address: String = cur.getString(cur.getColumnIndex("address"))
            val body: String = cur.getString(cur.getColumnIndexOrThrow("body"))
            sms.add("Number: $address .Message: $body")
        }

        cur?.close()

        Log.e("TEST", sms.toString());
    }
}