package com.peluso.missmytrain.data

data class Response(
	val jsonapi: Jsonapi? = null,
	val data: List<DataItem?>? = null
)
