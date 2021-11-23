package de.techfak.gse.dwenzel.game_screen.view;

import android.app.Activity;
import android.content.Context;
import android.widget.GridLayout;

import de.techfak.gse.dwenzel.R;
import de.techfak.gse.dwenzel.game_screen.controller.BoardLoader;
import de.techfak.gse.dwenzel.game_screen.controller.GameLoop;
import de.techfak.gse.dwenzel.game_screen.map.CoordinateView;
import de.techfak.gse.dwenzel.game_screen.map.FieldMap;
import de.techfak.gse.dwenzel.game_screen.model.DataLoader;

/* default */

public class GameDisplay {

    private final Context context;
    private final BoardLoader boardLoader;
    private FieldMap fieldMap;

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


        //Display display = getWindowManager().getDefaultDisplay();
        // int width = display.getWidth();  // deprecated
        //int height = display.getHeight();  // deprecated

        //  gameBoardLayout.setMaxHeight(context.getDisplay().getHeight());

        //  gameBoardLayout.setMaxWidth(context.getDisplay().getWidth());

        // setContentView(verticalLayout);
        //  gridLayoutButtons.setColumnCount(getResources()
        //  .getInteger(R.integer.PlaygroundRow));
    }

    /**
     * this method generates the Board View on Create the App.
     *
     * @param dataLoader to load images from assets.
     */
    private void onCreate(final DataLoader dataLoader) {

        final CoordinateView coordinateView
                = new CoordinateView(context); /*init panels for gameboard.*/

        coordinateView.setVerticalCoordinateView(((Activity) context)
                .findViewById(R.id.verticaLayout));
        coordinateView.setHorizontalCoordinateView(((Activity) context)
                .findViewById(R.id.horizontalLayout));


        final GridLayout gridLayout
                = ((Activity) context) /*Layout for button board.*/
                .findViewById(R.id.playground_grid);
        gridLayout.setColumnCount(context.getResources()
                .getInteger(R.integer.PlaygroundCol));


        ((Activity) context) /*set Background Board for the GameBoard*/
                .findViewById(R.id.gameBoard)
                .setBackground(dataLoader.loadDrawableFromAssets(context
                        .getApplicationContext(), "cork_board.png"));

    }


    /**
     * This method set the fieldMap from game
     * class and update the playground view after that.
     *
     * @param fieldMap fieldMap with changes from buttons.
     */
    public void setFieldPieces(final FieldMap fieldMap) {
        this.fieldMap = fieldMap;
        boardLoader.updateFieldMap(((Activity) context)
                .findViewById(R.id.playground_grid), fieldMap);
    }

    /**
     * start the Game Loop controller.
     */
    public void startRun() {
        GameLoop gameLoop = new GameLoop(context, fieldMap);
        //gameLoop.run();
    }


}
