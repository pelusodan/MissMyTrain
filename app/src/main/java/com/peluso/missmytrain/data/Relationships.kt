package com.peluso.missmytrain.data

data class Relationships(
	val route: Route? = null,
	val trip: Trip? = null,
	val stop: Stop? = null,
	val vehicle: Vehicle? = null
)
