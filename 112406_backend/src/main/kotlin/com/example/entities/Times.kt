package com.example.entities

import com.example.tables.*
import org.jetbrains.exposed.dao.*
import org.jetbrains.exposed.dao.id.EntityID
import org.joda.time.*
//代表一筆資料的class
class Times(id: EntityID<Int>) : IntEntity(id){
    companion object: IntEntityClass<Times>(Timess)//這筆資料來源是Tasks
    var fitnessdate: DateTime by Timess.fitnessdate
    var pushup:String by Timess.pushup
    var sqart:String by Timess.sqart
    var plank:String by Timess.plank
}