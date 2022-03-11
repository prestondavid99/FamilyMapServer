package server;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import data.Location;
import dataaccess.DataAccessException;
import requestresult.LoadRequest;
import requestresult.LoadResult;
import service.LoadService;

import java.io.*;
import java.net.HttpURLConnection;

public class LoadHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        boolean success = false;

        try {
            if(exchange.getRequestMethod().equalsIgnoreCase("post")) {
                Gson gson = new Gson();
                LoadRequest loadRequest;

                InputStream reqBody = exchange.getRequestBody();
                InputStreamReader reader = new InputStreamReader(reqBody);
                loadRequest = gson.fromJson(reader, LoadRequest.class);

                LoadService loadService = new LoadService();
                LoadResult result = loadService.load(loadRequest);

                if(result.isSuccess()) {
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                }
                else {
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                }

                OutputStream resBody = exchange.getResponseBody();
                OutputStreamWriter streamWriter = new OutputStreamWriter(resBody);
                streamWriter.write(gson.toJson(result));
                streamWriter.flush();

                exchange.getResponseBody().close();
                success = true;
            }

            if (!success) {
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                exchange.getResponseBody().close();
            }

        }
        catch (IOException | DataAccessException e) {
            e.printStackTrace();
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
            exchange.getResponseBody().close();
        }
    }
}
