package de.techfak.gse.dwenzel.game_screen.view;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;

import de.techfak.gse.dwenzel.R;
import de.techfak.gse.dwenzel.game_screen.controller.PlaygroundController;
import de.techfak.gse.dwenzel.game_screen.model.DataLoader;
import de.techfak.gse.dwenzel.game_screen.model.PlaygroundModel;

/**
 * This activity controls all the stuff on the main game card.
 */
public class BoardMainActivity extends AppCompatActivity implements Serializable, IPlaygroundView {

    /* UID.*/public static final long serialVersionUID = 4328742;


    /* Button Size.*/ private final static int buttonSize = 700 / 7;
    /* Buttons for the Playground.*/ private ImageButton[][] playgroundButtons;
    /* to order the Button for the Playground.*/ private GridLayout gridLayoutButtons;
    /* to get control of the playground.*/ private PlaygroundController playgroundController;
    /*Drawable for button Images*/private Drawable iconNotPressedRed;
    /*Drawable for button Images*/private Drawable iconNotPressedYellow;
    /*Drawable for button Images*/private Drawable iconNotPressedGreen;
    /*Drawable for button Images*/private Drawable iconNotPressedBlue;
    /*Drawable for button Images*/private Drawable iconNotPressedOrange;
    /*Drawable for button Images*/private Drawable iconPressedRed;
    /*Drawable for button Images*/private Drawable iconPressedYellow;
    /*Drawable for button Images*/private Drawable iconPressedGreen;
    /*Drawable for button Images*/private Drawable iconPressedBlue;
    /*Drawable for button Images*/private Drawable iconPressedOrange;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);
        loadButtonImages();
        if (getIntent().getExtras() != null) {
            String board = getIntent().getStringExtra("File");


            playgroundController = new PlaygroundController(this);


            playgroundController.createPlayground(board
                    , getResources().getInteger(R.integer.PlaygroundRow)
                    , getResources().getInteger(R.integer.PlaygroundCol));

            gridLayoutButtons = findViewById(R.id.playground_grid);
            gridLayoutButtons.setColumnCount(getResources().getInteger(R.integer.PlaygroundRow));
            playgroundButtons = new ImageButton[getResources().getInteger(R.integer.PlaygroundRow)][getResources().getInteger(R.integer.PlaygroundCol)];
            for (int i = 0; i < getResources().getInteger(R.integer.PlaygroundCol); i++) {
                for (int k = 0; k < getResources().getInteger(R.integer.PlaygroundRow); k++) {
                    playgroundButtons[k][i] = new ImageButton(this);

                    playgroundButtons[k][i].setLayoutParams(
                            new LinearLayout.LayoutParams(buttonSize, buttonSize));

                    ImageButton button = setFieldColor(playgroundButtons[k][i],
                            playgroundController.getPlayground().getFieldColor(k, i));
                    button.setBackground(null);
                    button.setAdjustViewBounds(true);
                    button.setPadding(0, 0, 0, 0);
                    button.setScaleType(ImageButton.ScaleType.FIT_START);
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
    public ImageButton setFieldColor(ImageButton button, String color) {
        if (color.equals("g")) {
            button.setImageDrawable(iconNotPressedGreen);
            return button;
        }
        if (color.equals("G")) {
            button.setImageDrawable(iconPressedGreen);
            return button;
        }
        if (color.equals("b")) {
            button.setImageDrawable(iconNotPressedBlue);
            return button;
        }
        if (color.equals("B")) {
            button.setImageDrawable(iconPressedBlue);
            return button;
        }

        if (color.equals("y")) {
            button.setImageDrawable(iconNotPressedYellow);
            return button;
        }
        if (color.equals("Y")) {
            button.setImageDrawable(iconPressedYellow);
            return button;
        }
        if (color.equals("o")) {
            button.setImageDrawable(iconNotPressedOrange);
            return button;
        }
        if (color.equals("O")) {
            button.setImageDrawable(iconPressedOrange);
            return button;
        }
        if (color.equals("r")) {
            button.setImageDrawable(iconNotPressedRed);
            return button;
        }
        if (color.equals("R")) {
            button.setImageDrawable(iconPressedRed);
            return button;
        }


        return button;
    }

    /**
     * Load athe needed png pictures for button usage.
     */
    public void loadButtonImages() {
        DataLoader dataLoader = new DataLoader();
        iconPressedYellow = dataLoader.loadDrawableFromAssets(getApplicationContext(), "icon_pressed_yellow.png");
        iconPressedYellow.setBounds(0, 0, buttonSize, buttonSize);
        iconNotPressedYellow = dataLoader.loadDrawableFromAssets(getApplicationContext(), "icon_notPressed_yellow.png");
        iconNotPressedYellow.setBounds(0, 0, buttonSize, buttonSize);

        iconPressedGreen = dataLoader.loadDrawableFromAssets(getApplicationContext(), "icon_pressed_green.png");
        iconPressedGreen.setBounds(0, 0, buttonSize, buttonSize);
        iconNotPressedGreen = dataLoader.loadDrawableFromAssets(getApplicationContext(), "icon_notPressed_green.png");
        iconNotPressedGreen.setBounds(0, 0, buttonSize, buttonSize);

        iconPressedRed = dataLoader.loadDrawableFromAssets(getApplicationContext(), "icon_pressed_red.png");
        iconPressedRed.setBounds(0, 0, buttonSize, buttonSize);
        iconNotPressedRed = dataLoader.loadDrawableFromAssets(getApplicationContext(), "icon_notPressed_red.png");
        iconNotPressedRed.setBounds(0, 0, buttonSize, buttonSize);

        iconPressedOrange = dataLoader.loadDrawableFromAssets(getApplicationContext(), "icon_pressed_orange.png");
        iconPressedOrange.setBounds(0, 0, buttonSize, buttonSize);
        iconNotPressedOrange = dataLoader.loadDrawableFromAssets(getApplicationContext(), "icon_notPressed_orange.png");
        iconNotPressedOrange.setBounds(0, 0, buttonSize, buttonSize);

        iconPressedBlue = dataLoader.loadDrawableFromAssets(getApplicationContext(), "icon_pressed_blue.png");
        iconPressedBlue.setBounds(0, 0, buttonSize, buttonSize);
        iconNotPressedBlue = dataLoader.loadDrawableFromAssets(getApplicationContext(), "icon_notPressed_blue.png");
        iconNotPressedBlue.setBounds(0, 0, buttonSize, buttonSize);

    }


}
