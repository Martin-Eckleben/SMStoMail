package ecke.smstomail.model

class SMS {
    var sender:String=""
    var message:String=""

    constructor(sender: String, message:String){
        this.message = message
        this.sender = sender
    }
}