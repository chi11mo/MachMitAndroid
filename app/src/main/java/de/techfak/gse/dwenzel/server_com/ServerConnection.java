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

import java.util.Observable;

public class ServerConnection extends Observable {

    private Context context;
    private int mStatusCode;



    private boolean isServerOnline = false;

    public void testConnection(final Context context, final String url) {
        this.context = context;
        final StringRequest request = buildRequest(url);
        final RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(request);

    }


    /**
     * Build the Request to check is Server Online.
     *
     * @param url  server url.
     * @return StringRequest.
     */
    private StringRequest buildRequest(final String url) {
        final String finalUrl = url + "/";
        final Response.Listener<String> onResponse = response -> {

            Toast.makeText(context, "Server unter der IP erreichbar!", Toast.LENGTH_SHORT).show();
            setServerOnline(true);

        };
        final Response.ErrorListener onError = error -> {

            Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
            setServerOnline(false);
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

    /**
     * is Server Connected.
     *
     * @return bool.
     */
    public boolean isServerOnline() {
        return isServerOnline;
    }

    /**
     * Setting server connected.
     *
     * @param serverOnline serverbool.
     */
    public void setServerOnline(final boolean serverOnline) {
        this.isServerOnline = serverOnline;
        setChanged();
        notifyObservers();
    }


}
