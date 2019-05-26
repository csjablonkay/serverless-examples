package csana.viewer.base;

import org.json.simple.JSONObject;
import redis.clients.jedis.Jedis;

import java.util.function.Supplier;
import java.util.logging.Logger;

public class Viewer implements Supplier<String> {

    private static Logger logger = Logger.getGlobal();
    private final String host;

    public Viewer(String host) {
        this.host = host;
    }

    @Override
    public String get() {
        try(Jedis jedis = new Jedis(host))
        {
            jedis.select(0);
            final String actStr = jedis.get("act");
            final String msg = "Act value is: " + actStr;
            logger.info(msg);
            JSONObject ret = new JSONObject();
            ret.put("act", Integer.valueOf(actStr));
            return ret.toJSONString();
        }
    }

    public static void main(String[] args) {
        Viewer viewer = new Viewer("127.0.0.1");
        System.out.println(viewer.get());
    }
}
