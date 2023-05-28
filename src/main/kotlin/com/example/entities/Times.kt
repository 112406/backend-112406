package com.example.entities

import com.example.tables.*
import org.jetbrains.exposed.dao.*
import org.jetbrains.exposed.dao.id.*
import org.joda.time.*
//代表一筆資料的class
class Times(id: EntityID<Int>) : IntEntity(id){
    companion object: IntEntityClass<Times>(Timess)//這筆資料來源是Tasks
    var fitnessdate: DateTime by Timess.fitnessdate
    var pushup:Int by Timess.pushup
    var sqart:Int by Timess.sqart
    var plank:Int by Timess.plank
    var leg_lift:Int by Timess.leg_lift
    var lunge_squat:Int by Timess.lunge_squat
    var bike_crunch:Int by Timess.bike_crunch
    var bridge:Int by Timess.bridge
}
