package de.techfak.gse.dwenzel.game_screen.dice;

import android.content.Context;

import de.techfak.gse.dwenzel.game_screen.model.DiceSpriteSheet;

public class Dice {
    private final DiceSpriteSheet diceSpriteSheet;
    private DiceView diceView;
    private final Context context;

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
     * Creates the dices and uptade the dice View.
     */
    public void createDice() {
        DiceRandomizer diceRandomizer = new DiceRandomizer(diceSpriteSheet);
        ColorRandomizer colorRandomizer = new ColorRandomizer(context);
        AbstractDice[] dice = diceRandomizer.getDices();
        diceView.setDice(dice, colorRandomizer.getColorList());

    }
}
