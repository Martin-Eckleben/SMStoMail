package ecke.smstomail.model

import android.content.Context

class SMTPConfig {
    companion object {
        var host:String? = null
        var username:String? = null
        var password:String? = null
        var port:String? = null
        var receiver:String? = null

        fun read(context:Context){
            val sharedPref = context.getSharedPreferences("smtpconfig", Context.MODE_PRIVATE) ?: return
            this.host = sharedPref.getString("host", null)
            this.username = sharedPref.getString("username", null)
            this.password = sharedPref.getString("password", null)
            this.port = sharedPref.getString("port", null)
            this.receiver = sharedPref.getString("receiver",null)
        }

        fun smtpConfigComplete():Boolean{
            if(this.host!=null && this.username!=null && this.password!=null && this.port!=null && this.receiver!=null)
                return true
            return false
        }
    }
}
