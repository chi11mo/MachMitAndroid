package de.techfak.gse.dwenzel.game_screen.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import de.techfak.gse.dwenzel.game_screen.map.AbstractField;
import de.techfak.gse.dwenzel.game_screen.map.FieldMap;
import de.techfak.gse.dwenzel.game_screen.view.FieldMarker;

public class Round extends Observable {
    private final Context context;
    private int currentRound;

    /*This list shows the current
    fields are tapped for current Turn*/
    private final List<AbstractField> currentTurnTaps = new ArrayList<>();

    /**
     * save current Round information.
     *
     * @param context view.
     */
    public Round(final Context context) {
        this.context = context;
        currentRound = 0;

    }

    /**
     * set a new Round.
     * and set current round as null because need to get
     * a new FieldMap with current turn.
     *
     * @param fieldMap field setting map for current round.
     */
    public void addRound() {

        //BoardLoader loader = new BoardLoader(context);
        currentRound++;
        //loader.updateField(fieldMap);
        setChanged();
        notifyObservers();
        currentTurnTaps.clear();

    }

    /**
     * get the list of current marked fields.
     *
     * @return list of marked fields.
     */
    public List<AbstractField> getCurrentTurnTaps() {
        return currentTurnTaps;
    }


    /**
     * setting up current turn taps for turn validation
     * it saved in the array list currentTurnTaps.
     *
     * @param abstractField set field into currentTurnTaps.
     */
    public void addTap(final AbstractField abstractField) {
        if (isFieldCurrentCross(abstractField)) {
            currentTurnTaps.remove(abstractField);
        } else {
            currentTurnTaps.add(abstractField);
        }
        //Log.d("Current saved Taps", String.valueOf(currentTurnTaps));
    }
    /**
     * remove taps.
     *
     * @param field set field into currentTurnTaps.
     */
    public void removeTap(final AbstractField field) {
        currentTurnTaps.remove(field);
    }


    /**
     * check if field is already in currentTurnTaps.
     *
     * @param abstractField in currentTurnTaps.
     * @return if is already in currentTurnTaps.
     */
    public boolean isFieldCurrentCross(final AbstractField abstractField) {
        for (final AbstractField abstractFieldSrc : currentTurnTaps) {
            if (abstractFieldSrc.equals(abstractField)) {
                return true;
            }
        }
        return false;
    }

    /**
     * This will remove all Current marked fields from the current Marked field
     * list.
     */
    public void removeAllTaps() {
        FieldMarker fieldMarker = new FieldMarker();
        for (AbstractField field : currentTurnTaps) {
            fieldMarker.removeFieldMark(field);
            // removeTap(field);
        }
        currentTurnTaps.clear();
    }

    public int getRoundNumber() {
        return currentRound;
    }


}
