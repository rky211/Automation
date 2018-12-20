package com.example.raj.automation


import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
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


class Room : Fragment() {
    var mSwitchB1: SwitchCompat?=null
    var mSwitchB2: SwitchCompat?=null
    var mSwitchF1:SwitchCompat?=null
    var mSeekbar1: AppCompatSeekBar?=null
    var mSeek1Text: TextView?=null

    var mOutputStream: OutputStream?=null

    var getState:Int=0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view =   inflater.inflate(R.layout.fragment_room, container, false)

        getState = this.arguments!!.getInt("STATE",0)

        mSwitchB1 = view.findViewById(R.id.room_switch_bulb1)
        mSwitchB2 =view.findViewById(R.id.room_switch_bulb2)
        mSwitchF1 =view.findViewById(R.id.room_switch_fan1)
        mSeekbar1 = view.findViewById(R.id.room_seekbar1)
        mSeek1Text = view.findViewById(R.id.room_seek_text1)

        mSwitchB1?.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
                mSwitchB1?.text ="ON"
                if (getState == 1){
                    try {

                        mOutputStream?.write(14)
                        Toast.makeText(activity, "14", Toast.LENGTH_LONG).show()
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
                        mOutputStream?.write(15)
                        Toast.makeText(activity, "15", Toast.LENGTH_LONG).show()
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

                        mOutputStream?.write(16)
                        Toast.makeText(activity, "16", Toast.LENGTH_LONG).show()
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
                        mOutputStream?.write(17)
                        Toast.makeText(activity, "17", Toast.LENGTH_LONG).show()
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
                        Toast.makeText(activity, "4", Toast.LENGTH_LONG).show()
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



        mSeekbar1?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
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
