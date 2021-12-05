package de.techfak.gse.dwenzel.game_screen.dice;

import android.graphics.drawable.Drawable;

import de.techfak.gse.dwenzel.game_screen.model.DiceSpriteSheet;

public class SixAbstractDice extends AbstractDice {
    private final Drawable drawableDice;

    /**
     * Abstract class to define drawable for dice eyes.
     *
     * @param diceSpriteSheet
     */
    public SixAbstractDice(final DiceSpriteSheet diceSpriteSheet) {
        super(diceSpriteSheet);
        drawableDice = diceSpriteSheet.getSixDiceDrawable();
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
