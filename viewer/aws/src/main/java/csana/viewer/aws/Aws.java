package csana.viewer.aws;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import csana.viewer.base.Viewer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Aws implements RequestStreamHandler {

    @Override
    public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) throws IOException {
        Viewer viewer = new Viewer(System.getenv("redis"));
        outputStream.write(viewer.get().getBytes());
    }

}
