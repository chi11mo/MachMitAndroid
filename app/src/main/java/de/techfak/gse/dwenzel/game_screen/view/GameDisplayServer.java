package de.techfak.gse.dwenzel.game_screen.view;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.GridLayout;

import androidx.appcompat.app.AlertDialog;

import de.techfak.gse.dwenzel.R;
import de.techfak.gse.dwenzel.game_screen.controller.GameLoopServer;
import de.techfak.gse.dwenzel.game_screen.model.FieldMap;
import de.techfak.gse.dwenzel.game_screen.model.DataLoader;

/* default */

public class GameDisplayServer implements AlertBox {

    private final Context context;
    private FieldMap fieldMap;
    private GameLoopServer gameLoopServer;

    /**
     * to create the view for the GameBoard.
     * And upgrade after changes.
     *
     * @param context     context from main activity.
     * @param boardLayout string of boardLayout.
     * @param name        name.
     * @param url         url.
     */
    public GameDisplayServer(final Context context,
                             final String boardLayout,
                             final String url,
                             final String name) {
        this.context = context;
        final int maxRow
                = context.getResources().getInteger(R.integer.PlaygroundRow);
        final int maxCol
                = context.getResources().getInteger(R.integer.PlaygroundCol);

        final DataLoader dataLoader = new DataLoader();


        //init game objects
        final FieldMap fieldMap = new FieldMap(boardLayout, maxRow, maxCol);

        onCreate(dataLoader);

        setFieldsOnCreate(fieldMap);
        startRun(url, name);


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

    }

    /**
     * start the Game Loop controller.
     *
     * @param name name.
     * @param url  url.
     */
    public void startRun(final String url, final String name) {
        // gameLoop = new GameLoop(context, fieldMap, this,url,name);
        Log.d("RUNNING", name + url);
        gameLoopServer = new GameLoopServer(context, fieldMap, this, url, name);
        //gameLoop.run();
    }

    /**
     * go to next round in game loop.
     */
    public void nextRound() {
        // gameLoop.nextRound();
        //gameLoop.startThread();
        gameLoopServer.nextRound();
        gameLoopServer.startThread();
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

    /**
     * update current fieldMap from the first init.
     *
     * @param fieldMap first init fieldmap from boardLayout.
     */
    public void update(final FieldMap fieldMap) {
        setFieldsOnCreate(fieldMap);
    }

}
