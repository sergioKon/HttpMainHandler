package com.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.UUID;

public class Token {
    private long startTime;
    private long tokenTimeout;
    private final  Logger LOGGER= LogManager.getLogger(Token.class);
    public Token()  {
        String rootPath= System.getProperty("user.dir");

        Properties props = new Properties();
        String root = System.getProperty("user.dir").replace("\\", "/");
        String path = root + "/build/resources/main/";
        String configFile = path+"config.properties";
        try (FileInputStream fis = new FileInputStream(configFile)){
           props.load(fis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Use a property
        this.tokenTimeout = Long.parseLong(props.getProperty("tokenTimeout"));
        LOGGER.debug("token time out : {}" ,tokenTimeout);
    }
    public  String generate(){
        startTime=System.currentTimeMillis();
        LOGGER.debug(" the token has been generated ");
        return UUID.randomUUID().toString();
    }
    long getStartTime(){
        return startTime;
    }
    public long getTokenTimeout(){
        return this.tokenTimeout;
    }
}

