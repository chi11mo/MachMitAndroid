package de.techfak.gse.dwenzel.game_screen.view;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;

import de.techfak.gse.dwenzel.R;
import de.techfak.gse.dwenzel.game_screen.controller.PlaygroundController;
import de.techfak.gse.dwenzel.game_screen.model.PlaygroundModel;

/**
 * This activity controls all the stuff on the main game card.
 */
public class BoardMainActivity extends AppCompatActivity implements Serializable, IPlaygroundView {

    /* UID.*/public static final long serialVersionUID = 4328742;


    /* Button Size.*/ private final static int buttonSize = 700 / 7;
    /* Buttons for the Playground.*/ private Button[][] playgroundButtons;
    /* to order the Button for the Playground.*/ private GridLayout gridLayoutButtons;
    /* to get control of the playground.*/ private PlaygroundController playgroundController;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        if (getIntent().getExtras() != null) {
            String board = getIntent().getStringExtra("File");


            playgroundController = new PlaygroundController(this);


            playgroundController.createPlayground(board
                    , getResources().getInteger(R.integer.PlaygroundRow)
                    , getResources().getInteger(R.integer.PlaygroundCol));

            gridLayoutButtons = findViewById(R.id.playground_grid);
            gridLayoutButtons.setColumnCount(getResources().getInteger(R.integer.PlaygroundRow));
            playgroundButtons = new Button[getResources().getInteger(R.integer.PlaygroundRow)][getResources().getInteger(R.integer.PlaygroundCol)];
            for (int i = 0; i < getResources().getInteger(R.integer.PlaygroundCol); i++) {
                for (int k = 0; k < getResources().getInteger(R.integer.PlaygroundRow); k++) {
                    playgroundButtons[k][i] = new Button(this);
                    playgroundButtons[k][i].setWidth(1);
                    playgroundButtons[k][i].setWidth(1);
                    playgroundButtons[k][i].setLayoutParams(
                            new LinearLayout.LayoutParams(buttonSize, buttonSize));

                    Button button = setFieldColor(playgroundButtons[k][i],
                            playgroundController.getPlayground().getFieldColor(k, i));


                    gridLayoutButtons.addView(button);


                }


            }

        }

    }

    /**
     * Listener for android toolbar back listener.
     * give a dialog if you want to go back to game_start_activity.
     */
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Back")
                .setCancelable(false)
                .setMessage("You want to Exit your current game ?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        finish();
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.dismiss();
                    }
                });
        builder.show();
    }


    /**
     * get the Update Model.
     *
     * @param playgroundModel updated Model from View.
     */
    @Override
    public void updatePlayground(PlaygroundModel playgroundModel) {
        for (int i = 0; i < getResources().getInteger(R.integer.PlaygroundCol); i++) {
            for (int k = 0; k < getResources().getInteger(R.integer.PlaygroundRow); k++) {
                if (playgroundModel.isFieldCrossed(k, i)) {
                    Log.d("Which field is Crossed : ", k + "/" + i);
                }
            }
        }

    }

    /**
     * Setting up field color after Reading the data.
     *
     * @param button button form playground.
     * @param color  color as aString from playground.
     * @return the colored button.
     */
    public Button setFieldColor(Button button, String color) {
        if (color.equals("g")) {
            button.setBackgroundColor(getResources().getColor(R.color.green));
            return button;
        }
        if (color.equals("G")) {
            button.setBackgroundColor(Color.GRAY);
            return button;
        }
        if (color.equals("b")) {
            button.setBackgroundColor(getResources().getColor(R.color.blue));
            return button;
        }
        if (color.equals("B")) {
            button.setBackgroundColor(Color.GRAY);
            return button;
        }

        if (color.equals("y")) {
            button.setBackgroundColor(getResources().getColor(R.color.yellow));
            return button;
        }
        if (color.equals("Y")) {
            button.setBackgroundColor(Color.GRAY);
            return button;
        }
        if (color.equals("o")) {
            button.setBackgroundColor(getResources().getColor(R.color.orange));
            return button;
        }
        if (color.equals("O")) {
            button.setBackgroundColor(Color.GRAY);
            return button;
        }
        if (color.equals("r")) {
            button.setBackgroundColor(getResources().getColor(R.color.red));
            return button;
        }
        if (color.equals("R")) {
            button.setBackgroundColor(Color.GRAY);
            return button;
        }


        return button;
    }


}
