package de.techfak.gse.dwenzel.game_screen.dice;

import java.util.Random;

import de.techfak.gse.dwenzel.game_screen.model.DiceSpriteSheet;

public class DiceRandomizer {
    public static final int DICE_MAX_INDEX = 6;
    private final DiceSpriteSheet diceSpriteSheet;
    private final DiceCreator diceCreator;
    private Random random;

    /**
     * randomize dices.
     *
     * @param diceSpriteSheet spriteSheet dices.
     */
    public DiceRandomizer(final DiceSpriteSheet diceSpriteSheet) {
        this.diceSpriteSheet = diceSpriteSheet;
        diceCreator = new DiceCreator(diceSpriteSheet);
        random = new Random();
    }

    /**
     * Create a array with all crates dices.
     *
     * @return array with dices.
     */
    public AbstractDice[] getDices() {
        AbstractDice[] dices;
        dices = diceCreator.getDice();

        dices[0] = diceCreator.getDice()[randomNumber()];
        dices[1] = diceCreator.getDice()[randomNumber()];
        dices[2] = diceCreator.getDice()[randomNumber()];

        return dices;
    }


    /**
     * Creates DiceNumber != 0.
     *
     * @return get DiceNumber.
     */
    private int randomNumber() {
        int tmpNumber = 0;

        do {

            tmpNumber = random.nextInt(DICE_MAX_INDEX);
        } while (tmpNumber == 0);
        return tmpNumber;
    }
}
