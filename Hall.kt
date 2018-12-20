package com.example.raj.automation


import android.bluetooth.BluetoothDevice
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.AppCompatSeekBar
import android.support.v7.widget.SwitchCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import java.io.IOException
import java.io.OutputStream


class Hall : Fragment() {

    var mSwitchB1:SwitchCompat?=null
    var mSwitchB2:SwitchCompat?=null
    var mSwitchB3:SwitchCompat?=null
    var mSwitchF1:SwitchCompat?=null
    var mSeekbar1:AppCompatSeekBar?=null
    var mSeek1Text:TextView?=null

    var mOutputStream: OutputStream?=null
    var getState:Int=0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
         var view =   inflater.inflate(R.layout.fragment_hall, container, false)

        getState = this.arguments!!.getInt("STATE",0)

        mSwitchB1 = view.findViewById(R.id.switch_bulb1)
        mSwitchB2 =view.findViewById(R.id.switch_bulb2)
        mSwitchB3 = view.findViewById(R.id.switch_bulb3)
        mSwitchF1 =view.findViewById(R.id.switch_fan1)
        mSeekbar1 = view.findViewById(R.id.seekbar1)
        mSeek1Text = view.findViewById(R.id.seek_text1)

        mSwitchB1?.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
                mSwitchB1?.text ="ON"
                if (getState == 1){
                    try {
                        mOutputStream?.write("a".toByteArray())
                        Toast.makeText(activity, "a", Toast.LENGTH_LONG).show()
                    }catch (e: IOException){
                        Toast.makeText(activity, "Connection not established with your home", Toast.LENGTH_LONG).show()
                        e.printStackTrace()
                    }
                }
                else{
                    Toast.makeText(activity, "Connection not established with your home", Toast.LENGTH_LONG).show()
                }
            }
            else{
                mSwitchB1?.text ="OFF"
                if (getState == 1) {
                    try {
                        mOutputStream?.write("b".toByteArray())
                        Toast.makeText(activity, "b", Toast.LENGTH_LONG).show()
                    } catch (e: IOException) {
                        // TODO Auto-generated catch block
                        Toast.makeText(activity, "Connection not established with your home", Toast.LENGTH_LONG).show()
                        e.printStackTrace()
                    }

                } else {
                    Toast.makeText(activity, "Connection not established with your home", Toast.LENGTH_LONG).show()
                }

            }
        }
        mSwitchB2?.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
                mSwitchB2?.text ="ON"
                if (getState == 1){
                    try {

                        mOutputStream?.write(2)
                        Toast.makeText(activity, "2", Toast.LENGTH_LONG).show()
                    }catch (e: IOException){
                        Toast.makeText(activity, "Connection not established with your home", Toast.LENGTH_LONG).show()
                        e.printStackTrace()
                    }
                }
                else{
                    Toast.makeText(activity, "Connection not established with your home", Toast.LENGTH_LONG).show()
                }
            }
            else{
                mSwitchB2?.text ="OFF"
                if (getState == 1) {
                    try {
                        mOutputStream?.write(3)
                        Toast.makeText(activity, "3", Toast.LENGTH_LONG).show()
                    } catch (e: IOException) {
                        // TODO Auto-generated catch block
                        Toast.makeText(activity, "Connection not established with your home", Toast.LENGTH_LONG).show()
                        e.printStackTrace()
                    }

                } else {
                    Toast.makeText(activity, "Connection not established with your home", Toast.LENGTH_LONG).show()
                }

            }
        }

        mSwitchB3?.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
                mSwitchB3?.text ="ON"
                if (getState == 1){
                    try {

                        mOutputStream?.write(4)
                        Toast.makeText(activity, "4", Toast.LENGTH_LONG).show()
                    }catch (e: IOException){
                        Toast.makeText(activity, "Connection not established with your home", Toast.LENGTH_LONG).show()
                        e.printStackTrace()
                    }
                }
                else{
                    Toast.makeText(activity, "Connection not established with your home", Toast.LENGTH_LONG).show()
                }

            }
            else{
                mSwitchB3?.text ="OFF"
                if (getState == 1) {
                    try {
                        mOutputStream?.write(5)
                        Toast.makeText(activity, "5", Toast.LENGTH_LONG).show()
                    } catch (e: IOException) {
                        // TODO Auto-generated catch block
                        Toast.makeText(activity, "Connection not established with your home", Toast.LENGTH_LONG).show()
                        e.printStackTrace()
                    }

                } else {
                    Toast.makeText(activity, "Connection not established with your home", Toast.LENGTH_LONG).show()
                }

            }
        }
        mSwitchF1?.setOnCheckedChangeListener { buttonView, isChecked ->

            if (isChecked){
                mSwitchF1?.text ="ON"
                if (getState == 1){
                    try {

                        mOutputStream?.write(255)
                        Toast.makeText(activity, "255", Toast.LENGTH_LONG).show()
                    }catch (e: IOException){
                        Toast.makeText(activity, "Connection not established with your home", Toast.LENGTH_LONG).show()
                        e.printStackTrace()
                    }
                }
                else{
                    Toast.makeText(activity, "Connection not established with your home", Toast.LENGTH_LONG).show()
                }

            }
            else{
                mSwitchF1?.text ="OFF"
                if (getState == 1) {
                    try {
                        mOutputStream?.write(0)
                        Toast.makeText(activity, "0", Toast.LENGTH_LONG).show()
                    } catch (e: IOException) {
                        // TODO Auto-generated catch block
                        Toast.makeText(activity, "Connection not established with your home", Toast.LENGTH_LONG).show()
                        e.printStackTrace()
                    }

                } else {
                    Toast.makeText(activity, "Connection not established with your home", Toast.LENGTH_LONG).show()
                }

            }
        }

        mSeekbar1?.setOnSeekBarChangeListener(object :SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

                mSeek1Text?.text = "Fan1 Speed::"+progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })

        return view
    }


}
