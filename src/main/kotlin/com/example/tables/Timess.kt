package com.example.tables

import org.jetbrains.exposed.dao.id.*
import org.jetbrains.exposed.sql.jodatime.*
//用object定義table的欄位和資料型態
//Tasks : IntIdTable()  繼承id欄位是autoinquiment和prime key
//複數的object
object Timess : IntIdTable(){
    val fitnessdate = datetime("fitnessdate")
    val pushup = integer("pushup").default(0)
    val sqart= integer("sqart").default(0)
    val plank = integer("plank").default(0)
    val leg_lift = integer("leg_lift").default(0)
    val lunge_squat = integer("lunge_squat").default(0)
    val bike_crunch = integer("bike_crunch").default(0)
    val bridge = integer("bridge").default(0)
}
