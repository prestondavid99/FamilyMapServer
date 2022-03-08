package server;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dataaccess.DataAccessException;
import requestresult.PersonResult;
import service.EventService;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

public class EventHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        boolean success = false;

        try {
            if (exchange.getRequestMethod().toLowerCase().equals("get")) {
                Gson gson = new Gson();

                EventService eventService = new EventService();
                PersonResult result = eventService.getEvents(); // TODO : Where do I get this AuthToken?

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

