package com.example.raj.automation


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.SwitchCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import java.io.IOException
import java.io.OutputStream


class Kitchen : Fragment() {


    var mSwitchB1: SwitchCompat?=null
    var mSwitchB2: SwitchCompat?=null

    var sendObj:SendRecvClass?=null
    var mOutputStream: OutputStream?=null

    var getState:Int=0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_kitchen, container, false)

        getState = this.arguments!!.getInt("STATE",0)

        mSwitchB1 = view.findViewById(R.id.kitchen_switch_bulb1)
        mSwitchB2 = view.findViewById(R.id.kitchen_switch_bulb2)

        mSwitchB1?.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
                mSwitchB1?.text ="ON"
                if (getState == 1){
                    try {

                        mOutputStream?.write(6)
                        Toast.makeText(activity, "6", Toast.LENGTH_LONG).show()
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
                        mOutputStream?.write(7)
                        Toast.makeText(activity, "7", Toast.LENGTH_LONG).show()
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

                        mOutputStream?.write(8)
                        Toast.makeText(activity, "8", Toast.LENGTH_LONG).show()
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
                        mOutputStream?.write(9)
                        Toast.makeText(activity, "9", Toast.LENGTH_LONG).show()
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
        return view


    }
    }
