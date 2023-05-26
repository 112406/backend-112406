package com.example.responses
//用來回傳json轉出成data class
class TaskResponse(
    val id: Int,
    val account: String,
    val password: String,
    val exp: String
)