package de.techfak.gse.dwenzel.server_com.ServerController;

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

import java.util.Observable;

import de.techfak.gse.multiplayer.server.response_body.DiceResponse;

public class DiceServerInteraction extends Observable {
    private Context context;
    private String finalUrl;
    private String url;
    private String name;
    private DiceResponse diceResponse;

    /**
     * Interaction between Die and Server.
     *
     * @param context app context.
     * @param url     server url.
     * @param name    name to server.
     */
    public DiceServerInteraction(final Context context, final String url, final String name) {
        this.finalUrl = url + "/api/game/dice?name=" + name;
        this.url = url;
        this.context = context;
        this.name = name;
        getDiceRequest();

    }

    /**
     * Method to get current dice.
     */
    public void getDiceRequest() {
        final StringRequest request = buildRequestGet(finalUrl);
        final RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(request);
    }

    private StringRequest buildRequestGet(final String finalUrl) {
        final ObjectMapper objectMapper = new ObjectMapper();


        final Response.Listener<String> onResponse = response -> {

            try {
                DiceResponse
                        body = objectMapper.readValue(response, DiceResponse.class);
                setDice(body);
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
                // Log.d("Status Code Get Dice", String.valueOf(mStatusCode));
                return super.parseNetworkResponse(response);
            }

        };
    }

    /**
     * Setting the get Response.
     *
     * @param body dieResponse.
     */
    private void setDice(final DiceResponse body) {
        this.diceResponse = body;
        setChanged();
        notifyObservers();

    }

    /**
     * get Die Response.
     *
     * @return dieResponse.
     */
    public DiceResponse getDiceResponse() {
        return diceResponse;
    }

}
