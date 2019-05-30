package csana.incrementer.aws;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import csana.incrementer.base.Incrementer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Aws implements RequestStreamHandler {
    @Override
    public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) throws IOException {
        Incrementer incrementer = new Incrementer(System.getenv("redis"));
        outputStream.write(incrementer.get().getBytes());
    }
}
