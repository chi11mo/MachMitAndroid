package de.techfak.gse.dwenzel.game_screen.controller;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import de.techfak.gse.dwenzel.R;
import de.techfak.gse.dwenzel.end_screen.EndGameActivity;
import de.techfak.gse.dwenzel.game_screen.model.Field;
import de.techfak.gse.dwenzel.game_screen.model.FieldMap;
import de.techfak.gse.dwenzel.game_screen.model.Player;
import de.techfak.gse.dwenzel.game_screen.model.PointChecker;
import de.techfak.gse.dwenzel.game_screen.model.Round;
import de.techfak.gse.dwenzel.game_screen.model.rules.Rules;
import de.techfak.gse.dwenzel.game_screen.view.AlertBox;
import de.techfak.gse.dwenzel.game_screen.view.DiceView;
import de.techfak.gse.dwenzel.game_screen.view.FieldMapView;
import de.techfak.gse.dwenzel.game_screen.view.PointView;
import de.techfak.gse.dwenzel.server_com.server_controller.DiceServerInteraction;
import de.techfak.gse.dwenzel.server_com.server_controller.GameStatusServerInteraction;
import de.techfak.gse.dwenzel.server_com.server_controller.PlayerServerInteraction;
import de.techfak.gse.dwenzel.server_com.server_controller.RoundServerInteraction;
import de.techfak.gse.multiplayer.game.GameStatus;
import de.techfak.gse.multiplayer.server.response_body.PlayerResponse;

public class GameLoopServer extends AppCompatActivity implements Runnable, Observer {
    private static final int THREAD_SLEEP = 1000;
    private static final int THREAD_SLEEP_SERVER = 10000;

    private final Context context;

    private final String url;
    private final String name;
    private final FieldMap fieldMap;
    private final FieldMapView fieldMapView;

    private final DiceServerInteraction diceServerInteraction;
    private final DiceView diceView;

    private final Round round;
    private final RoundServerInteraction roundServerInteraction;
    private final Rules rules;

    private final Player player;
    private final PlayerServerInteraction playerServerInteraction;
    private final PointChecker pointChecker;

    private final PointView pointView;

    private final TextView textViewCurRound;
    private final AlertBox alertBox;
    private boolean isRunning;

    private GameStatus gameStatus;
    private Thread myThread;
    private GameStatusServerInteraction gameStatusServerInteraction;
    private boolean isGameEnd = false;

    /**
     * Game loop Server site.
     *
     * @param context  app.
     * @param fieldMap fieldmap.
     * @param alertBox alertbox for invalid rules.
     * @param url      server url.
     * @param name     name to log serve.
     */
    public GameLoopServer(final Context context, final FieldMap fieldMap,
                          final AlertBox alertBox, final String url, final String name) {
        this.context = context;
        this.fieldMap = fieldMap;
        this.alertBox = alertBox;
        this.url = url;
        this.name = name;

        fieldMapView = new FieldMapView(context);
        fieldMapView.createFieldMapGameboard(fieldMap);

        player = new Player(name);


        //change a server version.
        round = new Round();
        round.addObserver(this);

        rules = new Rules();
        rules.addObserver(this);

        diceView = new DiceView(context);

        pointChecker = new PointChecker();
        pointView = new PointView(context);

        textViewCurRound = ((Activity) context)
                .findViewById(R.id.currentRoundView);

        gameStatusServerInteraction = new GameStatusServerInteraction(context, url, name);
        gameStatusServerInteraction.addObserver(this);

        gameStatusServerInteraction.getGameStatusRequest();

        diceServerInteraction = new DiceServerInteraction(context, url, name);
        diceServerInteraction.addObserver(this);

        playerServerInteraction = new PlayerServerInteraction(context, url, name);
        playerServerInteraction.addObserver(this);

        roundServerInteraction = new RoundServerInteraction(context, url, name);
        roundServerInteraction.addObserver(this);



        gameStatus = GameStatus.NOT_STARTED;


    }


    /**
     * This will be start the loop thread.
     */
    public void startThread() {
        myThread = new Thread(this);
        myThread.start();
    }

    /**
     * Stop the thread.
     */
    private void stopThread() {
        myThread.stop();
    }

    /**
     * Changes in View for new round.
     */
    public void updateNewRound() {
        if (rules.checkRules(round.getCurrentTurnTaps())) {
            fieldMap.updateFieldMap(round.getCurrentTurnTaps());
            pointChecker.checkPoints(fieldMap, round.getCurrentTurnTaps());
            fieldMapView.removeAllMarks(fieldMap);
            player.setCurrentPoints(pointChecker.getPoints());
            roundServerInteraction.setNextRoundRequestPOST(player.getCurrentPoints());
            round.addRound();
            getServerInformation();
            if (pointChecker.isGameEnd()) {
                gameStatusServerInteraction.setGameStatusRequestPOST(gameStatus);
            }


        } else {
            for (Field field : round.getCurrentTurnTaps()) {
                field.setIsCrossed(false);
            }
            fieldMapView.removeAllMarks(fieldMap);
            round.removeAllTaps();
        }

    }

    /**
     * to set next Round and if gameStatus is NOT Running run dialog to start it.
     */
    public void nextRound() {


        if (gameStatus == GameStatus.NOT_STARTED) {
            isRunning = false;
            ifServerNotStarted();

        }
        if (!playerServerInteraction.isPlayerFinnishedRound(player.getPlayerName())
                && gameStatus == GameStatus.RUNNING) {
            isRunning = false;
            getServerInformation();
            updateNewRound();

        }
        getServerInformation();

    }

    /**
     * Polling server Information.
     */
    private void getServerInformation() {
        try {
            diceServerInteraction.getDiceRequest();
            roundServerInteraction.getRoundRequest();
            playerServerInteraction.getPlayerRequest();
            gameStatusServerInteraction.getGameStatusRequest();
            if (gameStatus == GameStatus.FINISHED) {
                jumpToEndGame();
                isRunning = false;
            }
            Thread.sleep(THREAD_SLEEP);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run() {
        isRunning = true;
        //If server not started wait for start.
        while (gameStatus == GameStatus.NOT_STARTED) {
            try {
                gameStatusServerInteraction.getGameStatusRequest();
                Thread.sleep(THREAD_SLEEP_SERVER);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        getServerInformation();
        while (playerServerInteraction.isPlayerFinnishedRound(player.getPlayerName())) {
            getServerInformation();
        }

        //When server Start go running Button Observer.
        while (isRunning) {

            if (gameStatus != null
                    && gameStatus == GameStatus.RUNNING
                    && !playerServerInteraction.isPlayerFinnishedRound(player.getPlayerName())) {
                checkButtons();
            }
        }
    }

    /**
     * Checking buttons.
     */
    public void checkButtons() {
        try {
            for (int iRow = 0; iRow
                    < context.getResources().getInteger(R.integer.PlaygroundRow); iRow++) {
                for (int iCol = 0; iCol
                        < context.getResources().getInteger(R.integer.PlaygroundCol); iCol++) {
                    int finalIRow = iRow;
                    int finalICol = iCol;

                    fieldMapView.getButtonGameGroupe()[iRow][iCol]
                            .setOnClickListener(event -> {
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


    @Override
    public void update(final Observable observable, final Object object) {

        if (rules.getMissedRuleInfo() != null) {
            alertBox.showAlert(context.getString(R.string.no_valid_turn),
                    rules.getMissedRuleInfo());
        } else {
            textViewCurRound.setText(
                    context.getString(R.string.round_name_plate)
                            + roundServerInteraction.getRoundNumber());
            if (gameStatus == GameStatus.RUNNING
                    && diceServerInteraction.getDiceResponse() != null
                    && observable.equals(diceServerInteraction)) {
                diceView.setDiceFromServer(diceServerInteraction.getDiceResponse());
                updateRules();
            }
            if (observable.equals(playerServerInteraction)) {
                pointView.setPlayers(playerServerInteraction.getPlayers());
            }
            fieldMapView.updateFieldMap(fieldMap);
            pointView.setPoints(null, pointChecker);
        }

        gameStatus = gameStatusServerInteraction.getGameStatus();


    }

    /**
     * Update all rule settings.
     */
    private void updateRules() {
        rules.setFieldMap(fieldMap);
        rules.setDice(diceView.getDice());
    }


    /**
     * if win condition is accepted then go to end screen.
     */
    private void jumpToEndGame() {
        if (!isGameEnd) {
            isGameEnd = true;
            finishAffinity();
            final Intent myIntent = new Intent(context, EndGameActivity.class);
            final List<PlayerResponse> playerResponse = playerServerInteraction.getPlayers();
            EndCardSorter endCard = new EndCardSorter();
            List<String> endCardList = endCard.sortForEndCard(playerResponse);
            myIntent.putStringArrayListExtra("EndCardList", (ArrayList<String>) endCardList);
            context.startActivity(myIntent);
            // stopThread();
            finish();
        }
    }

    /**
     * Method to get a dialog if game not RUNNING.
     */
    public void ifServerNotStarted() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Spiel nicht gestartet ")
                .setCancelable(false)
                .setMessage("Wollen sie das Spiel starten ?")
                .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int which) {
                        gameStatusServerInteraction.setGameStatusRequestPOST(gameStatus);
                    }
                }).setNegativeButton("Nein", new DialogInterface.OnClickListener() {
            public void onClick(final DialogInterface dialog, final int which) {
            }
        });
        builder.show();

    }
}
