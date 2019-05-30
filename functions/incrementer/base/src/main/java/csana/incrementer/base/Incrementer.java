package csana.incrementer.base;

import org.json.simple.JSONObject;
import redis.clients.jedis.Jedis;

import java.util.function.Supplier;
import java.util.logging.Logger;

public class Incrementer implements Supplier<String> {

    private static Logger logger = Logger.getGlobal();
    private final String host;

    public Incrementer(String host) {
        this.host = host;
    }

    private static String increment(String valStr)
    {
        return (valStr != null) ? String.valueOf(Integer.valueOf(valStr) + 1) : "1";
    }

    @Override
    public String get() {
        try(Jedis jedis = new Jedis(host))
        {
            jedis.select(0);
            final String actStr = increment(jedis.get("act"));
            jedis.set("act", actStr);
            final String msg = "Act value is: " + actStr;
            logger.info(msg);
            JSONObject ret = new JSONObject();
            ret.put("act", Integer.valueOf(actStr));
            return ret.toJSONString();
        }
    }

    public static void main(String[] args) {
        Incrementer incrementer = new Incrementer("127.0.0.1");
        System.out.println(incrementer.get());
    }
}
