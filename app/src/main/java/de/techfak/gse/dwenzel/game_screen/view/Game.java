package de.techfak.gse.dwenzel.game_screen.view;

import android.content.Context;

import de.techfak.gse.dwenzel.R;
import de.techfak.gse.dwenzel.game_screen.map.FieldMap;
import de.techfak.gse.dwenzel.sprite_sheet.ButtonSpriteSheet;

public class Game {


    private final GameDisplay gameDisplay;


    public Game(final Context context, final String boardLayout) {


        final int maxRow
                = context.getResources().getInteger(R.integer.PlaygroundRow);
        final int maxCol
                = context.getResources().getInteger(R.integer.PlaygroundCol);

        //Display to show board.
        gameDisplay = new GameDisplay(context);


        //init game objects
        final ButtonSpriteSheet buttonSpriteSheet = new ButtonSpriteSheet(context);
        final FieldMap fieldMap = new FieldMap(buttonSpriteSheet, boardLayout, maxRow, maxCol);

        gameDisplay.setFieldsOnCreate(fieldMap);
        gameDisplay.startRun();

        // gameLoop = new GameLoop(this, surfaceHolder);


    }


    public void update(final FieldMap fieldMap) {
        gameDisplay.setFieldsOnCreate(fieldMap);
    }

    /**
     * action to nextRound button.
     */
    public void nextRound() {
        gameDisplay.nextRound();
    }
}
