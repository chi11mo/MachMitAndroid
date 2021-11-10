package de.techfak.gse.dwenzel.game_screen.view;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import de.techfak.gse.dwenzel.R;
import de.techfak.gse.dwenzel.game_screen.controller.PlaygroundController;
import de.techfak.gse.dwenzel.game_screen.model.DataLoader;
import de.techfak.gse.dwenzel.game_screen.model.PlaygroundModel;

/**
 * This activity controls all the stuff on the main game card.
 */
public class BoardMainActivity extends AppCompatActivity implements PlaygroundView {

    /* UID.*/                                       public static final long serialVersionUID = 4328742;

    /*Char to begin with big Letter ABC */          private static final int AINASCII = 64;
    /* Button Size.*/                               private static int buttonSize;
    /*vertical Layout for coordinate on board*/     private LinearLayout verticalLayout;
    /*vertical Layout for coordinate on board*/     private LinearLayout horizontalLayout;
    /*Drawable for button Images*/                  private Drawable iconNotPressedRed;
    /*Drawable for button Images*/                  private Drawable iconNotPressedYellow;
    /*Drawable for button Images*/                  private Drawable iconNotPressedGreen;
    /*Drawable for button Images*/                  private Drawable iconNotPressedBlue;
    /*Drawable for button Images*/                  private Drawable iconNotPressedOrange;
    /*Drawable for button Images*/                  private Drawable iconPressedRed;
    /*Drawable for button Images*/                  private Drawable iconPressedYellow;
    /*Drawable for button Images*/                  private Drawable iconPressedGreen;
    /*Drawable for button Images*/                  private Drawable iconPressedBlue;
    /*Drawable for button Images*/                  private Drawable iconPressedOrange;
    /*Drawable for button Background*/              private Drawable corkBoard;

    /**
     * on Create method for do things when activity starts.
     *
     * @param savedInstanceState instances of activity.
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);
        loadButtonImages();
        buttonSize = getResources().getInteger(R.integer.ButtonSize);
        /*Layout for board.*/
        ConstraintLayout gameBoardLayout = findViewById(R.id.gameBoard);
        gameBoardLayout.setBackground(corkBoard);
        verticalLayout = findViewById(R.id.verticaLayout);
        horizontalLayout = findViewById(R.id.horizontalLayout);
        if (getIntent().getExtras() != null) {
            final String board = getIntent().getStringExtra("File");


            /* to get control of the playground.*/
            final PlaygroundController playgroundController = new PlaygroundController(this);


            playgroundController.createPlayground(board, getResources().getInteger(R.integer.PlaygroundRow),
                    getResources().getInteger(R.integer.PlaygroundCol));

            /* to order the Button for the Playground.*/
            final GridLayout gridLayoutButtons = findViewById(R.id.playground_grid);

            gridLayoutButtons.setColumnCount(getResources().getInteger(R.integer.PlaygroundRow));
            /* Buttons for the Playground.*/
            final ImageButton[][] playgroundButtons =
                    new ImageButton[getResources().getInteger(R.integer.PlaygroundRow)]
                            [getResources().getInteger(R.integer.PlaygroundCol)];
            for (int i = 0; i < getResources().getInteger(R.integer.PlaygroundCol); i++) {
                for (int k = 0; k < getResources().getInteger(R.integer.PlaygroundRow); k++) {
                    playgroundButtons[k][i] = new ImageButton(this);

                    playgroundButtons[k][i].setLayoutParams(
                            new LinearLayout.LayoutParams(getResources().getInteger(R.integer.ButtonSize),
                                    getResources().getInteger(R.integer.ButtonSize)));

                    ImageButton button;
                    button = getFieldColor(playgroundButtons[k][i],
                            playgroundController.getPlayground().getFieldColor(k, i));
                    button.setBackground(null);
                    button.setAdjustViewBounds(true);
                    button.setPadding(0, 0, 0, 0);
                    button.setScaleType(ImageButton.ScaleType.FIT_START);
                    gridLayoutButtons.addView(button);


                }


            }
            initCoordinates();

        }

    }

    /**
     * Generate UI to show coordinates table.
     */
    public void initCoordinates() {
        TextView[][] coordinatesBoard = new TextView[getResources().getInteger(R.integer.PlaygroundRow) + 1]
                [getResources().getInteger(R.integer.PlaygroundRow) + 1];
        for (int i = 0; i < getResources().getInteger(R.integer.PlaygroundCol) + 1; i++) {
            coordinatesBoard[i][0] = new TextView(this);
            coordinatesBoard[i][0].setLayoutParams(
                    new LinearLayout.LayoutParams(getResources().getInteger(R.integer.ButtonSize),
                            getResources().getInteger(R.integer.ButtonSize)));
            coordinatesBoard[i][0].setText(String.valueOf(i));
            verticalLayout.addView(coordinatesBoard[i][0]);
        }
        for (int i = 0; i < getResources().getInteger(R.integer.PlaygroundRow) + 1; i++) {
            coordinatesBoard[0][i] = new TextView(this);
            coordinatesBoard[0][i].setLayoutParams(
                    new LinearLayout.LayoutParams(getResources().getInteger(R.integer.ButtonSize),
                            getResources().getInteger(R.integer.ButtonSize)));
            coordinatesBoard[0][i].setText(Character.toString((char) (i + AINASCII)));
            horizontalLayout.addView(coordinatesBoard[0][i]);
        }
    }

    /**
     * Listener for android toolbar back listener.
     * give a dialog if you want to go back to game_start_activity.
     */
    @Override
    public void onBackPressed() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Back")
                .setCancelable(false)
                .setMessage("You want to Exit your current game ?")
                .setPositiveButton("Yes", (dialog, whichButton) -> {
                    finish();
                    dialog.dismiss();
                })
                .setNegativeButton("No", (dialog, whichButton) -> dialog.dismiss());
        builder.show();
    }


    /**
     * get the Update Model.
     *
     * @param playgroundModel updated Model from View.
     */
    @Override
    public void updatePlayground(final PlaygroundModel playgroundModel) {
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
    public ImageButton getFieldColor(final ImageButton button, final String color) {

        switch (color) {
            case "g":
                button.setImageDrawable(iconNotPressedGreen);
                break;
            case "G":
                button.setImageDrawable(iconPressedGreen);
                break;
            case "b":
                button.setImageDrawable(iconNotPressedBlue);
                break;
            case "B":
                button.setImageDrawable(iconPressedBlue);
                break;
            case "y":
                button.setImageDrawable(iconNotPressedYellow);
                break;
            case "Y":
                button.setImageDrawable(iconPressedYellow);
                break;
            case "o":
                button.setImageDrawable(iconNotPressedOrange);
                break;
            case "O":
                button.setImageDrawable(iconPressedOrange);
                break;
            case "r":
                button.setImageDrawable(iconNotPressedRed);
                break;
            case "R":
                button.setImageDrawable(iconPressedRed);
                break;
            default:
                Log.d("getColor Board Activity", "no Color found for Button.");


        }
        return button;


    }

    /**
     * Load athe needed png pictures for button usage.
     */
    public void loadButtonImages() {
        final DataLoader dataLoader = new DataLoader();
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

        corkBoard = dataLoader.loadDrawableFromAssets(getApplicationContext(), "cork_board.png");

    }


}
