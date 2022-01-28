package de.techfak.gse.dwenzel.game_screen.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Dice extends Observable {
    static final int MAX_DICE_EYES = 6;
    static final int MAX_DICE_COLOR_IDX = 5;
    static final int MAX_DICE = 3;


    private final List<Integer> colorList = new ArrayList<Integer>();
    private final List<Integer> numberList = new ArrayList<Integer>();

    /**
     * To generate Dices.
     * Numbers and Color
     */
    public Dice() {
        rollDice();
    }

    /**
     * Method to roll the dice with random numbers.
     * and save results in list.¬
     */
    public void rollDice() {
        colorList.clear();
        numberList.clear();
        for (int idx = 0; idx < MAX_DICE; idx++) {
            colorList.add(getRandomNumber(0, MAX_DICE_COLOR_IDX));
        }
        for (int idx = 0; idx < MAX_DICE; idx++) {
            numberList.add(getRandomNumber(0, MAX_DICE_EYES));
        }

        setChanged();
        notifyObservers();
    }

    /**
     * Method to get random Integer with min/max Range.
     * @param min min range.
     * @param max max range.
     * @return rnd number.
     */
    private int getRandomNumber(final int min, final int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    /**
     * List with color idx.
     * @return List with color idx.
     */
    public List<Integer> getColorList() {
        return colorList;
    }

    /**
     * List with number idx.
     * @return List with number idx.
     */
    public List<Integer> getNumberList() {
        return numberList;
    }


}
