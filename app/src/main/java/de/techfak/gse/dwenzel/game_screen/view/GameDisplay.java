package de.techfak.gse.dwenzel.game_screen.view;

import android.app.Activity;
import android.content.Context;
import android.widget.GridLayout;

import androidx.appcompat.app.AlertDialog;

import de.techfak.gse.dwenzel.R;
import de.techfak.gse.dwenzel.game_screen.controller.GameLoop;
import de.techfak.gse.dwenzel.game_screen.map.CoordinateView;
import de.techfak.gse.dwenzel.game_screen.map.FieldMap;
import de.techfak.gse.dwenzel.game_screen.model.DataLoader;

/* default */

public class GameDisplay implements AlertBox {

    private final Context context;
    private final BoardLoader boardLoader;
    private FieldMap fieldMap;
    private GameLoop gameLoop;

    /**
     * to create the view for the GameBoard.
     * And upgrade after changes.
     *
     * @param context context from main activity.
     */
    public GameDisplay(final Context context) {

        final DataLoader dataLoader = new DataLoader();
        boardLoader = new BoardLoader(context);
        this.context = context;


        onCreate(dataLoader);


    }

    /**
     * this method generates the Board View on Create the App.
     *
     * @param dataLoader to load images from assets.
     */
    private void onCreate(final DataLoader dataLoader) {
        /*init panels for Game board.*/
        final CoordinateView coordinateView
                = new CoordinateView(context);

        coordinateView.setVerticalCoordinateView(((Activity) context)
                .findViewById(R.id.verticaLayout));
        coordinateView.setHorizontalCoordinateView(((Activity) context)
                .findViewById(R.id.horizontalLayout));

        /*Layout for button board*/
        final GridLayout gridLayout
                = ((Activity) context)
                .findViewById(R.id.playground_grid);
        gridLayout.setColumnCount(context.getResources()
                .getInteger(R.integer.PlaygroundCol));

        /*set Background Board for the GameBoard*/
        ((Activity) context)
                .findViewById(R.id.gameBoard)
                .setBackground(dataLoader.loadDrawableFromAssets(context
                        .getApplicationContext(), "cork_board.png"));

        ((Activity) context)
                .findViewById(R.id.roundNote)
                .setBackground(dataLoader.loadDrawableFromAssets(context
                        .getApplicationContext(), "map/notePaper.png"));

    }


    /**
     * This method set the fieldMap from game
     * class and update the playground view after that.
     *
     * @param fieldMap fieldMap with changes from buttons.
     */
    public void setFieldsOnCreate(final FieldMap fieldMap) {
        this.fieldMap = fieldMap;
        boardLoader.updateFieldMap(((Activity) context)
                .findViewById(R.id.playground_grid), fieldMap);
    }

    /**
     * start the Game Loop controller.
     */
    public void startRun() {
        gameLoop = new GameLoop(context, fieldMap, this);
        //gameLoop.run();
    }

    /**
     * go to next round in game loop.
     */
    public void nextRound() {
        gameLoop.nextRound();
        gameLoop.startThread();
    }


    /**
     * show Alerts.
     * invalid TurnRules will be shown up in the game.
     *
     * @param title   title of the alert box.
     * @param message the rule behind the exception.
     */
    @Override
    public void showAlert(final String title, final String message) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title)
                .setCancelable(false)
                .setMessage(message)
                .setPositiveButton("Okay", null);
        builder.show();
    }

}
