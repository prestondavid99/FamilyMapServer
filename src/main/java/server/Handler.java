package server;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import requestresult.LoadRequest;
import requestresult.LoadResult;
import requestresult.Request;
import requestresult.Result;
import service.LoadService;

import java.io.InputStream;
import java.io.InputStreamReader;

public class Handler {

    public Request fromJson(HttpExchange exchange, Request request) {
        Gson gson = new Gson();

        InputStream reqBody = exchange.getRequestBody();
        InputStreamReader reader = new InputStreamReader(reqBody);
        request = gson.fromJson(reader, Request.class);

        return request;
    }
}
