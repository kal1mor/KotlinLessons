package com.example.kotlinproject.utils.coroutins

import android.util.Log
import kotlinx.coroutines.*

class CoroutinsExamples {

    fun testCoroutinsJoin(){
        CoroutineScope(Dispatchers.IO).launch{
            val job =  launch {
                for (i in 1 .. 5){
                    Log.w("called", "$i")
                    delay(400L)
                }
            }

            Log.w("start" , "started")
            job.join()
            Log.w("finish" , "finished")
        }
    }

    fun testCoroutinCancel(){
        CoroutineScope(Dispatchers.IO).launch{
            val job =  launch {
                for (i in 1 .. 5){
                    Log.w("called", "$i")
                    delay(400L)
                }
            }

            Log.w("start" , "started")
            job.cancel()
            Log.w("finish" , "${coroutineContext[CoroutineName]}")
        }
    }
}