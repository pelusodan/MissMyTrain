package com.peluso.missmytrain.networking

import com.peluso.missmytrain.models.Location
import com.peluso.missmytrain.models.MapQuestResponse
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MapQuestService {
    @GET("route")
    fun getRoute(@Query("key") key: String,
                 @Query("from") from: String,
                 @Query("to") to: String,
                 @Query("routeType") routeType: String) : Observable<MapQuestResponse> // should be 'pedestrian'

}