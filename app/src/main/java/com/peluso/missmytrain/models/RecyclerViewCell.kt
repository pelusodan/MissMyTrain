package com.peluso.missmytrain.models

import android.animation.ArgbEvaluator
import android.graphics.Color
import android.util.Log
import androidx.core.graphics.green
import androidx.core.graphics.red
import com.google.gson.internal.bind.util.ISO8601Utils
import java.text.ParsePosition
import java.text.SimpleDateFormat


class RecyclerViewCell (
    var walkTime: String,
    val trainTime: String,
    val trainName: String,
    val trainDirection: Int
) {
    companion object {
        val TAG = "RecyclerViewCell"
    }
    // Difference calculated by walk time + TIME_GRADIENT_LIMIT
    private val UPPER_TIME_GRADIENT_LIMIT =  300000L
    private val LOWER_TIME_GRADIENT_LIMIT = -240000L

    fun displayGradient(): Color {
        val likelihood = calculateLikelihood()
        val invLikelihood = 1f - likelihood
        val green = Color.GREEN
        val red = Color.RED
        return Color.valueOf(ArgbEvaluator().evaluate(likelihood,red,green) as Int)
    }

    private fun calculateLikelihood(): Float{
        var mapQuestTime: Long
        var mbtaTime: Long
        try{
            mapQuestTime = parseTimeToMilli(walkTime)
            mbtaTime = ISO8601Utils.parse(trainTime, ParsePosition(0)).time
        }catch(e: Exception){
            Log.v(TAG,e.message)
            return 0f
        }
        val eta = System.currentTimeMillis() + mapQuestTime
        //return gradient if its in bewteen time restraints
        if( mbtaTime in eta + LOWER_TIME_GRADIENT_LIMIT .. eta + UPPER_TIME_GRADIENT_LIMIT){
            // time to spare at the station including bia
            val timeToSpare = (mbtaTime - eta) - LOWER_TIME_GRADIENT_LIMIT
            Log.v(TAG,"\ntimeToSpare: ${timeToSpare/1000}\n liklihood: ${ timeToSpare.toFloat() / (UPPER_TIME_GRADIENT_LIMIT - LOWER_TIME_GRADIENT_LIMIT)}\nMBTATIME:${mbtaTime/1000}\nETA:${eta/1000}")
            return timeToSpare.toFloat() / (UPPER_TIME_GRADIENT_LIMIT - LOWER_TIME_GRADIENT_LIMIT)

        }
        // return red if its greater than lower limit
        if(System.currentTimeMillis() + mapQuestTime >
            mbtaTime + LOWER_TIME_GRADIENT_LIMIT)  { return 0f}
        // return green if its less than upper limit
        if(System.currentTimeMillis() + mapQuestTime <
            mbtaTime + UPPER_TIME_GRADIENT_LIMIT) { return 1f}
        return -200f
    }

    private fun parseTimeToMilli(hourFormat: String): Long {
        var ms = 0L
        val split: List<String> = hourFormat.split(":")
        try {
            // hours
            ms += (split[0].toLong() * 60 * 60 * 1000)
            // minutes
            ms += split[1].toLong() * 60 * 1000
            // seconds
            ms += split[2].toLong() * 1000
            return ms
        } catch (e: Exception) {
            return -1
        }
    }

}