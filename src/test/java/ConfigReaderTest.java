import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReaderTest {
    final static Logger logger= LogManager.getLogger(ConfigReaderTest.class);
    @BeforeAll
    static void init(){
        Configurator.setRootLevel(Level.INFO);
    }
    @Test
    void readProperties() throws IOException {
        Properties props = new Properties();
        try (InputStream inputStream = ClassLoader.getSystemResourceAsStream("config.properties")) {
            props.load(inputStream);
            for (Object key : props.keySet()) {
                logger.debug("key = {} value = {}" , key, props.get(key));
            }
        }
    }
}
