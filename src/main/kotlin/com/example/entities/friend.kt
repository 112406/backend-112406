package com.example.entities

import com.example.tables.*
import org.jetbrains.exposed.dao.*
import org.jetbrains.exposed.dao.id.EntityID
import org.joda.time.*
//代表一筆資料的class
class friend(id: EntityID<Int>) : IntEntity(id){
    companion object: IntEntityClass<friend>(friends)//這筆資料來源是Tasks

    var friendid:String by friends.friendid
}