package com.http.handlers

import com.http.readers.AllowHttpBaseReader
import com.http.readers.ApproveHttpBaseReader
import com.http.readers.BookingHttpBaseReader
import com.http.readers.CancelHtpBaseReader
import com.http.readers.DefaultHtpBaseReader
import com.sun.net.httpserver.HttpExchange
import com.sun.net.httpserver.HttpHandler
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

object MainHttpHandler : HttpHandler {
        @JvmStatic
        private val LOGGER: Logger = LogManager.getLogger(MainHttpHandler::class.java)

    override fun handle(httpExchange: HttpExchange) {
        val relativePath = httpExchange.requestURI.getPath()
        LOGGER.info(" main  handler started  {}", relativePath)

        val httpBaseReader = when (relativePath) {
            "/cancel" -> CancelHtpBaseReader(httpExchange)
            "/allow" -> AllowHttpBaseReader(httpExchange)
            "/booking" -> BookingHttpBaseReader(httpExchange)
            "/approve" -> ApproveHttpBaseReader(httpExchange)
            else -> {
                LOGGER.warn(" url is not valid ")
                DefaultHtpBaseReader(httpExchange)
            }
        }
        httpBaseReader.process()
        LOGGER.info(" the content of request =  {}", httpBaseReader.getBody())
        httpBaseReader.sendResponse()
    }


}
