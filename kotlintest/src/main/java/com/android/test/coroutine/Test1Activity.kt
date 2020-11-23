package com.android.test.coroutine

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.test.R
import kotlinx.coroutines.*
import java.text.SimpleDateFormat

class Test1Activity : AppCompatActivity() ,CoroutineScope by MainScope(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test1)
        //java
//        test1()
//        test2()
        //android
        //CoroutineScope by MainScope()
        launch {
            log("-----------")
            withContext(Dispatchers.IO){
                log("--------io")
            }
            withContext(Dispatchers.Default) {
                log("----default")
            }
            withContext(Dispatchers.Unconfined) {
                log("Unconfined--")
            }

        }
    }

    val dateFormate = SimpleDateFormat("HH:mm:SSS");
    val currentTime = { dateFormate.format(System.currentTimeMillis()) }
    fun log(msg: Any) = println("[${currentTime()}  ${Thread.currentThread().name}] $msg")


    fun test2() {
        GlobalScope.launch(Dispatchers.Main) {
            log("main")
        }
        GlobalScope.launch(Dispatchers.IO) {
            log("io")
        }
        GlobalScope.launch(Dispatchers.Default) {
            log("default")
        }
        GlobalScope.launch(Dispatchers.Unconfined) {
            log("Unconfined")
        }


    }

   fun test1(){
        //        Thread {
//            print("----hello word")
//        }.start()


//        thread {
//            print("----hello word1")
//        }
        //launch 必须放在上下文中  此处为runBlocking
        //旧的写法
        runBlocking {
            launch {
                println("-----hello word2")
            }
        }

        log("----------1")
        //新的写法
        val job = GlobalScope.launch(start = CoroutineStart.LAZY) {
            log("-----2")
            delay(500)
            log("------5")
        }

        log("----------3")
        Thread.sleep(3000)
//         job.join() //  切换线程  后续任务会切换到新的线程 star=default    =lazy  join之后的指令不一定执行在哪个线程  =atomic
        log("------4")

        //Thread.sleep(3000)


    }

}