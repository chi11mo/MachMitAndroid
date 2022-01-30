package de.techfak.gse.dwenzel.server_com;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.IOException;
import java.util.Observable;

import de.techfak.gse.multiplayer.game.BaseGame;
import de.techfak.gse.multiplayer.game.BaseGameImpl;
import de.techfak.gse.multiplayer.game.BoardParser;
import de.techfak.gse.multiplayer.game.BoardParserImpl;
import de.techfak.gse.multiplayer.game.SynchronizedGame;
import de.techfak.gse.multiplayer.game.exceptions.InvalidBoardLayoutException;
import de.techfak.gse.multiplayer.game.exceptions.InvalidFieldException;
import de.techfak.gse.multiplayer.server.Server;

public class StartServer extends Observable {
    private final Context context;
    private Server server;

    private boolean isServerConnected;
    private String url;

    /**
     * This Class check server connection and Start the Server.
     *
     * @param context context from loginView.
     */
    public StartServer(final Context context) {
        this.context = context;
    }

    /**
     * Method to start Server.
     *
     * @param boardString string with board Layout.
     * @param port        port to start.
     */
    public void start(final String boardString, final int port) {

        final SynchronizedGame game;

        try {
            final BoardParser boardParser = new BoardParserImpl();
            final BaseGame baseGame =
                    new BaseGameImpl(boardParser.parse(boardString));
            game = new SynchronizedGame(baseGame);
            server = new Server(port, game);
            server.start();

            // server.stop();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFieldException e) {
            e.printStackTrace();
        } catch (InvalidBoardLayoutException e) {
            e.printStackTrace();
        }
        url = "http://localhost:" + port;


        //Build GET request auf http://localhost:8080
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
            Log.w("Response", "Top!");
            setServerConnected(true);
        };
        final Response.ErrorListener onError = error -> {

            Log.w("Response", error.networkResponse.data.toString());
            setServerConnected(false);
        };
        return new StringRequest(Request.Method.GET, finalUrl, onResponse, onError);

    }

    /**
     * Get Current por.
     *
     * @return port as int.
     */
    public int getPort() {
        return server.getListeningPort();
    }

    /**
     * set ServerConnection send this to Observer.
     *
     * @param isServerConnected is Server Online.
     */
    private void setServerConnected(final boolean isServerConnected) {
        this.isServerConnected = isServerConnected;
        setChanged();
        notifyObservers();
    }

    /**
     * Check is Server Connected.
     *
     * @return isServerConnected.
     */
    public boolean isServerConnected() {
        return isServerConnected;
    }

    /**
     * get Connected Url.
     *
     * @return connected Url.
     */
    public String getUrl() {
        return url;
    }


}
