package de.techfak.gse.dwenzel.game_screen.model;

import android.content.Context;

import java.util.Observable;

public class Player extends Observable {
    private final Context context;


    private int currentPoints;


    private String playerName;

    /**
     * Saves current player spot.
     *
     * @param context    context for view.
     * @param playerName name of player.
     */
    public Player(final Context context, final String playerName) {
        this.context = context;
        this.playerName = playerName;
    }

    /**
     * current points.
     *
     * @return int with current points.
     */
    public int getCurrentPoints() {
        return currentPoints;
    }

    /**
     * setting the current points.
     *
     * @param currentPoints current points.
     */
    public void setCurrentPoints(final int currentPoints) {
        this.currentPoints = currentPoints;
        setChanged();
        notifyObservers();
    }

    /**
     * getter for the name.
     *
     * @return string name.
     */
    public String getPlayerName() {
        return playerName;
    }
}
