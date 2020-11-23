package com.example.restapi.models

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.restapi.R

class CustomListAdapter(private val context:Activity,private val id:Array<Int>,private val title:Array<String>):ArrayAdapter<String>(context, R.layout.custom_list,title) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
           val inflator = context.layoutInflater;
           val rowView =inflator.inflate(R.layout.custom_list,null,true)

          val idtext =rowView.findViewById<TextView>(R.id.idview)
         val titletext = rowView.findViewById<TextView>(R.id.titleview)

         idtext.text="id:- " +id[position].toString()
         titletext.text  = title[position].toString()

        return rowView
    }

}
