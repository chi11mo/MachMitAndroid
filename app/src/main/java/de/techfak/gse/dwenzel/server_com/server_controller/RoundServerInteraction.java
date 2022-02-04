package de.techfak.gse.dwenzel.server_com.server_controller;

import android.content.Context;
import android.util.Log;

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

import de.techfak.gse.multiplayer.server.request_body.EndRoundBody;
import de.techfak.gse.multiplayer.server.response_body.RoundResponse;

public class RoundServerInteraction extends Observable {
    private Context context;
    private String finalUrl;
    private String name;
    private int roundNumber;

    /**
     * Round server Interaction.
     *
     * @param context app context.
     * @param url     url server.
     * @param name    name to server.
     */
    public RoundServerInteraction(final Context context, final String url, final String name) {
        this.finalUrl = url + "/api/game/round?name=" + name;
        this.context = context;
        this.name = name;

    }

    /**
     * Method to get current dice.
     */
    public void getRoundRequest() {
        final StringRequest request = buildRequestGet(finalUrl);
        final RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(request);
    }

    /**
     * Build Request to get current round.
     *
     * @param finalUrl url of server.
     * @return request.
     */
    private StringRequest buildRequestGet(final String finalUrl) {
        final ObjectMapper objectMapper = new ObjectMapper();


        final Response.Listener<String> onResponse = response -> {

            try {
                RoundResponse
                        body = objectMapper.readValue(response, RoundResponse.class);
                setRound(body.getRound());
                //Toast.makeText(context, body.getStatus().toString(), Toast.LENGTH_LONG).show();
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }


        };
        final Response.ErrorListener onError = error -> {

            Log.d("Dice Server Interaction", "Dice Get Error");

        };
        return new StringRequest(Request.Method.GET, finalUrl, onResponse, onError) {
            @Override
            public String getBodyContentType() {
                return "application/json";
            }


            @Override
            protected Response<String> parseNetworkResponse(final NetworkResponse response) {
                int mStatusCode = response.statusCode;
                Log.d("Status Code Get Round", String.valueOf(mStatusCode));
                return super.parseNetworkResponse(response);
            }

        };
    }

    /**
     * set Round number.
     *
     * @param round round number.
     */
    private void setRound(final int round) {
        this.roundNumber = round;
        setChanged();
        notifyObservers();
        //Log.d("RoundNumber", String.valueOf(round));

    }

    /**
     * getter for round number.
     *
     * @return roundNumber.
     */
    public int getRoundNumber() {
        return roundNumber;
    }

    /**
     * set next round.
     *
     * @param currentPoints current points.
     */
    public void setNextRoundRequestPOST(final int currentPoints) {
        final ObjectMapper objectMapper = new ObjectMapper();
        final String jsonBody;

        try {
            jsonBody = objectMapper.writeValueAsString(new EndRoundBody(name, currentPoints));

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
                RoundResponse
                        body = objectMapper.readValue(response, RoundResponse.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

        };
        final Response.ErrorListener onError = error -> {


        };
        return new StringRequest(Request.Method.POST, finalUrl, onResponse, onError) {
            @Override
            public String getBodyContentType() {
                return "application/json";
            }

            @Override
            public byte[] getBody() {
                return jsonBody.getBytes(StandardCharsets.UTF_8);
            }

            @Override
            protected Response<String> parseNetworkResponse(final NetworkResponse response) {
                int mStatusCode = response.statusCode;
                Log.d("Status Code Round POST", String.valueOf(mStatusCode));
                return super.parseNetworkResponse(response);
            }

        };


    }
}
