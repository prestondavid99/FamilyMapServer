package server;

import com.google.gson.Gson;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dataaccess.DataAccessException;
import requestresult.EventIdResult;
import service.EventIdService;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

public class EventIDHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            boolean success = false;
            if (exchange.getRequestMethod().toLowerCase().equals("get")) {
                Headers reqHeaders = exchange.getRequestHeaders();
                if (reqHeaders.containsKey("AuthToken")) { // TODO : Is this AuthToken or Authorization?
                    String authToken = reqHeaders.getFirst("AuthToken");
                    if (authToken.equals("afj232hj2332")) { // TODO : What do I put here?
                        Gson gson = new Gson();
                        String URI = exchange.getRequestURI().toString();
                        String[] array = URI.split("/");
                        String eventID = array[2];

                        EventIdService eventIdService = new EventIdService();
                        EventIdResult result = eventIdService.getEvent(authToken, eventID);

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

                }
            }
        } catch (IOException | DataAccessException e) {
            e.printStackTrace();
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
            exchange.getResponseBody().close();
        }
    }
}
