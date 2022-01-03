package de.techfak.gse.dwenzel.game_screen.controller;

import android.app.Activity;
import android.content.Context;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Observable;
import java.util.Observer;

import de.techfak.gse.dwenzel.R;
import de.techfak.gse.dwenzel.game_screen.dice.Dice;
import de.techfak.gse.dwenzel.game_screen.dice.DiceView;
import de.techfak.gse.dwenzel.game_screen.map.AbstractField;
import de.techfak.gse.dwenzel.game_screen.map.FieldMap;
import de.techfak.gse.dwenzel.game_screen.map.FieldMapView;
import de.techfak.gse.dwenzel.game_screen.map.PointView;
import de.techfak.gse.dwenzel.game_screen.model.Player;
import de.techfak.gse.dwenzel.game_screen.model.PointChecker;
import de.techfak.gse.dwenzel.game_screen.model.Round;
import de.techfak.gse.dwenzel.game_screen.rules.Rules;
import de.techfak.gse.dwenzel.game_screen.view.AlertBox;
import de.techfak.gse.dwenzel.game_screen.view.FieldMarker;

public class GameLoop extends AppCompatActivity implements Runnable, Observer {
    private static final int THREAD_SLEEP = 1000;

    private final Context context;


    private final FieldMap fieldMap;
    private final FieldMapView fieldMapView;

    private final Dice dice;
    private final DiceView diceView;

    private final Round round;
    private final Rules rules;
    private final Player player;
    private final PointChecker pointChecker;

    private final PointView pointView;

    private final TextView textViewCurRound;

    private boolean isRunning;
    private boolean isFirstRound = true;

    /**
     * GameLoop is game controller.
     * Have the Button listener for a current round and controls
     * button input.
     *
     * @param context  is context from View
     * @param fieldMap for control the playground.
     * @param alertBox to alert the invalid turns.
     */
    public GameLoop(final Context context, final FieldMap fieldMap, final AlertBox alertBox) {
        this.context = context;
        this.fieldMap = fieldMap;
        fieldMapView = new FieldMapView(context);

        //init round and add the first round.
        round = new Round(context);
        round.addObserver(this);

        player = new Player(context);
        player.addObserver(this);

        dice = new Dice(context);
        dice.addObserver(this);
        diceView = new DiceView(context);

        rules = new Rules(alertBox);
        pointChecker = new PointChecker();
        pointView = new PointView(context);
        // round.add Round(fieldMap);
        /*start game loop thread.*/

        textViewCurRound = ((Activity) context)
                .findViewById(R.id.currentRoundView);

    }

    /**
     * This will be start the loop thread.
     */
    public void startThread() {
        final Thread myThread = new Thread(this);
        myThread.start();
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
                                //   fieldMapView.removeField(field);
                                fieldMarker.removeFieldMark(field);
                                round.removeTap(field);

                            } else {
                                //  fieldMapView.addField(field);
                                fieldMarker.addFieldMark(field);
                                round.addTap(field);

                            }


                        });
                    }
                }
                Thread.sleep(THREAD_SLEEP);
            } catch (InterruptedException e) {
                // e.printStackTrace();
            }
        }


    }

    /**
     * action to nextRound button.
     * and adds a new round if the turn is valid.
     */
    public void nextRound() {
        isRunning = false;
        if (isFirstRound) {

            updateRules();
            fieldMap.updateFieldMap(round.getCurrentTurnTaps());
            round.addRound();


            isFirstRound = false;
        } else {
            if (rules.checkRules(round.getCurrentTurnTaps())) {
                updateRules();
                fieldMap.updateFieldMap(round.getCurrentTurnTaps());
                pointChecker.checkPoints(fieldMap, round.getCurrentTurnTaps());
                round.addRound();

            } else {
                round.removeAllTaps();
            }
        }
    }

    /**
     * Update all rule settings.
     */
    private void updateRules() {
        rules.setFieldMap(fieldMap);
        updateDice();
    }

    /**
     * Roll new Dice every Round.
     */
    private void updateDice() {
        dice.createDice();
        rules.setDice(dice);

    }

    @Override
    public void update(final Observable argOne, final Object argTwo) {
        textViewCurRound.setText(context.getString(R.string.RoundName) + round.getRoundNumber());
        diceView.setDice(dice.getDice(), dice.getColorList());
        for (AbstractField field : round.getCurrentTurnTaps()) {
            fieldMapView.addField(field);
        }


    }
}
