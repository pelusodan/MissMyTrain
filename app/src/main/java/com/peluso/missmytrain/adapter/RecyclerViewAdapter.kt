package com.peluso.missmytrain.adapter

import android.content.Context
import android.graphics.Movie
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.peluso.missmytrain.MainActivity.Companion.TAG
import com.peluso.missmytrain.R
import com.peluso.missmytrain.models.RecyclerViewCell
import com.peluso.missmytrain.models.Train
import kotlinx.android.synthetic.main.recyclerview_cell.view.*

class RecyclerViewAdapter(val context: Context) : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    var entry_list: List<RecyclerViewCell> = listOf()



    class MyViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!){
        val walkTime: TextView = itemView!!.findViewById(R.id.walkTimeTextView)
        val trainTime: TextView = itemView!!.findViewById(R.id.trainTimeTextView)
        val trainName: TextView = itemView!!.findViewById(R.id.trainNameTextView)
        val trainDirection: TextView = itemView!!.findViewById(R.id.directionNameTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_cell,parent,false)
        Log.v(TAG,"VIEW CREATED")
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        Log.v(TAG,"ITEM COUNT GOT")
        return entry_list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.trainName.text = entry_list[position].trainName
        holder.trainTime.text = entry_list[position].trainTime
        holder.walkTime.text = entry_list[position].walkTime
        holder.trainDirection.text = if (entry_list[position].trainDirection==1)  "Inbound" else "Outbound"
        Log.v(TAG,"VIEW BINDED")
    }

    fun setItems(trains: List<Train>) {
        val cells: ArrayList<RecyclerViewCell> = ArrayList()
        for(train in trains) {
            cells.add(RecyclerViewCell("99min",
                train.attributes.arrival_time,
                train.relationships.route.data.id,
                train.attributes.direction_id))
        }
        entry_list = cells

    }



}
