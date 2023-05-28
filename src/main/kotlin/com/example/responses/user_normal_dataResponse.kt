package com.example.responses
//用來回傳json轉出成data class
class user_normal_dataResponse(
    val id: Int,
    val gender: Boolean,
    val height: Int,
    val weight: Int,
    val mail: String
)