package com.peluso.missmytrain.models



// Class for the reply coming from the source
data class MBTAResponse(
    val data: List<Train>
){
}