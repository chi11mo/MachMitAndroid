package de.techfak.gse.dwenzel.start;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import de.techfak.gse.dwenzel.R;
import de.techfak.gse.dwenzel.board.BoardMainActivity;
import de.techfak.gse.dwenzel.validation.BoardValidation;

/**
 * Entry point activity for the app.
 */
public class GameStartActivity extends AppCompatActivity implements Serializable {
    private static final String TAG = "GameStartActivity";


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamestart);
    }

    /**
     * Method to Start the game and get Input of playground data.
     *
     * @param view view information
     */

    public void onClickGamestart(View view) {

        TextInputEditText textInputPlaygroundInput = findViewById(R.id.playgroundInput);

        String playgroundInputString = String.valueOf(textInputPlaygroundInput.getText());
        Log.i(TAG, playgroundInputString);
        InputStream file = null;


        try {
            file = getAssets().open(playgroundInputString);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String exception = BoardValidation.fileValidation(
                file, getResources().getInteger(R.integer.PlaygroundRow), getResources().getInteger(R.integer.PlaygroundCol));
        if (!exception.equals("Valid")) {
            popUpExceptionAlert(exception, view);
        } else {
            System.out.println(exception);
            Intent myIntent = new Intent(GameStartActivity.this, BoardMainActivity.class);

            myIntent.putExtra("File",playgroundInputString);
            startActivity(myIntent);
        }
    }




    /**
     * Pop up if board file isn't valid.
     *
     * @param exception which exception is threw.
     * @param view      view of xml.
     */
    private void popUpExceptionAlert(String exception, View view) {
        if (exception.equals("InvalidBoardException"))
            exception = "Board Structure is Invalid :  do 15 rows and 7 columns.";
        if (exception.equals("InvalidFieldException"))
            exception = "Board Structure is Invalid :   do b, B, g, G, o, O, r, R, y, Y ";

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Invalid Board Layout Try Another txt Data.")
                .setCancelable(false)
                .setMessage(exception)
                .setPositiveButton("Okay", null);
        builder.show();
    }
}


