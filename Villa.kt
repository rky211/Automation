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
import java.lang.Exception


class Villa : Fragment() {


    var mSwitchB1: SwitchCompat?=null
    var mSwitchB2: SwitchCompat?=null
    var mSwitchB3: SwitchCompat?=null
    var sendObj:SendRecvClass?=null
    var mOutputStream:OutputStream?=null

    var getState:Int=0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view =   inflater.inflate(R.layout.fragment_villa, container, false)

        mSwitchB1 = view.findViewById(R.id.villa_switch_bulb1)
        mSwitchB2 =view.findViewById(R.id.villa_switch_bulb2)
        mSwitchB3 = view.findViewById(R.id.villa_switch_bulb3)

        getState = this.arguments!!.getInt("STATE",0)

        mSwitchB1?.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
                mSwitchB1?.text ="ON"
                if (getState == 1){
                    try {

                        mOutputStream?.write(18)
                        Toast.makeText(activity, "18", Toast.LENGTH_LONG).show()
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
                        mOutputStream?.write(19)
                        Toast.makeText(activity, "19", Toast.LENGTH_LONG).show()
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

                        mOutputStream?.write(20)
                        Toast.makeText(activity, "20", Toast.LENGTH_LONG).show()
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
                        mOutputStream?.write(21)
                        Toast.makeText(activity, "21", Toast.LENGTH_LONG).show()
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

                        mOutputStream?.write(22)
                        Toast.makeText(activity, "22", Toast.LENGTH_LONG).show()
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
                        mOutputStream?.write(23)
                        Toast.makeText(activity, "23", Toast.LENGTH_LONG).show()
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
