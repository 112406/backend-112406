package com.example.plugins

import com.example.entities.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.css.*
import org.jetbrains.exposed.sql.transactions.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("有料")
        }
    }
    /*routing {
        route("/foo"){
            get("/bar"){
                call.respondText("foo/bar")
            }
            route("/baz"){
                get("/"){call.respondText("foo/baz")}
                get("/qux"){call.respondText("foo/baz/qux")}
            }
        }
    }
    routing {
        get("/html-dsl") {
            call.respondHtml {
                body {
                    h1 { +"HTML" }
                    ul {
                        for (n in 1..10) {
                            li { +"$n" }
                        }
                    }
                }
            }
        }
    }
    routing {
        get("/login/{user}") {
            if(call.parameters["user"] == "admin")
            call.respondHtml {
                body {
                    form(action = "/logiN", encType = FormEncType.applicationXWwwFormUrlEncoded, method = FormMethod.post) {
                        p {
                            +"帳號:"
                            textInput(name = "account")
                        }
                        p {
                            +"密碼:"
                            passwordInput(name = "password")
                        }
                        p {
                            submitInput() { value = "送出"}
                        }
                        p{
                            checkBoxInput() {value = "yes"}
                            +"YES?"
                            p{checkBoxInput() {value = "no"}
                            +"NO?"}
                        }
                    }
                }
            }
        }
            post("/logiN") {
                val tasks  = transaction{
                    Task.new{
                        account = account //數值從flutter傳過來
                        password = password //數值從flutter傳過來
                        exp = "0" //預設“0"  不知道為啥都只能設varchat 用int變出錯
                    }
                }
            }
    }*/
    //更新運動紀錄、exp
    routing {
        var id = 1;
        get("/update/pushup") {
            updatesport("pushup", id)
            call.respond(mapOf("data" to a))
        }
        get("/update/sqart") {
            updatesport("sqart", id)
            call.respond(mapOf("data" to a))
        }
        get("/update/plank") {
            updatesport("plank", id)
            call.respond(mapOf("data" to a))
        }
        get("/update/leg_lift") {
            updatesport("leg_lift", id)
            call.respond(mapOf("data" to a))
        }
        get("/update/lunge_squat") {
            updatesport("lunge_squat", id)
            call.respond(mapOf("data" to a))
        }
        get("/update/bike_crunch") {
            updatesport("bike_crunch", id)
            call.respond(mapOf("data" to a))
        }
        get("/update/bridge") {
            updatesport("bridge", id)
            call.respond(mapOf("data" to a))
        }
        get("/update/exp") {
            updatesport("exp", id)
            call.respond(mapOf("data" to a))
        }
    }
    //變更基本資料
    routing {
        var id = 1;
        get("/update/user/gender") {
            updateuser("gender", id)
            call.respond(mapOf("data" to a))
        }
        get("/update/user/height") {
            updateuser("height", id)
            call.respond(mapOf("data" to a))
        }
        get("/update/user/weight") {
            updateuser("weight", id)
            call.respond(mapOf("data" to a))
        }
        get("/update/user/mail") {
            updateuser("mail", id)
            call.respond(mapOf("data" to a))
        }
    }
    //新增帳號
    routing {
        get("/newaccount") {
            Task.new {
                account = "account 1"
                password = "123"
                exp = 0
            }
        }
    }
    //新增好友
    routing {
        get("/newfriend") {
            friend.new {
                friendid = "0002"
            }
        }
    }
}

//運動次數、exp增加fun
fun updatesport(x : String, id : Int){
    var a = transaction {
        var y = Times.findById(id)
        if(x == "pushup"){
            y?.pushup = y?.pushup?.plus(1)!!;}
        if(x == "sqart"){
            y?.sqart = y?.sqart?.plus(1)!!;}
        if(x == "plank"){
            y?.plank = y?.plank?.plus(1)!!;}
        if(x == "leg_lift"){
            y?.leg_lift = y?.leg_lift?.plus(1)!!;}
        if(x == "lunge_squat"){
            y?.lunge_squat = y?.lunge_squat?.plus(1)!!;}
        if(x == "bike_crunch"){
            y?.bike_crunch = y?.bike_crunch?.plus(1)!!;}
        if(x == "bridge"){
            y?.bridge = y?.bridge?.plus(1)!!;}
        if(x == "exp"){
            Task.findById(id)?.exp = Task.findById(id)?.exp?.plus(100)!!;}
    }
}
//變更基本資料fun
fun updateuser(x : String, id : Int){
    var a = transaction {
        var y = user_normal_data.findById(id)
        if(x == "gender"){
            y?.gender = !(y?.gender)!!}
        if(x == "height"){
            y?.height = y?.height?.plus(1)!!;}//看要改啥
        if(x == "weight"){
            y?.weight = y?.weight?.plus(1)!!;}//看要改啥
        if(x == "mail"){
            y?.mail = y?.mail.plus("aaa")}//看要改啥
    }
}
