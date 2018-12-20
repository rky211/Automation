package com.example.raj.automation

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v4.widget.ListViewCompat
import android.system.Os.getuid
import android.util.Log
import android.view.View
import android.widget.*
import java.io.IOException
import java.io.OutputStream
import java.nio.channels.NotYetConnectedException
import java.util.*
import android.system.Os.socket



class BluetoothDeviceActivity : AppCompatActivity() {


    //----for avail device --------
    var mBroadcastReceiver = object : BroadcastReceiver(){
        override fun onReceive(context: Context, intent: Intent) {


            var action = intent.action

            when(action)
            {
                BluetoothDevice.ACTION_FOUND ->{
                    // Discovery has found a device. Get the BluetoothDevice
                    // object and its info from the Intent.
                    val device: BluetoothDevice =
                        intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE)
                    val deviceName = device.name
                    val deviceHardwareAddress = device.address // MAC address

                    availDev.add(device)
                    availList.add(deviceName+" :: "+deviceHardwareAddress)

                    aAdapter = ArrayAdapter(applicationContext, R.layout.single_bt_device, availList)
                    availListView?.adapter = aAdapter
                    aAdapter?.notifyDataSetChanged()

                    Toast.makeText(this@BluetoothDeviceActivity,"Name::"+deviceName+" Add::"+deviceHardwareAddress,Toast.LENGTH_SHORT).show()

                }

            }


        }

    }

    //--for Paired-----------
    var mBroadcastReceiver2 = object : BroadcastReceiver(){
        override fun onReceive(context: Context, intent: Intent) {
            var action = intent.action

            when(action)
            {
                BluetoothDevice.ACTION_BOND_STATE_CHANGED ->{

                    val device: BluetoothDevice =
                        intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE)


                    if (device.bondState == BluetoothDevice.BOND_BONDED){
                        Toast.makeText(this@BluetoothDeviceActivity,"Paired",Toast.LENGTH_SHORT).show()
                    }
                    if (device.bondState == BluetoothDevice.BOND_BONDING){
                        Toast.makeText(this@BluetoothDeviceActivity,"Start Pairing..",Toast.LENGTH_SHORT).show()
                    }
                    if (device.bondState == BluetoothDevice.BOND_NONE){
                        Toast.makeText(this@BluetoothDeviceActivity,"Not Paired",Toast.LENGTH_SHORT).show()
                    }
                }
                else-> Toast.makeText(this@BluetoothDeviceActivity,"Some Error",Toast.LENGTH_SHORT).show()
            }

        }

    }

    var status:TextView?=null
    var pairedListView:ListView?=null
    var availListView: ListView?=null
    var pairList = mutableListOf<String>()
    var availList = mutableListOf<String>()
    var mBluetoothAdapter: BluetoothAdapter?=null
    var mToolbar:android.support.v7.widget.Toolbar?=null
    var pairDev = mutableListOf<BluetoothDevice>()
    var availDev = mutableListOf<BluetoothDevice>()
    var mDevice:BluetoothDeviceActivity?=null
    var isBtConnected = false

    private var sendRecv: SendRecvClass? = null

    var deviceAdd:String?=null
    var pAdapter: ArrayAdapter<String>?=null
    var aAdapter: ArrayAdapter<String>?=null

    companion object {

        var STATE_CONNECTING:Int = 2
        var STATE_CONNECTED:Int = 3
        var STATE_CONNECTION_FAILED:Int = 4

        var APP_NAME:String = "BTAPP"
        var MY_UUID:UUID = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb")
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bluetooth_device)

        mToolbar = findViewById(R.id.bt_toolbar)
        setSupportActionBar(mToolbar)
        supportActionBar?.title = "Bluetooth"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        pairedListView = findViewById(R.id.paired_list_item)
        availListView = findViewById(R.id.available_list_item)
        status  = findViewById(R.id.textStatus)

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter()


        //paired Device List
            val pairedDevices: Set<BluetoothDevice>? = mBluetoothAdapter?.bondedDevices
            pairedDevices?.forEach { device ->
                val deviceName = device.name
                val deviceHardwareAddress = device.address // MAC address

                pairDev.add(device)
                pairList.add(deviceName+" :: "+deviceHardwareAddress)

                //Toast.makeText(this,"Name::"+deviceName+" Add::"+deviceHardwareAddress, Toast.LENGTH_SHORT).show()
            }

            pAdapter = ArrayAdapter(applicationContext,R.layout.single_bt_device,pairList)
            pairedListView?.adapter = pAdapter


        //Available Device
        val filter = IntentFilter(BluetoothDevice.ACTION_FOUND)
        registerReceiver(mBroadcastReceiver, filter)
        mBluetoothAdapter?.startDiscovery()


        setConnectionWithPairedAndAvailDevice()

    }
    private fun setConnectionWithPairedAndAvailDevice() {

        val filter1 = IntentFilter(BluetoothDevice.ACTION_BOND_STATE_CHANGED)
        registerReceiver(mBroadcastReceiver2, filter1)

        pairedListView?.setOnItemClickListener { parent, view, position, id ->

            mBluetoothAdapter?.cancelDiscovery()

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                pairDev[position].createBond()
            }

            deviceAdd = pairDev[position].address

            var btClientClass = BluetoothClient(pairDev[position])
            btClientClass?.start()

            status?.text = "Connecting..."

        }

        availListView?.setOnItemClickListener { parent, view, position, id ->


            mBluetoothAdapter?.cancelDiscovery()

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                availDev[position].createBond()
            }



        }

    }





    //-------- Handler------------
    var handler: Handler = Handler(object : Handler.Callback{
        override fun handleMessage(msg: Message?): Boolean {

            when(msg?.what){

                STATE_CONNECTING -> status?.text = "Connecting"
                STATE_CONNECTED -> {
                    status?.text = "Connected"

                   var mIntent= Intent(this@BluetoothDeviceActivity,MainActivity::class.java)
                    mIntent.putExtra("ADD",deviceAdd)
                    mIntent.putExtra("STATE",1)
                    startActivity(mIntent)
                }

                STATE_CONNECTION_FAILED -> status?.text ="Connection Failed"

            }
            return true
        }

    })



    //----------- Client Class -----------------
    public inner class BluetoothClient:Thread {

        private var connectSuccess = true //if it's here, it's almost connected

        var device: BluetoothDevice? = null
        private var mmSocket: BluetoothSocket? = null


        constructor(device1: BluetoothDevice?) {

            device = device1
        }

        public override fun run() {

            mBluetoothAdapter?.cancelDiscovery()

            try {

                if(mmSocket == null || !isBtConnected) {

                    //connects to the device's address and checks if it's available
                    var btDevice = mBluetoothAdapter?.getRemoteDevice(deviceAdd)

                    mmSocket = btDevice?.createInsecureRfcommSocketToServiceRecord(MY_UUID)//create a RFCOMM (SPP) connection

                    mBluetoothAdapter?.cancelDiscovery()

                    mmSocket?.connect()

                    //sendRecv = SendRecvClass(mmSocket)
                    //sendRecv?.start()

                }
            } catch (e: Exception) {
                try {
                    var clazz = mmSocket!!.remoteDevice.javaClass
                    var paramTypes = arrayOf<Class<*>>(Integer.TYPE)
                    var m = clazz.getMethod("createRfcommSocket", *paramTypes)
                    var fallbackSocket = m.invoke(mmSocket?.remoteDevice, Integer.valueOf(2)) as BluetoothSocket
                    fallbackSocket.connect()

                }catch (e:Exception){

                    connectSuccess = false
                    e.printStackTrace()
                    Log.e("MyTag", "Exception",e)
                }


            }

            if (!connectSuccess) {
               // msg("Connection Failed. Invalid Bluetooth module? Try again.")
                var mMsg: Message = Message.obtain()
                mMsg.what = STATE_CONNECTION_FAILED
                handler.sendMessage(mMsg)
                //finish()
            } else {

               // msg("Connected.")
                var mMsg: Message = Message.obtain()
                mMsg.what = STATE_CONNECTED
                handler.sendMessage(mMsg)

                isBtConnected = true
            }

        }


    }


    override fun onDestroy() {
        super.onDestroy()


        // Don't forget to unregister the ACTION_FOUND receiver.
        unregisterReceiver(mBroadcastReceiver)
        unregisterReceiver(mBroadcastReceiver2)
    }

    override fun onResume() {
        super.onResume()

        aAdapter?.notifyDataSetChanged()
        pAdapter?.notifyDataSetChanged()
    }


    }

