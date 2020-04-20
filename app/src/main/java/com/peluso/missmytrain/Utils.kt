package com.peluso.missmytrain

object Utils {
    fun displayTrainString(mbtaString: String): String{
        val split = mbtaString.split(":","T")
        return (split[1].toInt()%12).toString()+":"+split[2]+"."+split[3]
    }
    fun displayWalkString(mapquestString: String): String{
        val split = mapquestString.split(":")
        return "${split[1]}:${split[2]}"
    }
}