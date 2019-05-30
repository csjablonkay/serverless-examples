package csana.cleaner.base;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import redis.clients.jedis.Jedis;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.function.Supplier;
import java.util.logging.Logger;

public class Cleaner implements Supplier<String> {

    private static Logger logger = Logger.getGlobal();
    private final String host;

    public Cleaner(String host) {
        this.host = host;
    }

    @Override
    public String get() {
        try(Jedis jedis = new Jedis(host))
        {
            jedis.select(0);
            jedis.set("act", "0");
            final String msg = "The act is set to 0!";
            logger.info(msg);
            JSONObject ret = new JSONObject();
            ret.put("act", 0);
            return ret.toJSONString();
        }
    }

    public static void main(String[] args) {
        Cleaner cleaner = new Cleaner("127.0.0.1");
        System.out.println(cleaner.get());
    }
}
