package com.peluso.missmytrain.viewmodels

import android.net.DnsResolver
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.peluso.missmytrain.R
import com.peluso.missmytrain.models.*
import com.peluso.missmytrain.networking.*
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.internal.operators.observable.ObservableAllSingle
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList
import kotlin.coroutines.CoroutineContext

class MainActivityViewModel(val rp: ResourceProvider,
                            val recyclcerFillCallback: (List<RecyclerViewCell>)-> Unit) : ViewModel() {
    companion object{
        val TAG = "MainActivityViewModel"
    }
    // flags for seeing if MBTA call and MAPQUEST call have both arrived
    var walkTime = "00:05:00"

    fun connect(walktimeSetter: (String)->Unit, dataAdapter: (List<RecyclerViewCell>)->Unit){
        val MBTAClient = MBTAClient.retrofitInstance!!.create(MBTAService::class.java)
        val MBTACall = MBTAClient.getPredictions("place-coecl")
        val mapQuestClient = MapQuestClient.retrofitInstance!!.create(MapQuestService::class.java)
        val mapQuestCall = mapQuestClient.getRoute(rp.getString(R.string.MapQuestAPIKey),
            Location("Boston",rp.getString(R.string.Address),"Massachusetts").toString(),
            // Currently hardcoded for Copley Station
            Location("Boston","640 Boylston Street","Massachusetts").toString(),
            "pedestrian"
        )
        Observable.zip(MBTACall,
            mapQuestCall,
            BiFunction<MBTAResponse,MapQuestResponse,List<RecyclerViewCell>> {mbta, mapquest ->
            return@BiFunction zipper(mapquest.route.formattedTime,mbta)
        } ) .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.v(TAG,it.toString())
                dataAdapter(it)
            }
       /* MBTACall
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .zipWith(mapQuestCall, BiFunction<MBTAResponse,MapQuestResponse,List<RecyclerViewCell>> {mbta, mapquest ->
                zipper(mapquest.route.formattedTime,mbta)
            } )
            .subscribe {  }
            }*/
            /*.subscribe({value->

            },{error->
                Log.v(TAG,error.message)
            },{
                Log.v(TAG,"Completed")
            })*/


        /*

        mapQuestCall.enqueue(object : Callback<MapQuestResponse>{
            override fun onFailure(call: Call<MapQuestResponse>, t: Throwable) {
                walkTime = "99:99:99"
            }

            override fun onResponse(
                call: Call<MapQuestResponse>,
                response: Response<MapQuestResponse>
            ) {
                if(!response!!.isSuccessful()){
                    return
                }
                Log.v(TAG, response.body().toString())
                walktimeSetter(response.body()!!.route.formattedTime)
            }

        })
        MBTACall.enqueue(object : Callback<MBTAResponse>{
            override fun onFailure(call: Call<MBTAResponse>, t: Throwable) {
                Log.wtf(TAG,t.message)
            }
            override fun onResponse(call: Call<MBTAResponse>, response: Response<MBTAResponse>) {
                if(!response!!.isSuccessful()){
                    Log.wtf(TAG,response.message())
                    return
                }
                dataAdapter(zipper(walkTime,response.body()!!))
            }
        })*/
    }

    private fun zipper(t: String, u: MBTAResponse): List<RecyclerViewCell>{
        var out = ArrayList<RecyclerViewCell>()
        u.data.forEach {
            out.add(RecyclerViewCell(t,it.attributes.arrival_time,it.relationships.route.data.id,it.attributes.direction_id))
        }
        return out
    }
    private fun zipperTest(mp: MapQuestResponse, mbtaResponse: MBTAResponse ): List<RecyclerViewCell>{
        var out = ArrayList<RecyclerViewCell>()
        mbtaResponse.data.forEach {
            out.add(RecyclerViewCell(mp.route.formattedTime,it.attributes.arrival_time,it.relationships.route.data.id,it.attributes.direction_id))
        }
        return out
    }


}