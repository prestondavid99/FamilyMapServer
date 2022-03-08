package server;

import com.google.gson.Gson;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dataaccess.DataAccessException;
import requestresult.LoginRequest;
import requestresult.LoginResult;
import requestresult.PersonIdResult;
import service.LoginService;
import service.PersonIdService;

import java.io.*;
import java.net.HttpURLConnection;

public class PersonIDHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            boolean success = false;
            if (exchange.getRequestMethod().toLowerCase().equals("get")) {
                Headers reqHeaders = exchange.getRequestHeaders();
                if (reqHeaders.containsKey("AuthToken")) {
                    String authToken = reqHeaders.getFirst("AuthToken");
                    if (authToken.equals("afj232hj2332")) { // TODO : What do I put here?
                        Gson gson = new Gson();

                        PersonIdService personIdService = new PersonIdService();
                        PersonIdResult result = personIdService.getPerson(authToken);

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
        } catch (IOException e) {
            e.printStackTrace();
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
            exchange.getResponseBody().close();
        }
    }
}
