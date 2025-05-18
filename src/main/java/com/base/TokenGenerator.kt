package com.base

import org.apache.logging.log4j.LogManager
import java.util.Properties
import java.util.Timer
import java.util.TimerTask
import java.util.UUID

object TokenGenerator{
    var token: String = ""
    private var timeout: Long=0
    private val LOGGER = LogManager.getLogger("TokenGenerator")

    fun generate()  {
        token= UUID.randomUUID().toString()
        val props = Properties()
        val inputStream = ClassLoader.getSystemResourceAsStream("config.properties")
        props.load(inputStream)
        timeout= (props.getProperty("tokenTimeout")).toLong()
        LOGGER.debug(" token time out = "+ timeout+ "sec")
        startTimer()
    }

    private fun startTimer() {
        val timer = Timer()
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                token= UUID.randomUUID().toString()
                LOGGER.debug(" new token  $token")
            }
        }, 0, (timeout*1000))
    }
}