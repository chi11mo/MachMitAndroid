package de.techfak.gse.dwenzel.server_com;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class ServerConnection {

    private Context context;
    private int mStatusCode;

    public void testConnection(final Context context, final String url) {
        this.context = context;
        final StringRequest request = buildRequest(url, "/");
        final RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(request);

    }


    /**
     * Build the Request to check is Server Online.
     *
     * @param url  server url.
     * @param name just link .
     * @return StringRequest.
     */
    private StringRequest buildRequest(final String url, final String name) {
        final String finalUrl = url + name;
        final Response.Listener<String> onResponse = response -> {

            Toast.makeText(context, "Server unter der IP erreichbar!", Toast.LENGTH_SHORT).show();

        };
        final Response.ErrorListener onError = error -> {

            Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
        };
        return new StringRequest(Request.Method.GET, finalUrl, onResponse, onError) {

            @Override
            protected Response<String> parseNetworkResponse(final NetworkResponse response) {
                mStatusCode = response.statusCode;
                Log.d("Status Code Server Connection", String.valueOf(mStatusCode));
                return super.parseNetworkResponse(response);
            }
        };
    }

}
