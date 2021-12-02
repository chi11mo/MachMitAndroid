package de.techfak.gse.dwenzel.game_screen.controller;

import android.content.Context;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import de.techfak.gse.dwenzel.R;
import de.techfak.gse.dwenzel.game_screen.map.FieldMap;
import de.techfak.gse.dwenzel.game_screen.model.Round;
import de.techfak.gse.dwenzel.game_screen.model.TurnRules;

public class GameLoop extends AppCompatActivity implements Runnable {
    private static final int THREAD_SLEEP = 1000;
    private final Context context;


    private FieldMap fieldMap;
    final int[] firstMarkColor = {6};
    private boolean isRunning;
    private Round round;

    /**
     * GameLoop is game controller.
     * Have the Button listener for a current round and controlls
     * button input.
     *
     * @param context  is context from View
     * @param fieldMap for control the playground.
     */
    public GameLoop(final Context context, final FieldMap fieldMap) {
        this.context = context;
        this.fieldMap = fieldMap;
        //init round and add the first round.
        round = new Round(context);
        // round.addRound(fieldMap);
        //start gameloop thread.
        start();

    }

    public void start() {
        final Thread myThead = new Thread(this);
        myThead.start();
    }

    /**
     * run is Thread method to control the button listener.
     */
    @Override
    public void run() {
        isRunning = true;
        TurnRules turnRules = new TurnRules(fieldMap);


        while (isRunning) {
            try {


                for (int iRow = 0; iRow
                        < context.getResources().getInteger(R.integer.PlaygroundRow); iRow++) {
                    for (int iCol = 0; iCol
                            < context.getResources().getInteger(R.integer.PlaygroundCol); iCol++) {
                        int finalIRow = iRow;

                        int finalICol = iCol;
                        fieldMap.getFields()[iRow][iCol].getButton().setOnClickListener(event -> {
                            if (turnRules.isTurnValid(fieldMap.getFields()[finalIRow][finalICol], firstMarkColor[0])) {
                                firstMarkColor[0] = fieldMap.getFields()[finalIRow][finalICol].getFieldColor();
                                if (fieldMap.getFields()[finalIRow][finalICol].isCrossed()) {
                                    fieldMap.getFields()[finalIRow][finalICol].setIsCrossed(false);
                                    fieldMap.getFields()[finalIRow][finalICol]
                                            .getButton()
                                            .setImageDrawable(fieldMap
                                                    .getFields()[finalIRow][finalICol]
                                                    .getDrawableField(false));
                                    round.removeTap(fieldMap.getFields()[finalIRow][finalICol]);
                                    if (round.getCurrentTurnTaps().isEmpty()) {
                                        firstMarkColor[0] = 6;
                                    }
                                } else {
                                    fieldMap.getFields()[finalIRow][finalICol].setIsCrossed(true);
                                    fieldMap.getFields()[finalIRow][finalICol]
                                            .getButton().setImageDrawable(fieldMap
                                            .getFields()[finalIRow][finalICol]
                                            .getDrawableField(true));
                                    //add field to the current Turn taps.
                                    round.addTap(fieldMap.getFields()[finalIRow][finalICol]);
                                }

                            } else {
                                Log.d("GameLoob", "Mark is not valid");
                            }
                            //Log.d(":Button event", (finalIRow) + "/" + finalICol);
                            // Log.d(":Button event", String.valueOf(fieldMap
                            // .getFields()[finalIRow][finalICol]
                            // .isCrossed()));
                        });
                    }
                }
                //context.get
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
        round.addRound(fieldMap);

       firstMarkColor[0] = 6;
    }
}
