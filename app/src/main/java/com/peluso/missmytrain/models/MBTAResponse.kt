package com.peluso.missmytrain.models

import com.peluso.missmytrain.data.Response

// Class for the reply coming from the source
data class MBTAResponse(
    val status: String,
    val copyright: String,
    val last_updated: String = "",
    val num_results: Int,
    val results: Response
){

    // THE first class we're making is for the top headlines api
    // for practice with API formatting and retrofit
    /*
    * "status": "OK",
	"copyright": "Copyright (c) 2019 The New York Times Company. All Rights Reserved.",
	"section": "technology",
	"last_updated": "2019-12-23T13:47:15-05:00",
	"num_results": 28,
	"results":
    * */






}