package de.techfak.gse.dwenzel.game_screen.model;

import android.app.Activity;
import android.content.Context;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import de.techfak.gse.dwenzel.R;
import de.techfak.gse.dwenzel.game_screen.map.Field;
import de.techfak.gse.dwenzel.game_screen.map.FieldMap;

public class Round {
    private final Context context;
    private int currentRound;
    private FieldMap fieldMap;
    private TextView textViewCurRound;
    /*This list shows the current
    fields are tapped for current Turn*/
    private List<Field> currentTurnTaps = new ArrayList<>();

    /**
     * save curren Round information.
     * @param context view.
     */
    public Round(final Context context) {
        this.context = context;
        currentRound = 0;
        textViewCurRound = ((Activity) context)
                .findViewById(R.id.currentRoundView);
        textViewCurRound.setBackgroundColor(context.getColor(R.color.green));
    }

    /**
     * set a new Round.
     * and set current round as null because need to get
     * a new FieldMap with current turn.
     * @param fieldMap field setting map for current round.
     */
    public void addRound(final FieldMap fieldMap) {
        currentRound++;
        textViewCurRound.setText("Round : " + currentRound);
        this.fieldMap = fieldMap;
    }

    /**
     * Get the current round as a int.
     *
     * @return current round as a int.
     */
    public int getCurrentRound() {
        return currentRound;
    }

    /**
     * setting up current turn taps for turn validation
     * it saved in the array list currentTurnTaps.
     *
     * @param field set field into currentTurnTaps.
     */
    public void addTap(final Field field) {
        if (isFieldCurrentCross(field)) {
            currentTurnTaps.remove(field);
        } else {
            currentTurnTaps.add(field);
        }
        //Log.d("Current saved Taps", String.valueOf(currentTurnTaps));
    }

    /**
     * check if field is already in currentTurnTaps.
     *
     * @param field in currentTurnTaps.
     * @return if is already in currentTurnTaps.
     */
    public boolean isFieldCurrentCross(final Field field) {
        for (final Field fieldSrc : currentTurnTaps) {
            if (fieldSrc.equals(field)) {
                return true;
            }
        }
        return false;
    }
}
