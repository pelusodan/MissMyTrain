package com.peluso.missmytrain.adapter

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.peluso.missmytrain.view.MainActivity.Companion.TAG
import com.peluso.missmytrain.R
import com.peluso.missmytrain.Utils
import com.peluso.missmytrain.models.*
import io.reactivex.subjects.PublishSubject

class RecyclerViewAdapter(val context: Context) : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    var entry_list: List<RecyclerViewCell> = listOf()

    val clickSubject = PublishSubject.create<MBTAResponse>()


    class MyViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!){
        val walkTime: TextView = itemView!!.findViewById(R.id.walkTimeTextView)
        val trainTime: TextView = itemView!!.findViewById(R.id.trainTimeTextView)
        val trainName: TextView = itemView!!.findViewById(R.id.trainNameTextView)
        val trainDirection: TextView = itemView!!.findViewById(R.id.directionNameTextView)
        val cellLayout: LinearLayout = itemView!!.findViewById(R.id.recyclerViewCell)
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
        holder.trainTime.text = Utils.displayTrainString(entry_list[position].trainTime)
        holder.walkTime.text = Utils.displayWalkString(entry_list[position].walkTime)
        holder.trainDirection.text = if (entry_list[position].trainDirection==1)  "Inbound" else "Outbound"
        holder.cellLayout.background = ColorDrawable(entry_list[position].displayGradient().toArgb())
        Log.v(TAG,"VIEW BINDED")
    }

    fun setData(cells: List<RecyclerViewCell>){
        entry_list = cells
    }

    // function for setting walk times
    fun setWalkTimes(response: MapQuestResponse) {
        var cells = this.entry_list.toMutableList()
        for(i in 0 until cells.size-1){
            cells[i].walkTime = response.route.formattedTime
        }
        notifyDataSetChanged()
    }



}
