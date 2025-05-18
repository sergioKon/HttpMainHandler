package com.base;

import com.http.handlers.LoginHandler;
import com.http.handlers.ProtectedHandler;
import com.sun.net.httpserver.HttpServer;
import org.apache.logging.log4j.*;

import java.io.IOException;

import org.apache.logging.log4j.core.config.Configurator;
import java.net.InetSocketAddress;

public class Init {
  //  private static final Logger LOGGER = LogManager.getLogger(Init.class);
    public static void main(String[] args) {
        final int port = 8000;
        final int backlog =0;
        System.out.println("App started!");
        System.exit(-2);
       /* HttpServer server = HttpServer.create(new InetSocketAddress(port), backlog);
        server.createContext("/login", new LoginHandler());
        server.createContext("/protected", new ProtectedHandler());
        server.setExecutor(null);
        server.start();
*/
        Configurator.setRootLevel(Level.INFO);
     //   LOGGER.info("Server running at http://localhost:{}", port);
    }
}