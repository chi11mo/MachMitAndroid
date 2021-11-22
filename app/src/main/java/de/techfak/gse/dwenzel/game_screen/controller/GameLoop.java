package de.techfak.gse.dwenzel.game_screen.controller;

import android.content.Context;
import android.util.Log;

import de.techfak.gse.dwenzel.R;
import de.techfak.gse.dwenzel.game_screen.map.FieldMap;
import de.techfak.gse.dwenzel.game_screen.model.Round;

public class GameLoop implements Runnable {

    private final Context context;


    private FieldMap fieldMap;

    private boolean isRunning;
    private Round round;


    public GameLoop(Context context, FieldMap fieldMap) {
        this.context = context;
        this.fieldMap = fieldMap;
        //init round and add the first round.
        round = new Round(context);
        round.addRound(fieldMap);
        //start gameloop thread.

        Thread myThead = new Thread(this);
        myThead.start();
    }


    @Override
    public void run() {
        isRunning = true;
        while (isRunning) {
            try {


                for (int iRow = 0; iRow < context.getResources().getInteger(R.integer.PlaygroundRow); iRow++) {
                    for (int iCol = 0; iCol < context.getResources().getInteger(R.integer.PlaygroundCol); iCol++) {
                        int finalIRow = iRow;

                        int finalICol = iCol;
                        fieldMap.getFields()[iRow][iCol].getButton().setOnClickListener(event -> {
                            /**
                             if (fieldMap.getFields()[row][col].isCrossed()) {
                             fieldMap.getFields()[row][col].setIsCrossed(false);
                             fieldMap.getFields()[row][col].getButton().setImageDrawable(fieldMap.getFields()[row][col].getDrawableField(true));
                             } else {
                             fieldMap.getFields()[row][col].setIsCrossed(true);
                             fieldMap.getFields()[row][col].getButton().setImageDrawable(fieldMap.getFields()[row][col].getDrawableField(false));
                             }
                             **/
                            Log.d(":Button event", (finalIRow) + "/" + finalICol);
                            Log.d(":Button event", String.valueOf(fieldMap.getFields()[finalIRow][finalICol].isCrossed()));
                        });
                    }
                }
                //context.get
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

/*
        for (int iRow = 0; iRow < context.getResources().getInteger(R.integer.PlaygroundRow); iRow++) {
            for (int iCol = 0; iCol < context.getResources().getInteger(R.integer.PlaygroundCol); iCol++) {
                int finalIRow = iRow;
                fieldMap.getFields()[iRow][iCol].getButton().setOnClickListener(event -> {
                    Log.d(":Button event", String.valueOf(finalIRow));
                });
            }
        }
*/
    }

    public void setIsRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }


}
