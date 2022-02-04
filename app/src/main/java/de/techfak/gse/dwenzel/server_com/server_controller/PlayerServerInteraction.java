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

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import de.techfak.gse.multiplayer.server.response_body.PlayerListResponse;
import de.techfak.gse.multiplayer.server.response_body.PlayerResponse;

public class PlayerServerInteraction extends Observable {
    private Context context;
    private String finalUrl;
    private List<PlayerResponse> players = new ArrayList<>();

    /**
     * Player server Interaction.
     *
     * @param context app context.
     * @param url     url server.
     * @param name    name to server.
     */
    public PlayerServerInteraction(final Context context, final String url, final String name) {
        this.finalUrl = url + "/api/game/players?name=" + name;
        this.context = context;
        // getPlayerRequest();
    }


    /**
     * Method to get current dice.
     */
    public void getPlayerRequest() {
        final StringRequest request = buildRequestGet(finalUrl);
        final RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(request);
    }

    /**
     * buidl get Request.
     *
     * @param finalUrl server url.
     * @return request.
     */
    private StringRequest buildRequestGet(final String finalUrl) {
        final ObjectMapper objectMapper = new ObjectMapper();


        final Response.Listener<String> onResponse = response -> {

            try {
                PlayerListResponse
                        body = objectMapper.readValue(response, PlayerListResponse.class);
                setPlayerList(body.getPlayers());
                //Toast.makeText(context, body.getStatus().toString(), Toast.LENGTH_LONG).show();
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }


        };
        final Response.ErrorListener onError = error -> {

            Log.d("Player Server Interaction", "get Error");
            // Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();


        };
        return new StringRequest(Request.Method.GET, finalUrl, onResponse, onError) {
            @Override
            public String getBodyContentType() {
                return "application/json";
            }


            @Override
            protected Response<String> parseNetworkResponse(final NetworkResponse response) {
                int mStatusCode = response.statusCode;
                Log.d("Status Code Get Player List", String.valueOf(mStatusCode));
                return super.parseNetworkResponse(response);
            }

        };
    }

    /**
     * set player list.
     *
     * @param players playerlist.
     */
    private void setPlayerList(final List<PlayerResponse> players) {
        // Log.d("Player List", String.valueOf(players));
        this.players = players;
        setChanged();
        notifyObservers();
    }

    /**
     * player list.
     * @return player list.
     */
    public List<PlayerResponse> getPlayers() {
        return players;
    }
}
