package com.example

import com.example.entities.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.example.plugins.*
import com.example.responses.*
import com.example.tables.*
import io.ktor.client.request.forms.*
import io.ktor.serialization.jackson.*
import io.ktor.server.html.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.css.*
import kotlinx.html.*
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
    //configureMonitoring()
    configureRouting()
    /*安裝feature jackson (已安裝在build.gradle和gradle.properties)
    install(ContentNegotiation){
        jackson{

        }
    }*/
    //連接資料庫
    //todo_api是自己取名
    Database.connect(
        url = "jdbc:h2:mem:todo_api;DB_CLOSE_DELAY=-1",
        driver = "org.h2.Driver"
    )
    //SchemaUtils建資料表table
    transaction {
        SchemaUtils.create(Tasks)
        SchemaUtils.create(Timess)
    }
    //導入資料
    //程式碼都要放在transaction裡面才能正確操作資料庫
    transaction {
        Task.new{
            account = "account 1"
            password = "123"
            exp = "0"
            Times.new{
                pushup = 3.toString()
                sqart = "1"
                plank = "1"
                fitnessdate = DateTime.now()
            }
        }
        Task.new{
            account = "account 2"
            password = "456"
            exp = "0"
            Times.new{
                pushup = "2"
                sqart = "2"
                plank = "2"
                fitnessdate = DateTime.now()
            }
        }
        Task.new{
            account = "account 3"
            password = "789"
            exp = "0"
            Times.new{
                pushup = "3"
                sqart = "3"
                plank = "3"
                fitnessdate = DateTime.now()
            }
        }
    }
    //連線
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
            //上面用map這邊也用map     data to tasks是說data從tasks(複數的)來的
            call.respond(mapOf("data" to tasks))
        }
    }
    routing {
        get("/api/Timess") {
            val Timess  = transaction{   //val tasks = 是把it都打完後再設變數包起來
                Times.all().map {    //.map把一個個Task轉成TaskResponse
                    TimesResponse(
                        it.id.value,
                        it.fitnessdate.toString("yyyy-MM-dd"),
                        it.pushup,
                        it.sqart,
                        it.plank
                    )
                }
            }
            //上面用map這邊也用map     data to tasks是說data從tasks(複數的)來的
            call.respond(mapOf("data" to Timess))
        }
    }
}


