package com.example.entities

import com.example.tables.*
import org.jetbrains.exposed.dao.*
import org.jetbrains.exposed.dao.id.EntityID
import org.joda.time.*
//代表一筆資料的class
class Task(id: EntityID<Int>) : IntEntity(id){
    companion object: IntEntityClass<Task>(Tasks)//這筆資料來源是Tasks

    var account:String by Tasks.account
    var password:String by Tasks.password
    var exp:String by Tasks.exp
}