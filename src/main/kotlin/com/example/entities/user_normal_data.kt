package com.example.entities

import com.example.tables.*
import org.jetbrains.exposed.dao.*
import org.jetbrains.exposed.dao.id.*
//代表一筆資料的class
class user_normal_data(id: EntityID<Int>) : IntEntity(id){
    companion object: IntEntityClass<user_normal_data>(user_normal_datas)//這筆資料來源是Tasks

    var gender:Boolean by user_normal_datas.gender
    var height:Int by user_normal_datas.height
    var weight:Int by user_normal_datas.weight
    var mail:String by user_normal_datas.mail
}