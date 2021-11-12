package de.techfak.gse.dwenzel.game_screen.view;

import android.os.Bundle;
import android.util.Log;
import android.widget.GridLayout;
import android.widget.LinearLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import de.techfak.gse.dwenzel.R;
import de.techfak.gse.dwenzel.game_screen.controller.PlaygroundController;
import de.techfak.gse.dwenzel.game_screen.model.BoardLoader;
import de.techfak.gse.dwenzel.game_screen.model.DataLoader;
import de.techfak.gse.dwenzel.game_screen.model.PlaygroundModel;

/**
 * This activity controls all the stuff on the main game card.
 */
public class BoardMainActivity extends AppCompatActivity implements PlaygroundView {

    /* UID.*/                                       public static final long serialVersionUID = 4328742;


    /**
     * on Create method for do things when activity starts.
     *
     * @param savedInstanceState instances of activity.
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        /*Layout for board.*/
        final ConstraintLayout gameBoardLayout = findViewById(R.id.gameBoard);

        final DataLoader dataLoader = new DataLoader();
        gameBoardLayout.setBackground(dataLoader.loadDrawableFromAssets(getApplicationContext(), "cork_board.png"));

        /*vertical Layout for coordinate on board*/
        final LinearLayout verticalLayout = findViewById(R.id.verticaLayout);
        /*vertical Layout for coordinate on board*/
        final LinearLayout horizontalLayout = findViewById(R.id.horizontalLayout);

        final BoardLoader boardLoader = new BoardLoader(this,
                getResources().getInteger(R.integer.PlaygroundRow),
                getResources().getInteger(R.integer.PlaygroundCol),
                getResources().getInteger(R.integer.ButtonSize));

        boardLoader.setVerticalCoordinateView(verticalLayout);
        boardLoader.setHorizontalCoordinateView(horizontalLayout);
        if (getIntent().getExtras() != null) {
            final String board = getIntent().getStringExtra("File");


            /* to get control of the playground.*/
            final PlaygroundController playgroundController = new PlaygroundController(this);


            playgroundController.createPlayground(board, getResources().getInteger(R.integer.PlaygroundRow),
                    getResources().getInteger(R.integer.PlaygroundCol));

            /* to order the Button for the Playground.*/
            final GridLayout gridLayoutButtons = findViewById(R.id.playground_grid);

            gridLayoutButtons.setColumnCount(getResources().getInteger(R.integer.PlaygroundRow));

            boardLoader.setButtonPlayground(gridLayoutButtons, playgroundController);
            /* Buttons for the Playground.*/


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


}
