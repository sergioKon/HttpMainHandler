package com.base

import java.util.Properties
import java.util.Timer
import java.util.TimerTask
import java.util.UUID

object TokenGenerator{
    var token: String = ""
    var timeout: Long=0

    fun generate()  {
        token= UUID.randomUUID().toString()
        val props = Properties()
        val inputStream = ClassLoader.getSystemResourceAsStream("config.properties")
        props.load(inputStream)
        timeout= (props.getProperty("tokenTimeout")).toLong()

        startTimer()
    }

    private fun startTimer() {
        val timer = Timer()
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                token= UUID.randomUUID().toString()
            }
        }, 0, (timeout*1000))
    }
}