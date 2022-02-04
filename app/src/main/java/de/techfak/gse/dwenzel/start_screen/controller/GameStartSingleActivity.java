package de.techfak.gse.dwenzel.start_screen.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;

import java.io.Serializable;

import de.techfak.gse.dwenzel.R;
import de.techfak.gse.dwenzel.game_screen.controller.BoardMainSingleActivity;
import de.techfak.gse.dwenzel.start_screen.view.LoginView;

public class GameStartSingleActivity extends Activity implements Serializable, LoginView {
    private EditText boardLayoutInput;
    private String boardLayout;

    /**
     * Creates activity on first start.
     *
     * @param savedInstanceState instances saved .
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_single);

        boardLayoutInput = findViewById(R.id.boardLayoutInput);


    }

    /**
     * method  to go in next activity because board is valid.
     *
     * @param message validation message.
     */
    @Override
    public void onLoginSuccess(final String message) {
        finish();
        final Intent myIntent = new Intent(this, BoardMainSingleActivity.class);
        Log.d("Board is :", message);
        myIntent.putExtra("File", boardLayout);
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
     * start game.
     * @param view view.
     */
    public void onStartGame(final View view) {
        boardLayout = String.valueOf(boardLayoutInput.getText());
        LoginController loginController = new LoginController(this);

        int maxRow = getResources().getInteger(R.integer.PlaygroundRow);
        int maxCol = getResources().getInteger(R.integer.PlaygroundCol);
        loginController.onLogin(boardLayout, maxRow, maxCol);
    }


}
