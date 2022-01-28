package de.techfak.gse.dwenzel.start_screen.controller;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;

import de.techfak.gse.dwenzel.R;
import de.techfak.gse.dwenzel.game_screen.controller.BoardMainActivity;
import de.techfak.gse.dwenzel.start_screen.view.LoginView;

/**
 * Entry point activity for the app.
 */
public class GameStartActivity extends AppCompatActivity implements Serializable, LoginView {

    /*UID GameStartActivity.*/              public static final long serialVersionUID = 4328743;
    /*Text input from playground data.*/    private EditText textInputPlaygroundInput;
    /*Controller for the data validation.*/ private LoginController loginController;
    /*to store data path in String.*/       private String playgroundInputString;

    /**
     * Creates activity on first start.
     *
     * @param savedInstanceState instances saved .
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        loginController = new LoginController(this);

        textInputPlaygroundInput = findViewById(R.id.playgroundInput);


        /*Button to check validation.*/
        Button loginButton = findViewById(R.id.loginButton);

        textInputPlaygroundInput.setText("ggGyyyyGboboyyy\n"
                + "ogygyyoogbboogg\n"
                + "bgrggggrRryyogg\n"
                + "brrgoobbggYyorb\n"
                + "roOoorbbooorrrr\n"
                + "rBbrrrryyorbbbo\n"
                + "yybbbbryyygggog");

        loginButton.setOnClickListener(v -> {
            playgroundInputString = String.valueOf(textInputPlaygroundInput.getText());

            loginController.onLogin(playgroundInputString,
                    getResources().getInteger(R.integer.PlaygroundRow),
                    getResources().getInteger(R.integer.PlaygroundCol));
        });
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
        myIntent.putExtra("File", playgroundInputString);
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


}


