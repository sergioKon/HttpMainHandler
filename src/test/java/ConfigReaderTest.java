import com.base.Token;
import org.junit.jupiter.api.Test;

public class ConfigReaderTest {
    @Test void readConfig(){
        Token token = new Token();
        assert token.getTokenTimeout() == 3600;
    }
}
