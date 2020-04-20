package com.peluso.missmytrain.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.peluso.missmytrain.R
import com.peluso.missmytrain.adapter.RecyclerViewAdapter
import com.peluso.missmytrain.models.*
import com.peluso.missmytrain.viewmodels.MainActivityViewModel
import com.peluso.missmytrain.viewmodels.MainActivityViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    companion object {
        val TAG = "MainActivity"
    }

    lateinit var recyclerAdapter: RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // resource provider for secret strings
        val vmFactory = MainActivityViewModelFactory(ResourceProvider(this), this::fillRecyclerView)
        val model = ViewModelProvider(this, vmFactory).get(MainActivityViewModel::class.java)
        recyclerAdapter = RecyclerViewAdapter(this)
        recyclerView.adapter = recyclerAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        mainButton.setOnClickListener {
            Log.v(TAG,"Attempting to connect")
            model.connect({
                model.walkTime = it
            },{
                fillRecyclerView(it)
            })
        }
    }

    private fun fillRecyclerView(cells: List<RecyclerViewCell>){
        recyclerAdapter.setData(cells)
        recyclerAdapter.notifyDataSetChanged()

    }
}

