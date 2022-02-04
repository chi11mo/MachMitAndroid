package de.techfak.gse.dwenzel.server_com.server_controller;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Observable;

import de.techfak.gse.multiplayer.game.GameStatus;
import de.techfak.gse.multiplayer.server.request_body.StatusBody;
import de.techfak.gse.multiplayer.server.response_body.StatusResponse;

public class GameStatusServerInteraction extends Observable {
    private static final String JSON = "application/json";
    private Context context;
    private String finalUrl;
    private String url;
    private GameStatus gameStatus;
    private String name;

    /**
     * get gamestatus from server.
     *
     * @param context app context.
     * @param url     url server.
     * @param name    name to log to server.
     */
    public GameStatusServerInteraction(final Context context, final String url, final String name) {
        this.finalUrl = url + "/api/game/status?name=" + name;
        this.url = url;
        this.context = context;
        this.name = name;
        getGameStatusRequest();
    }

    /**
     * get GameStatus Request.
     */
    public void getGameStatusRequest() {

        final StringRequest request = buildRequestGet(finalUrl);
        final RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(request);

    }

    /**
     * Set Gamestatus for gameloob.
     *
     * @param gameStatus gamestatus.
     */
    public void setGameStatus(final GameStatus gameStatus) {
        this.gameStatus = gameStatus;

        setChanged();
        notifyObservers();

    }

    /**
     * get gamestatus.
     *
     * @return gamestatus.
     */
    public GameStatus getGameStatus() {
        return gameStatus;
    }

    /**
     * build get Request.
     *
     * @param finalUrl url to log into server.
     * @return request.
     */
    private StringRequest buildRequestGet(final String finalUrl) {
        final ObjectMapper objectMapper = new ObjectMapper();


        final Response.Listener<String> onResponse = response -> {

            try {
                StatusResponse
                        body = objectMapper.readValue(response, StatusResponse.class);
                setGameStatus(body.getStatus());
                Toast.makeText(context, body.getStatus().toString(), Toast.LENGTH_LONG).show();
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }


        };
        final Response.ErrorListener onError = error -> {


            // Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();


        };
        return new StringRequest(Request.Method.GET, finalUrl, onResponse, onError) {
            @Override
            public String getBodyContentType() {
                return JSON;
            }


            @Override
            protected Response<String> parseNetworkResponse(final NetworkResponse response) {
                int mStatusCode = response.statusCode;
                Log.d("Status Code Get GameStatus", String.valueOf(mStatusCode));
                return super.parseNetworkResponse(response);
            }

        };
    }

    /**
     * build post request.
     *
     * @param gameStatus current gamestatus.
     */
    public void setGameStatusRequestPOST(final GameStatus gameStatus) {
        final ObjectMapper objectMapper = new ObjectMapper();
        final String jsonBody;

        try {
            if (gameStatus == GameStatus.NOT_STARTED) {
                jsonBody = objectMapper.writeValueAsString(
                        new StatusBody(GameStatus.RUNNING, name));
            } else if (gameStatus == GameStatus.RUNNING) {
                jsonBody = objectMapper.writeValueAsString(
                        new StatusBody(GameStatus.FINISHED, name));
            } else {
                jsonBody = objectMapper.writeValueAsString(
                        new StatusBody(GameStatus.NOT_STARTED, name));
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        final StringRequest request = buildRequestPOST(jsonBody);
        final RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(request);

    }

    /**
     * method to request POSt change GamstStatus.
     *
     * @param jsonBody with change gamestatus.
     * @return gamestatus.
     */
    private StringRequest buildRequestPOST(final String jsonBody) {
        final ObjectMapper objectMapper = new ObjectMapper();


        final Response.Listener<String> onResponse = response -> {

            try {
                StatusResponse
                        body = objectMapper.readValue(response, StatusResponse.class);
                setGameStatus(body.getStatus());
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            getGameStatusRequest();
        };
        final Response.ErrorListener onError = error -> {

            // Toast.makeText(context, error, Toast.LENGTH_LONG).show();

        };
        return new StringRequest(Request.Method.POST, finalUrl, onResponse, onError) {
            @Override
            public String getBodyContentType() {
                return JSON;
            }

            @Override
            public byte[] getBody() {
                return jsonBody.getBytes(StandardCharsets.UTF_8);
            }

            @Override
            protected Response<String> parseNetworkResponse(final NetworkResponse response) {
                int mStatusCode = response.statusCode;
              //  Log.d("Status Code GameStatus POST", String.valueOf(mStatusCode));
                return super.parseNetworkResponse(response);
            }

        };


    }
}
