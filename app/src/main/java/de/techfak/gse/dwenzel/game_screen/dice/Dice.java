package de.techfak.gse.dwenzel.game_screen.dice;

import android.content.Context;

import java.util.List;

import de.techfak.gse.dwenzel.sprite_sheet.DiceSpriteSheet;

public class Dice {
    private final DiceSpriteSheet diceSpriteSheet;

    private final DiceView diceView;
    private final Context context;
    private int[] diceColor;
    private int[] diceNumbers;

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
        AbstractDice[] dice = diceRandomizer.getDices();
        final List colorList = colorRandomizer.getColorList();
        diceColor = colorRandomizer.getColorNumbers();
        diceNumbers = diceRandomizer.getDiceNumbers();
        diceView.setDice(dice, colorList);

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
}
