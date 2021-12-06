package de.techfak.gse.dwenzel.game_screen.controller;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import de.techfak.gse.dwenzel.R;
import de.techfak.gse.dwenzel.game_screen.dice.Dice;
import de.techfak.gse.dwenzel.game_screen.map.AbstractField;
import de.techfak.gse.dwenzel.game_screen.map.FieldMap;
import de.techfak.gse.dwenzel.game_screen.model.DiceSpriteSheet;
import de.techfak.gse.dwenzel.game_screen.model.Round;
import de.techfak.gse.dwenzel.game_screen.model.TurnRules;
import de.techfak.gse.dwenzel.game_screen.view.AlertBox;
import de.techfak.gse.dwenzel.game_screen.view.FieldMarker;

public class GameLoop extends AppCompatActivity implements Runnable {
    private static final int THREAD_SLEEP = 1000;
    private static final int NULL_COLOR_INDEX = 6;
    private final Context context;


    private FieldMap fieldMap;
    private final int[] firstMarkColor = {NULL_COLOR_INDEX};
    private final Dice dice;
    private final AlertBox alertBox;
    private boolean isRunning;
    private Round round;
    private TurnRules turnRules;


    /**
     * GameLoop is game controller.
     * Have the Button listener for a current round and controlls
     * button input.
     *
     * @param context  is context from View
     * @param fieldMap for control the playground.
     */
    public GameLoop(final Context context, final FieldMap fieldMap, final AlertBox alertBox) {
        this.context = context;
        this.fieldMap = fieldMap;
        this.alertBox = alertBox;

        //init round and add the first round.
        round = new Round(context);
        dice = new Dice(context);
        turnRules = new TurnRules(alertBox, fieldMap);
        // round.addRound(fieldMap);
        //start gameloop thread.
        start();

    }

    /**
     * This will be start the loop thread.
     */
    public void start() {
        final Thread myThead = new Thread(this);
        myThead.start();
    }

    /**
     * run is Thread method to control the button listener.
     * Listen which Field will be crossed and checking if this was in the Turn Rules.
     * if you unMark a field all other current marked field will unmarked also.
     * When a mark is valid then it will be save in a List in Round (CurrentTurnTaps).
     */
    @Override
    public void run() {
        isRunning = true;

        FieldMarker fieldMarker = new FieldMarker();

        while (isRunning) {
            try {


                for (int iRow = 0; iRow
                        < context.getResources().getInteger(R.integer.PlaygroundRow); iRow++) {
                    for (int iCol = 0; iCol
                            < context.getResources().getInteger(R.integer.PlaygroundCol); iCol++) {
                        int finalIRow = iRow;

                        int finalICol = iCol;
                        fieldMap.getFields()[iRow][iCol].getButton().setOnClickListener(event -> {
                            AbstractField field = fieldMap.getFields()[finalIRow][finalICol];

                            if (fieldMap.getFields()[finalIRow][finalICol].isCrossed()) {

                                round.removeAllTaps();

                                if (round.getCurrentTurnTaps().isEmpty()) {
                                    firstMarkColor[0] = NULL_COLOR_INDEX;
                                }
                            } else {
                                if (turnRules.isTurnValid(field, firstMarkColor[0],round.getCurrentTurnTaps())) {


                                    firstMarkColor[0] = field.getFieldColor();
                                    fieldMarker.addFieldMark(field);
                                    //add field to the current Turn taps.
                                    round.addTap(field);
                                }
                            }


                        });
                    }
                }
                Thread.sleep(THREAD_SLEEP);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }

    /**
     * action to nextRound button.
     * and adds a new round if the turn is valid.
     */
    public void nextRound() {
        isRunning = false;
        updateDice();
        round.addRound(fieldMap);

        firstMarkColor[0] = NULL_COLOR_INDEX;
    }

    /**
     * Roll new Dice every Round.
     */
    private void updateDice() {
        dice.createDice();
        turnRules.setDice(dice);

    }
}
