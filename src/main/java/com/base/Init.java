package com.base;

import com.http.handlers.MainHttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.apache.logging.log4j.*;
import java.io.IOException;

import org.apache.logging.log4j.core.config.Configurator;

import java.io.InputStream;
import java.net.InetSocketAddress;
import java.util.Properties;

public class Init {
    private static final Logger LOGGER = LogManager.getLogger(Init.class);
    public static Properties props = new Properties();
    public static void main(String[] args) {
        props.setProperty("backlog","0");
        try (InputStream inputStream = ClassLoader.getSystemResourceAsStream("config.properties")) {
            props.load(inputStream);
        } catch (IOException e) {
           LOGGER.fatal(" cannot load property file");
           System.exit(-1);
        }
        final int port = Integer.parseInt(props.getProperty("port"));
        final int backlog = Integer.parseInt(props.getProperty("backlog"));

        HttpServer server;
        try {
            server = HttpServer.create(new InetSocketAddress(port), backlog);

            server.createContext("/cancel", new MainHttpHandler());
            server.createContext( "/allow", new MainHttpHandler());
            server.createContext("/booking", new MainHttpHandler());
            server.createContext("/approve", new MainHttpHandler());

            server.setExecutor(null);
            server.start();

            Configurator.setRootLevel(Level.INFO);
            LOGGER.info("Server running at http://localhost:{}", port);
        } catch (IOException e) {
           LOGGER.fatal(" cannot run server check if port is a busy");
        }
    }
}