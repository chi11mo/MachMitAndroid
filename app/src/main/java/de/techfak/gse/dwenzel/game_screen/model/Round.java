package de.techfak.gse.dwenzel.game_screen.model;

import android.app.Activity;
import android.content.Context;
import android.widget.TextView;

import de.techfak.gse.dwenzel.R;
import de.techfak.gse.dwenzel.game_screen.map.FieldMap;

public class Round {
    private final Context context;
    private int currentRound;
    private FieldMap fieldMap;
    private TextView textViewCurRound;

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
     */
    public void addRound(FieldMap fieldMap) {
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
}
