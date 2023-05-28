package com.example.tables

import org.jetbrains.exposed.dao.id.*
//用object定義table的欄位和資料型態
//Tasks : IntIdTable()  繼承id欄位是autoinquiment和prime key
//複數的object
object user_normal_datas : IntIdTable(){
    val gender = bool("gender")
    val height = integer("height").default(0)
    val weight = integer("weight").default(0)
    val mail = varchar("mail", length = 255)
}