package com.example.raj.automation

import android.bluetooth.BluetoothSocket
import java.io.IOException
import java.io.OutputStream

class SendRecvClass:Thread {

    private var sendrecBtSocket: BluetoothSocket? = null
    // private var mInputStream: InputStream? = null
    private var mOutputStream: OutputStream? = null

    //---------Rececive Data -----------------


    constructor(socket: BluetoothSocket?){

        //var tempIn: InputStream? = null
        var tempOut: OutputStream? = null


        sendrecBtSocket = socket
        try {
            //  tempIn = sendrecBtSocket?.inputStream
            tempOut = sendrecBtSocket?.outputStream

        } catch (e: IOException) {

            e.printStackTrace()
        }

        //mInputStream = tempIn
        mOutputStream = tempOut
    }

    override fun run() {
        super.run()
        // var numBytes: Int?
        //var buffer = ByteArray(1024)
    }

    fun write(bytes: ByteArray) {
        try {

            //findViewById<TextView>(R.id.smtText)?.text = bytes.toString()
            mOutputStream?.write(bytes)
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }
}