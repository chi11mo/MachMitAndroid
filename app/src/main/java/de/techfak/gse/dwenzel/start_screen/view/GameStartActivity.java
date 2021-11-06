package de.techfak.gse.dwenzel.start_screen.view;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;

import de.techfak.gse.dwenzel.R;
import de.techfak.gse.dwenzel.game_screen.view.BoardMainActivity;
import de.techfak.gse.dwenzel.start_screen.controller.LoginController;

/**
 * Entry point activity for the app.
 */
public class GameStartActivity extends AppCompatActivity implements Serializable, LoginView {

    /*UID GameStartActivity.*/ public static final long serialVersionUID = 4328743;
    /*field Exception message.*/ private final static String fieldException = "InvalidFieldException";
    /*board Exception message.*/ private final static String boardException = "InvalidBoardException";
    /*Text intput from playground data.*/ TextInputEditText textInputPlaygroundInput;

    /*Controller for the data validation.*/ LoginController loginController;

    /*to store data path in String.*/ String playgroundInputString;

    /*Button to check validation.*/ Button loginButton;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamestart);


        loginController = new LoginController(this);

        textInputPlaygroundInput = findViewById(R.id.playgroundInput);


        loginButton = findViewById(R.id.loginButton);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playgroundInputString = String.valueOf(textInputPlaygroundInput.getText());
               // String[] splited = playgroundInputString.split(",");

                loginController.OnLogin(playgroundInputString, getResources().getInteger(R.integer.PlaygroundRow),
                        getResources().getInteger(R.integer.PlaygroundCol));
            }
        });
    }


    @Override
    public void OnLoginSuccess(String message) {
        Intent myIntent = new Intent(GameStartActivity.this, BoardMainActivity.class);
        Log.d("Board is :", message);
        myIntent.putExtra("File", playgroundInputString);
        startActivity(myIntent);

    }

    @Override
    public void OnLoginError(String exception) {
        if (exception.equals(boardException))
            exception = "Board Structure is Invalid :  do 15 rows and 7 columns.";
        if (exception.equals(fieldException))
            exception = "Board Structure is Invalid :   do b, B, g, G, o, O, r, R, y, Y ";

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Invalid Board Layout Try Another txt Data.")
                .setCancelable(false)
                .setMessage(exception)
                .setPositiveButton("Okay", null);
        builder.show();
    }
}

