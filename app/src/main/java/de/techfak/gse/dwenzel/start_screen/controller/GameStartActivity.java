package de.techfak.gse.dwenzel.start_screen.controller;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

import de.techfak.gse.dwenzel.R;
import de.techfak.gse.dwenzel.game_screen.controller.BoardMainActivity;
import de.techfak.gse.dwenzel.server_com.ServerController.LoginClient;
import de.techfak.gse.dwenzel.server_com.ServerConnection;
import de.techfak.gse.dwenzel.server_com.ServerController.BoardServerInteraction;
import de.techfak.gse.dwenzel.server_com.StartServer;
import de.techfak.gse.dwenzel.start_screen.view.LoginView;

/**
 * Entry point activity for the app.
 */
public class GameStartActivity
        extends AppCompatActivity
        implements Serializable, LoginView, Observer {

    public static final int PORT_NUMBER_SIZE = 4;

    private LoginController loginController;

    private String boardLayout;
    private StartServer startServer;
    private LoginClient loginClient;
    private BoardServerInteraction boardServerInteraction;

    private EditText serverLoginAnswer;
    private EditText userNameLoginAnswer;

    /**
     * Creates activity on first start.
     *
     * @param savedInstanceState instances saved .
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        startServer = new StartServer(this);
        startServer.addObserver(this);


        serverLoginAnswer = findViewById(R.id.serverNameInput);
        userNameLoginAnswer = findViewById(R.id.playerNameInput);


        serverLoginAnswer.setText("http://localhost:8080");
        userNameLoginAnswer.setText("Dominic");

    }

    /**
     * method  to go in next activity because board is valid.
     *
     * @param message validation message.
     */
    @Override
    public void onLoginSuccess(final String message) {
        final Intent myIntent = new Intent(this, BoardMainActivity.class);
        Log.d("Board is :", message);
        myIntent.putExtra("File", boardLayout);
        myIntent.putExtra("Url", startServer.getUrl());
        myIntent.putExtra("Name", loginClient.getName());
        startActivity(myIntent);

    }

    /**
     * method  to go in next activity because board is invalid.
     *
     * @param exception validation message.
     */
    @Override
    public void onLoginError(final String exception, final String board) {
        Log.d("BoardLayout is :", board);
        String message = exception;
        /*board Exception message.*/
        final String boardException = "InvalidBoardException";
        if (message.equals(boardException)) {
            message = "Board Struktur ist nicht valide :  7 Zeilen und 15 Spalten.";
        }
        /*field Exception message.*/
        final String fieldException = "InvalidFieldException";
        if (message.equals(fieldException)) {
            message = "Board Struktur ist nicht valide  :   b, B, g, G, o, O, r, R, y, Y ";
        }
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Invalid Board Layout Try Another txt Data.")
                .setCancelable(false)
                .setMessage(message)
                .setPositiveButton("Okay", null);
        builder.show();
    }

    /**
     * Server Start view. (Dialog).
     *
     * @param view view from Dialog xml.
     */
    public void onServerStart(final View view) {
        LayoutInflater layoutinflater = LayoutInflater.from(this);
        View promptUserView = layoutinflater.inflate(R.layout.dialog_prompt, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder.setView(promptUserView);

        final EditText userAnswer = (EditText) promptUserView.findViewById(R.id.portText);
        final EditText layoutUserAnswer = (EditText) promptUserView.findViewById(R.id.layoutText);

        alertDialogBuilder.setTitle("Port eingeben !");
        Log.d("port", String.valueOf(userAnswer.getText()));

        alertDialogBuilder.setPositiveButton("Server Starten",
                new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        String portAnswer = String.valueOf(userAnswer.getText());
                        String layoutAnswer = String.valueOf(layoutUserAnswer.getText());


                        if (testString(portAnswer)) {
                            int port = Integer.parseInt(portAnswer);
                            startServer.start(layoutAnswer,
                                    port);


                        } else {
                            TextView serverInfoView = findViewById(R.id.serverInfoView);
                            serverInfoView.setText("Server Online : Die Verbindung schlug fehl!");
                        }


                    }

                });

        // all set and time to build and show up!
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();


    }


    /**
     * This Method checks if port string is Valid.
     * 4 digits.
     *
     * @param port has to be 4 digits.
     * @return port is valid size.
     */
    public boolean testString(final String port) {
        if (port.length() != PORT_NUMBER_SIZE) {
            return false;
        }
        for (int i = 0, n = port.length(); i < n; i++) {
            if (!Character.isDigit(port.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Login the Player to the server if button Pressed.
     *
     * @param view view from login activity.
     */
    public void onLoginPlayer(final View view) {
        if (view.getId() == R.id.loginButton) {
            //handle the click here and make whatever you want

            Log.i("test", "Login Player Button");
            ServerConnection serverConnection = new ServerConnection();
            serverConnection.testConnection(this, serverLoginAnswer.getText().toString());
            loginClient = new LoginClient(this,
                    serverLoginAnswer.getText().toString(),
                    userNameLoginAnswer.getText().toString());
        }

    }

    /**
     * start the server to the server if button Pressed.
     *
     * @param view view from login activity.
     */
    public void onStartGame(final View view) {
        boardServerInteraction =
                new BoardServerInteraction(this,
                        serverLoginAnswer.getText().toString(),
                        userNameLoginAnswer.getText().toString());
        boardServerInteraction.addObserver(this);
    }

    @Override
    public void update(final Observable observable, final Object o) {
        if (startServer.isServerConnected()) {
            TextView serverInfoView = findViewById(R.id.serverInfoView);
            serverInfoView.setText("Server Online : " + startServer.getUrl());
            findViewById(R.id.serverButton).setEnabled(false);

            Log.w("Response Server", startServer.getServerResponseInfo());

            if (boardServerInteraction != null) {
                if (boardServerInteraction.getBoardString() != null) {
                    boardLayout = boardServerInteraction.getBoardString();
                    loginController = new LoginController(this);

                    int maxRow = getResources().getInteger(R.integer.PlaygroundRow);
                    int maxCol = getResources().getInteger(R.integer.PlaygroundCol);
                    loginController.onLogin(boardLayout, maxRow, maxCol);
                }
            }
        } else {
            TextView serverInfoView = findViewById(R.id.serverInfoView);
            serverInfoView.setText("Server Online : Die Verbindung schlug fehl!");
            Log.w("Response Server", startServer.getServerResponseInfo());
        }
    }


}


