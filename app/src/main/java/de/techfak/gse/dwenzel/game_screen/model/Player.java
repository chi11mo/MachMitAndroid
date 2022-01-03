package de.techfak.gse.dwenzel.game_screen.model;

import android.content.Context;

import java.util.Observable;

public class Player extends Observable {
    private final Context context;



    private int currentPoints;

    public Player(final Context context){
        this.context = context;
    }


    public int getCurrentPoints() {
        return currentPoints;
    }

    public void setCurrentPoints(int currentPoints) {
        this.currentPoints = currentPoints;
        setChanged();
        notifyObservers();
    }
}
