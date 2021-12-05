package de.techfak.gse.dwenzel.game_screen.dice;

import de.techfak.gse.dwenzel.game_screen.model.DiceSpriteSheet;

public class DiceCreator {
    private static final int MAX_DICE_EYE = 6;
    private final DiceSpriteSheet diceSpriteSheet;
    private AbstractDice[] dice;

    /**
     * creates drawable for the dices.
     *
     * @param diceSpriteSheet dice spriite sheet.
     */
    public DiceCreator(final DiceSpriteSheet diceSpriteSheet) {
        this.diceSpriteSheet = diceSpriteSheet;
        dice = new AbstractDice[MAX_DICE_EYE];
        initDices();
    }

    /**
     * initialized dices.
     */
    private void initDices() {
        for (int diceIndex = 0; diceIndex < MAX_DICE_EYE; diceIndex++) {
            dice[diceIndex] = AbstractDice.getDice(diceIndex, diceSpriteSheet);
        }
    }

    /**
     * tog et the dices.
     *
     * @return dice array.
     */
    public AbstractDice[] getDice() {
        return dice;
    }
}
