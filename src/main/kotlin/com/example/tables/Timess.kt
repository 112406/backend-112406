package com.example.tables

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.jodatime.datetime
//用object定義table的欄位和資料型態
//Tasks : IntIdTable()  繼承id欄位是autoinquiment和prime key
//複數的object
object Timess : IntIdTable(){
    val fitnessdate = datetime("fitnessdate")
    val pushup = varchar("pushup", 25)
    val sqart= varchar("sqart", 25)
    val plank = varchar("plank", 25)

}
