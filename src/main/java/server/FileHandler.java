package server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.nio.file.Files;

public class FileHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {

        try {
            String urlPath = exchange.getRequestURI().toString();
            if ((urlPath == null) || urlPath.equals("/")) {
                urlPath = "/index.html";
            }

            String filePath = "web" + urlPath;
            File website = new File(filePath);
            OutputStream responseBody = exchange.getResponseBody();
            if (!website.exists()) {
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_NOT_FOUND, 0);
                exchange.close();
            }
            Files.copy(website.toPath(), responseBody);
            responseBody.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
}