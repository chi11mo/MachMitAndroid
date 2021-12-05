package de.techfak.gse.dwenzel.game_screen.dice;

import android.graphics.drawable.Drawable;

import de.techfak.gse.dwenzel.game_screen.model.DiceSpriteSheet;

public class FiveAbstractDice extends AbstractDice {
    private final Drawable drawableDice;

    /**
     * Abstract class to define drawable for dice eyes.
     *
     * @param diceSpriteSheet
     */
    public FiveAbstractDice(final DiceSpriteSheet diceSpriteSheet) {
        super(diceSpriteSheet);
        drawableDice = diceSpriteSheet.getFiveDiceDrawable();
    }

    /**
     * get the right drawable.
     *
     * @return drawable of dice eye.
     */
    @Override
    public Drawable getDrawableDice() {
        return drawableDice;
    }
}
