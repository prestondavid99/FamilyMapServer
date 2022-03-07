package server;

import com.google.gson.Gson;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import requestresult.LoginRequest;
import requestresult.LoginResult;
import requestresult.PersonIdResult;
import service.LoginService;
import service.PersonIdService;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class PersonIDHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        boolean success = false;
        if (exchange.getRequestMethod().toLowerCase().equals("get")) {
            Headers reqHeaders = exchange.getRequestHeaders();
            if (reqHeaders.containsKey("AuthToken")) {
                String authToken = reqHeaders.getFirst("AuthToken");
                if (authToken.equals("afj232hj2332")) { // TODO : What do I put here?
                    Gson gson = new Gson();

                    PersonIdService personIdService = new PersonIdService();
                    //PersonIdResult result = personIdService.personId(loginRequest);

                }
            }
        }
    }
}
