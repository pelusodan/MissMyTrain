package com.peluso.missmytrain

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.peluso.missmytrain.adapter.RecyclerViewAdapter
import com.peluso.missmytrain.models.MBTAResponse
import com.peluso.missmytrain.networking.APIClient
import com.peluso.missmytrain.networking.MBTAService
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Thread.sleep


class MainActivity : AppCompatActivity() {

    companion object {
        val TAG = "MainActivity"
    }

    lateinit var recyclerView: RecyclerView
    lateinit var recyclerAdapter: RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerAdapter = RecyclerViewAdapter(this)

        val apiClient = APIClient.retrofitInstance!!.create(MBTAService::class.java)
        val call = apiClient.getPredictions("place-coecl")

        call.enqueue(object : Callback<MBTAResponse> {
            override fun onFailure(call: Call<MBTAResponse>, t: Throwable) {
                Log.wtf(TAG,t.message)
            }

            override fun onResponse(call: Call<MBTAResponse>, response: Response<MBTAResponse>) {
                if(!response.isSuccessful) {
                    Log.v(TAG,response.code().toString())
                    return
                }

                recyclerAdapter.setItems(response.body()!!.data)
                recyclerView = findViewById(R.id.recyclerView)
                recyclerView.layoutManager = LinearLayoutManager(parent)
                recyclerView.adapter = recyclerAdapter
            }
        })


    }

}

