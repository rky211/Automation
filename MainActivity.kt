package com.example.raj.automation

import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.content.*
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v7.widget.SwitchCompat
import android.view.MenuItem
import android.net.wifi.WifiManager
import android.os.Message
import android.support.v7.app.AlertDialog
import android.widget.*
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.lang.Exception
import java.util.*


class MainActivity : AppCompatActivity() {

    var toolbar:android.support.v7.widget.Toolbar?=null
    var mDrawerLayout: DrawerLayout?=null
    var actionBarDrawerToggle: ActionBarDrawerToggle?=null
    var mNavigationView:NavigationView?=null
    var mSwitchWifi:SwitchCompat?=null
    var mSwitchBt:SwitchCompat?=null
    var mBluetoothAdapter:BluetoothAdapter?=null
    var mConnectBtn:Button?=null


    var state:Int = 0
    var stateBundle:Bundle = Bundle()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mSwitchBt = findViewById(R.id.switch_bt)
        mSwitchWifi = findViewById(R.id.switch_wf)
        mConnectBtn = findViewById(R.id.connect_bt)

        var my = intent.getStringExtra("ADD")
        state = intent.getIntExtra("STATE",0)

        stateBundle.putInt("STATE",state)
        if(state == 1){
            mConnectBtn?.text = "Connected"
        }

        findViewById<TextView>(R.id.btConnectedText).text = my

        switchFun()

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter()

        setUpToolBar()

        isMyBtEnabled()

        mNavigationView?.menu?.getItem(0)?.isChecked = true
        displaySelectedFragment(Hall())

        mNavigationView = findViewById<NavigationView>(R.id.navigation_menu)
        mNavigationView?.setNavigationItemSelectedListener (object :NavigationView.OnNavigationItemSelectedListener{
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                when(item.itemId){
                    R.id.nav_item_hall -> {
                        supportActionBar?.title = "Hall"
                        displaySelectedFragment(Hall())
                    }
                    R.id.nav_item_kitchen -> {
                        supportActionBar?.title = "Kitchen"
                        displaySelectedFragment(Kitchen())
                    }

                    R.id.nav_item_room -> {
                        supportActionBar?.title = "Room"
                        displaySelectedFragment(Room())
                    }
                    R.id.nav_item_villa -> {
                        supportActionBar?.title = "Villa"
                        displaySelectedFragment(Villa())
                }
                    R.id.nav_item_toilet -> {
                        supportActionBar?.title = "Toilet"
                        displaySelectedFragment(Toilet())
                    }
                }

                mDrawerLayout?.closeDrawer(GravityCompat.START)
                return true
            }

        })

    }

    private fun isMyBtEnabled() {
        if ((mBluetoothAdapter?.isEnabled as Boolean)){

            mSwitchBt?.isChecked =true

            mConnectBtn?.setOnClickListener {
                startActivity(Intent(this,BluetoothDeviceActivity::class.java))
            }


        }

    }


    private fun switchFun() {
        mSwitchBt?.setOnCheckedChangeListener { buttonView, isChecked ->

            if(isChecked){


                if (mBluetoothAdapter == null){

                    Toast.makeText(this,"Bluetooth not Support",Toast.LENGTH_SHORT).show()
                }
                else if(!(mBluetoothAdapter?.isEnabled as Boolean)){
                    var enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                    startActivityForResult(enableBtIntent,1)


                }

            }
            else{

                if (mBluetoothAdapter == null){

                    Toast.makeText(this,"Bluetooth not Support",Toast.LENGTH_SHORT).show()
                }
                else if (mBluetoothAdapter?.isEnabled as Boolean){


                    mBluetoothAdapter?.disable()


                }
            }
        }

        mSwitchWifi?.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){

                //Other Operation
                val wifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
                    if(!wifiManager.isWifiEnabled)
                    {
                    //Perform Operation
                    wifiManager.isWifiEnabled = true}
            }
            else{
                val wifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
                if(wifiManager.isWifiEnabled) {
                    wifiManager.isWifiEnabled = false
                }
            }
        }
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1){
            if (resultCode == Activity.RESULT_OK){

                isMyBtEnabled()

            }
        }
    }



    fun setUpToolBar(){
        toolbar = findViewById<android.support.v7.widget.Toolbar>(R.id.main_page_toolbar)
        mDrawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)
        setSupportActionBar(toolbar)
        supportActionBar?.title ="Hall"

        actionBarDrawerToggle = ActionBarDrawerToggle(this,mDrawerLayout,toolbar,R.string.app_name,R.string.app_name)
        mDrawerLayout?.addDrawerListener(actionBarDrawerToggle!!)
        actionBarDrawerToggle?.syncState()
    }
    private fun displaySelectedFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragment.arguments = stateBundle
        fragmentTransaction.replace(R.id.frame, fragment)
        fragmentTransaction.commit()
    }
    override fun onBackPressed() {
        if (mDrawerLayout!!.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout?.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }

    }




}
