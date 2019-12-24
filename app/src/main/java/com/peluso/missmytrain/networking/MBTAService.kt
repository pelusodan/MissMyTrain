package com.peluso.missmytrain.networking

import com.peluso.missmytrain.models.MBTAResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryName

public interface MBTAService {
    /*
    *
    * predictions?filter[stop]=place-coecl
    * */

    @GET("predictions")
    fun getPredictions(@Query("filter[stop]") stopName: String): Call<MBTAResponse>
    
}