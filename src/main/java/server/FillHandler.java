package server;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dataaccess.DataAccessException;
import requestresult.FillResult;
import requestresult.LoadRequest;
import requestresult.LoadResult;
import service.FillService;

import java.io.*;
import java.net.HttpURLConnection;

import static java.lang.Integer.parseInt;

public class FillHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException { // /fill/susan/4
        boolean success = false;

        try {
            if (exchange.getRequestMethod().equalsIgnoreCase("post")) {
                Gson gson = new Gson();
                String URI = exchange.getRequestURI().toString();
                String[] array = URI.split("/");
                LoadRequest loadRequest;
                FillResult result = null;
                FillService fillService = new FillService();
                if (array.length == 3) {
                    result = fillService.fill(array[2], 4);
                } else if (array.length == 4) {
                    result = fillService.fill(array[2], parseInt(array[3]));
                } else {
                    throw new IOException("Error in URL");
                }

                assert result != null;
                if (result.isSuccess()) {
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                } else {
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

        } catch (IOException | DataAccessException e) {
            e.printStackTrace();
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
            exchange.getResponseBody().close();
        }

    }
}
