package server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;

public class FileHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {

//        boolean success = false;
//        try {
//            if (exchange.getRequestMethod().toLowerCase().equals("get")) {
//                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
//                exchange.getResponseBody().close();
//                success = true;
//            }
//
//            if (!success) {
//
//            }
//        }
        String urlPath = exchange.getRequestURI().toString();
        if ((urlPath == null) || urlPath.equals("/")) {
            urlPath = "/index.html";
        }

        String filePath = "web" + urlPath;
        File website = new File(filePath);
        if (website.exists()) {
            // Throw 404 error?
        }
        OutputStream responseBody = exchange.getResponseBody(); // TODO : No idea what these actually do
        Files.copy(website.toPath(), responseBody);
    }
}