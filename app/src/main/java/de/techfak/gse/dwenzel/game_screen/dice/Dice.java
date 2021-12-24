package de.techfak.gse.dwenzel.game_screen.dice;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import de.techfak.gse.dwenzel.sprite_sheet.DiceSpriteSheet;

public class Dice extends Observable {
    private final DiceSpriteSheet diceSpriteSheet;

    private final DiceView diceView;



    private AbstractDice[] dice;
    private final Context context;
    private int[] diceColor;
    private int[] diceNumbers;



    private List colorList = new ArrayList<>();

    /**
     * To generate Dices.
     * Numbers and Color
     *
     * @param context context from app.
     */
    public Dice(final Context context) {
        diceSpriteSheet = new DiceSpriteSheet(context);
        this.context = context;
        diceView = new DiceView(context);
    }

    /**
     * Creates the dices and update the dice View.
     */
    public void createDice() {
        DiceRandomizer diceRandomizer = new DiceRandomizer(diceSpriteSheet);
        ColorRandomizer colorRandomizer = new ColorRandomizer(context);
         dice = diceRandomizer.getDices();
        colorList = colorRandomizer.getColorList();
        diceColor = colorRandomizer.getColorNumbers();
        diceNumbers = diceRandomizer.getDiceNumbers();
        setChanged();
        notifyObservers();


    }

    /**
     * get rolled dice eyes.
     *
     * @return array with all rolled dice numbers.
     */
    public int[] getDiceEyes() {
        return diceNumbers;
    }

    /**
     * get rolled colors.
     *
     * @return rolled colors.
     */
    public int[] getDiceColors() {
        return diceColor;
    }

    public List getColorList() {
        return colorList;
    }
    public AbstractDice[] getDice() {
        return dice;
    }
}
