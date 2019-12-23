package com.peluso.missmytrain.networking

import com.peluso.missmytrain.models.MBTAResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

public interface MBTAService {

    //api key
    /*
    * https://api.nytimes.com/svc/topstories/v2/technology.json?
    * api-key=0xYqGDRykcAmLaa9lsalwtiseQs05TID
    * */

    @GET("{section}.json")
    fun getStoryTimes(@Path("section") section: String,
                      @Query("apiKey") apiKey: String
    ) : Call<MBTAResponse>
    
}