package com.example.responses
//用來回傳json轉出成data class
class TimesResponse(
    val id: Int,
    val fitnessdate: String,
    val pushup: Int,
    val sqart: Int,
    val plank: Int,
    val leg_lift: Int,
    val lunge_squat: Int,
    val bike_crunch: Int,
    val bridge: Int
)
