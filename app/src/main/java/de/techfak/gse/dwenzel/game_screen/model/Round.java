package de.techfak.gse.dwenzel.game_screen.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Round extends Observable {
    private int currentRound;

    /*This list shows the current
    fields are tapped for current Turn*/
    private final List<Field> currentTurnTaps = new ArrayList<>();

    /**
     * save current Round information.
     */
    public Round() {
        currentRound = 0;

    }

    /**
     * set a new Round.
     * and set current round as null because need to get
     * a new FieldMap with current turn.
     */
    public void addRound() {

       // currentRound++;
        currentTurnTaps.clear();
     //   setChanged();
      //  notifyObservers();


    }

    /**
     * get the list of current marked fields.
     *
     * @return list of marked fields.
     */
    public List<Field> getCurrentTurnTaps() {
        return currentTurnTaps;
    }


    /**
     * setting up current turn taps for turn validation
     * it saved in the array list currentTurnTaps.
     *
     * @param field set field into currentTurnTaps.
     */
    public void addTap(final Field field) {

        currentTurnTaps.add(field);

    }

    /**
     * remove taps.
     *
     * @param field set field into currentTurnTaps.
     */
    public void removeTap(final Field field) {
        currentTurnTaps.remove(field);

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

    /**
     * This will remove all Current marked fields from the current Marked field
     * list.
     */
    public void removeAllTaps() {

        currentTurnTaps.clear();
    }

    /**
     * To get current roundNumber.
     *
     * @return current roundNumber as Integer.
     */
    public int getRoundNumber() {
        return currentRound;
    }


}
