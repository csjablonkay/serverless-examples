package csana.incrementer.openfaas;

import csana.incrementer.base.Incrementer;
import io.fission.undertow.Function;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Fission implements Function
{

    @Override public void call(HttpServerExchange exchange) throws IOException
    {
        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");
        exchange.setStatusCode(200);
        exchange.getResponseSender().send(new Incrementer(new String(Files.readAllBytes(Paths.get("/configs/" +
            exchange.getRequestHeaders().get("X-Fission-Function-Namespace").getFirst() +
        "/hello/REDIS_HOST_MASTER")))).get());
    }

}