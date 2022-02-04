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

import de.techfak.gse.multiplayer.server.request_body.RegisterBody;
import de.techfak.gse.multiplayer.server.response_body.ResponseObject;

public class LoginClient {
    private final String url;
    private Context context;
    private String name;

    /**
     * Login Client to login players.
     *
     * @param context app context.
     * @param url     server url.
     * @param name    name to login.
     */
    public LoginClient(final Context context, final String url, final String name) {
        this.context = context;
        this.url = url;
        this.name = name;
        final ObjectMapper objectMapper = new ObjectMapper();
        final String jsonBody;

        try {
            jsonBody = objectMapper.writeValueAsString(new RegisterBody(name));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        final StringRequest request = buildRequest(url + "/api/game/players?name=", jsonBody);
        final RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(request);
    }


    /**
     * Build the Request to check is Server Online.
     *
     * @param url      server url.
     * @param jsonBody body of set name json.
     * @return StringRequest.
     */
    private StringRequest buildRequest(final String url, final String jsonBody) {
        final ObjectMapper objectMapper = new ObjectMapper();


        final Response.Listener<String> onResponse = response -> {

            try {
                ResponseObject
                        body = objectMapper.readValue(response, ResponseObject.class);
                Toast.makeText(context, body.getMessage(), Toast.LENGTH_LONG).show();
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }


        };
        final Response.ErrorListener onError = error -> {


            Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();


        };
        return new StringRequest(Request.Method.POST, url, onResponse, onError) {
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
                Log.d("Status Code Player Login", String.valueOf(mStatusCode));
                return super.parseNetworkResponse(response);
            }

        };


    }

    /**
     * Get logged name.
     * @return logged name as String.
     */
    public String getName() {
        return name;
    }
}
