package com.example

import com.example.entities.*
import com.example.plugins.*
import com.example.responses.*
import com.example.tables.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.css.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.*
import org.joda.time.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureTemplating()
    configureSerialization()
    configureMonitoring()
    configureRouting()
    //連接資料庫
    Database.connect(
        url = "jdbc:h2:mem:todo_api;DB_CLOSE_DELAY=-1",
        driver = "org.h2.Driver"
    )
    //SchemaUtils建資料表table
    transaction {
        SchemaUtils.create(Tasks)
        SchemaUtils.create(Timess)
        SchemaUtils.create(user_normal_datas)
        SchemaUtils.create(friends)
    }
    //導入資料
    //程式碼都要放在transaction裡面才能正確操作資料庫
    //先建立範例
    transaction {
            Task.new {
                account = "account 1"
                password = "123"
                exp = 200
            }
            Times.new{
                pushup = 3
                sqart = 1
                plank = 1
                fitnessdate = DateTime.now()
            }
            user_normal_data.new{
                gender = true
                height = 150
                weight = 40
                mail = "123@gmail.com"
            }
            friend.new{
                friendid = "0001"
            }
    }
    //顯示Task所有資料
    routing {
        get("/api/tasks") {
            val tasks  = transaction{   //val tasks = 是把it都打完後再設變數包起來
                Task.all().map {    //.map把一個個Task轉成TaskResponse
                    TaskResponse(
                        it.id.value,
                        it.account,
                        it.password,
                        it.exp
                    )
                }

            }
            //上面用map這邊也用map     data to tasks是說data從tasks(複數的有加s)來的
            call.respond(mapOf("data" to tasks))
        }
    }
    //輸入完帳號密碼 確定註冊按鈕按下去新增
    routing {
        get("/signup") {
            val tasks  = transaction{
                Task.new{
                    account = "account" //數值從flutter傳過來
                    password = "password" //數值從flutter傳過來
                    exp = 0 //預設“0"  不知道為啥都只能設varchat 用int變出錯
                }
            }
        }
    }
    routing {
        get("/api/user_normal_datas") {
            val user_normal_datas = transaction {
                user_normal_data.all().map {
                    user_normal_dataResponse(
                        it.id.value,
                        it.gender,
                        it.height,
                        it.weight,
                        it.mail
                    )
                }
            }
            call.respond(mapOf("data" to user_normal_datas))
        }
    }
    //顯示Timess所有資料
    routing {
        get("/api/Timess") {
            val Timess  = transaction{   //val tasks = 是把it都打完後再設變數包起來
                Times.all().map {    //.map把一個個Task轉成TaskResponse
                    TimesResponse(
                        it.id.value,
                        it.fitnessdate.toString("yyyy-MM-dd"),
                        it.pushup,
                        it.sqart,
                        it.plank,
                        it.leg_lift,
                        it.lunge_squat,
                        it.bike_crunch,
                        it.bridge
                    )
                }
            }
            //上面用map這邊也用map     data to tasks是說data從tasks(複數的)來的
            call.respond(mapOf("data" to Timess))
        }
    }
    routing {
        get("/api/friends") {
            val friends  = transaction{   //val tasks = 是把it都打完後再設變數包起來
                friend.all().map {    //.map把一個個Task轉成TaskResponse
                    friendResponse(
                        it.id.value,
                        it.friendid
                    )
                }
            }
            //上面用map這邊也用map     data to tasks是說data從tasks(複數的)來的
            call.respond(mapOf("data" to friends))
        }
    }
    //完成pushup 把數值加入歷史紀錄
    routing {
        get("/completed"){
        update()
        call.respond(mapOf("data" to a))
        }
    }
}
fun update(){
    var a = transaction {
        var y = Times.findById(1)
        y?.pushup = y?.pushup?.plus(1)!!;
    }
}

/*fun update(){
    var x = 1   //要加的量從flutter傳來 或直接不用這個變數
    var a = transaction {
        var y = Times.findById(1)
        y?.pushup = (y?.pushup?.toInt()?.plus(x)).toString()
    }
}*/
//函式範例
/*    routing {
        get("/completed"){
        update()
        call.respond(mapOf("data" to a))
        }
    }
}

fun update(){
    var x = 1
    var a = transaction {
        var y = Times.findById(1)
        y?.pushup = (y?.pushup?.toInt()?.plus(x)).toString()
    }
}*/


