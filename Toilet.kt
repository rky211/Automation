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


class Toilet : Fragment() {

    var mSwitchB1: SwitchCompat?=null
    var mSwitchG1:SwitchCompat?=null
    var mOutputStream: OutputStream?=null

    var getState:Int=0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        var view =   inflater.inflate(R.layout.fragment_toilet, container, false)

        getState = this.arguments!!.getInt("STATE",0)

        mSwitchB1 = view.findViewById(R.id.toilet_switch_bulb1)
        mSwitchG1 =view.findViewById(R.id.toilet_switch_geezer1)

        mSwitchB1?.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
                mSwitchB1?.text ="ON"
                if (getState == 1){
                    try {

                        mOutputStream?.write(10)
                        Toast.makeText(activity, "10", Toast.LENGTH_LONG).show()
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
                        mOutputStream?.write(11)
                        Toast.makeText(activity, "11", Toast.LENGTH_LONG).show()
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
        mSwitchG1?.setOnCheckedChangeListener { buttonView, isChecked ->

            if (isChecked){
                mSwitchG1?.text ="ON"
                if (getState == 1){
                    try {

                        mOutputStream?.write(12)
                        Toast.makeText(activity, "12", Toast.LENGTH_LONG).show()
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
                mSwitchG1?.text ="OFF"
                if (getState == 1) {
                    try {
                        mOutputStream?.write(13)
                        Toast.makeText(activity, "13", Toast.LENGTH_LONG).show()
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
