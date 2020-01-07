package com.peluso.missmytrain

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.peluso.missmytrain.adapter.RecyclerViewAdapter
import com.peluso.missmytrain.models.Location
import com.peluso.missmytrain.models.MBTAResponse
import com.peluso.missmytrain.models.MapQuestResponse
import com.peluso.missmytrain.networking.MBTAClient
import com.peluso.missmytrain.networking.MBTAService
import com.peluso.missmytrain.networking.MapQuestClient
import com.peluso.missmytrain.networking.MapQuestService
import io.reactivex.subjects.PublishSubject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


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

        // for MBTA API
        val MBTAClient = MBTAClient.retrofitInstance!!.create(MBTAService::class.java)
        val MBTACall = MBTAClient.getPredictions("place-coecl")

        // for MapQuest API
        val MapQuestClient = MapQuestClient.retrofitInstance!!.create(MapQuestService::class.java)
        val MapQuestCall = MapQuestClient.getRoute(getString(R.string.MapQuestAPIKey),
            Location("Boston",getString(R.string.Address),"Massachusetts"),
            // Currently hardcoded for Copley Station
            Location("Boston","640 Boylston Street","Massachusetts"),
            "pedestrian"
        )


        recyclerView.setOnClickListener {
            recyclerAdapter.clickSubject.onNext(
                recyclerAdapter.setTrains(t)
            )
        }
        /*MBTACall.enqueue(object : Callback<MBTAResponse> {
            override fun onFailure(call: Call<MBTAResponse>, t: Throwable) {
                Log.wtf(TAG,t.message)
            }

            override fun onResponse(call: Call<MBTAResponse>, response: Response<MBTAResponse>) {
                if(!response.isSuccessful) {
                    Log.v(TAG,response.code().toString())
                    return
                }

                recyclerAdapter.setTrains(response.body()!!.data)
                recyclerView = findViewById(R.id.recyclerView)
                recyclerView.layoutManager = LinearLayoutManager(parent)
                recyclerView.adapter = recyclerAdapter
            }
        })

        MapQuestCall.enqueue(object : Callback<MapQuestResponse> {
            override fun onFailure(call: Call<MapQuestResponse>, t: Throwable) {
                Log.wtf(TAG,t.message)
            }

            override fun onResponse(
                call: Call<MapQuestResponse>,
                response: Response<MapQuestResponse>
            ) {
                if(!response.isSuccessful) {
                    Log.v(TAG,response.code().toString())
                    return
                }

                recyclerAdapter.setWalkTimes(response.body()!!)
                recyclerView = findViewById(R.id.recyclerView)
                recyclerView.layoutManager = LinearLayoutManager(parent)
                recyclerView.adapter = recyclerAdapter
            }
        })*/


    }

}

