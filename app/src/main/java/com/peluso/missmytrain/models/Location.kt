package com.peluso.missmytrain.models

data class Location(
	val city: String,
	val street: String,
	val state: String
){
	override fun toString(): String {
		return "$street, $city, $state"
	}
}
