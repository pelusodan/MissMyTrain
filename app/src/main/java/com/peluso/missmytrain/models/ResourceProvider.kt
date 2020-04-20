package com.peluso.missmytrain.models

import android.content.Context
// for grabbing resources for the viewModel
class ResourceProvider(val context: Context) {

    fun getString(resID: Int): String{return context.getString(resID)}

}