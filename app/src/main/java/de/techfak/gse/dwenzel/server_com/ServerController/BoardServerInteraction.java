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

import de.techfak.gse.multiplayer.server.response_body.BoardResponse;

public class BoardServerInteraction extends Observable {


    private String boardString;

    /**
     * INteraction with board and server to get Board Strong Layout from server.
     * @param context app context.
     * @param url server url.
     * @param name name of one player logged in server.
     */
    public BoardServerInteraction(final Context context, final String url, final String name) {
        final StringRequest request = buildRequest(url + "/api/game/board?name=", name);
        final RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(request);
    }


    /**
     * Build the Request to check is Server Online.
     *
     * @param url server url.
     * @param name name logged player.
     * @return StringRequest.
     */
    private StringRequest buildRequest(final String url, final String name) {
        final ObjectMapper objectMapper = new ObjectMapper();
        final String finalUrl = url + name;

        final Response.Listener<String> onResponse = response -> {

            try {
                BoardResponse
                        body = objectMapper.readValue(response, BoardResponse.class);
                setBoardString(body.getBoard());
                // Toast.makeText(context, body.getMessage(), Toast.LENGTH_LONG).show();
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
                return "application/json";
            }


            @Override
            protected Response<String> parseNetworkResponse(final NetworkResponse response) {
                int mStatusCode = response.statusCode;
                Log.d("Status Code Get Board", String.valueOf(mStatusCode));
                return super.parseNetworkResponse(response);
            }

        };

    }

    /**
     * method get board layout.
     * @return board layout string.
     */
    public String getBoardString() {
        return boardString;
    }

    /**
     * set board layout string.
     * @param boardString string.
     */
    public void setBoardString(final String boardString) {
        this.boardString = boardString;
        setChanged();
        notifyObservers();
    }
}
