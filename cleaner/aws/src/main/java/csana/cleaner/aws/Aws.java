package csana.cleaner.aws;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import csana.cleaner.base.Cleaner;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Aws implements RequestStreamHandler {

    @Override
    public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) throws IOException {
        Cleaner cleaner = new Cleaner(System.getenv("redis"));
        outputStream.write(cleaner.get().getBytes());
    }
}
