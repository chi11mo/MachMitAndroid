package de.techfak.gse.dwenzel.game_screen.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Observable;
import java.util.Observer;

import de.techfak.gse.dwenzel.R;
import de.techfak.gse.dwenzel.end_screen.EndGameActivity;
import de.techfak.gse.dwenzel.game_screen.dice.Dice;
import de.techfak.gse.dwenzel.game_screen.dice.DiceView;
import de.techfak.gse.dwenzel.game_screen.map.Field;
import de.techfak.gse.dwenzel.game_screen.map.FieldMap;
import de.techfak.gse.dwenzel.game_screen.map.FieldMapView;
import de.techfak.gse.dwenzel.game_screen.map.PointView;
import de.techfak.gse.dwenzel.game_screen.model.Player;
import de.techfak.gse.dwenzel.game_screen.model.PointChecker;
import de.techfak.gse.dwenzel.game_screen.model.Round;
import de.techfak.gse.dwenzel.game_screen.rules.Rules;
import de.techfak.gse.dwenzel.game_screen.view.AlertBox;

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
        fieldMapView = new FieldMapView(context, fieldMap);

        //init round and add the first round.
        round = new Round();
        round.addObserver(this);

        player = new Player(context, context.getString(R.string.PlayerOneName));
        player.addObserver(this);

        dice = new Dice();
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


        while (isRunning) {
            try {


                for (int iRow = 0; iRow
                        < context.getResources().getInteger(R.integer.PlaygroundRow); iRow++) {
                    for (int iCol = 0; iCol
                            < context.getResources().getInteger(R.integer.PlaygroundCol); iCol++) {
                        int finalIRow = iRow;

                        int finalICol = iCol;
                        fieldMapView.getButtonGameGroupe()[iRow][iCol].setOnClickListener(event -> {
                            Field field = fieldMap.getFields()[finalIRow][finalICol];

                            if (fieldMap.getFields()[finalIRow][finalICol].isCrossed()) {
                                round.removeTap(field);
                                field.setIsCrossed(false);
                                fieldMapView.removeFieldMark(finalIRow, finalICol);

                            } else {

                                round.addTap(field);
                                field.setIsCrossed(true);
                                fieldMapView.addFieldMark(finalIRow, finalICol);

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
                player.setCurrentPoints(pointChecker.getPoints());
                round.addRound();
                if (pointChecker.isGameEnd()) {
                    jumpToEndGame();
                }

            } else {
                for (Field field : round.getCurrentTurnTaps()) {
                    field.setIsCrossed(false);
                }
                fieldMapView.removeAllMarks();
                round.removeAllTaps();
            }
        }
    }

    /**
     * if win condition is accepted then go to end screen.
     */
    private void jumpToEndGame() {
        finishAffinity();
        final Intent myIntent = new Intent(context, EndGameActivity.class);
        String endCard = player.getPlayerName() + " : " + player.getCurrentPoints();
        myIntent.putExtra("EndPoints", endCard);
        context.startActivity(myIntent);
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
        dice.rollDice();
        rules.setDice(dice);

    }

    @Override
    public void update(final Observable argOne, final Object argTwo) {
        textViewCurRound.setText(context.getString(R.string.round_name_plate) + round.getRoundNumber());
        diceView.setDice(dice.getNumberList(), dice.getColorList());
        //  String log = String.valueOf(dice.getNumberList()) + String.valueOf(dice.getColorList());
        //  Log.d("Dice Roll", log);
        fieldMapView.updateFieldMap(fieldMap);
        pointView.setPoints(player, pointChecker);


    }
}
