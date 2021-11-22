package de.techfak.gse.dwenzel.game_screen.view;

import android.content.Context;

import de.techfak.gse.dwenzel.R;
import de.techfak.gse.dwenzel.game_screen.controller.GameLoop;
import de.techfak.gse.dwenzel.game_screen.map.FieldMap;
import de.techfak.gse.dwenzel.game_screen.model.ButtonSpriteSheet;

public class Game {


    private GameLoop gameLoop;
    private FieldMap fieldMap;
    private GameDisplay gameDisplay;


    public Game(final Context context, final String boardLayout) {


        final int maxRow
                = context.getResources().getInteger(R.integer.PlaygroundRow);
        final int maxCol
                = context.getResources().getInteger(R.integer.PlaygroundCol);

        //Display to show board.
        gameDisplay = new GameDisplay(context);


        //init game objects
        ButtonSpriteSheet buttonSpriteSheet = new ButtonSpriteSheet(context);
        fieldMap = new FieldMap(buttonSpriteSheet, boardLayout, maxRow, maxCol);

        gameDisplay.setFieldPieces(fieldMap);
        gameDisplay.startRun();

        // gameLoop = new GameLoop(this, surfaceHolder);


    }


    public void update(final FieldMap fieldMap) {
        gameDisplay.setFieldPieces(fieldMap);
    }
}
