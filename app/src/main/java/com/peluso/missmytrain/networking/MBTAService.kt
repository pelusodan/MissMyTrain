package com.peluso.missmytrain.networking

import com.peluso.missmytrain.models.MBTAResponse
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryName

public interface MBTAService {

    @GET("predictions")
    fun getPredictions(@Query("filter[stop]") stopName: String): Observable<MBTAResponse>
    
}