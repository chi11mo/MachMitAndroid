package de.techfak.gse.dwenzel.game_screen.dice;

import android.graphics.drawable.Drawable;

import de.techfak.gse.dwenzel.sprite_sheet.DiceSpriteSheet;

public class TwoAbstractDice extends AbstractDice {
    private final Drawable drawableDice;

    /**
     * Abstract class to define drawable for dice eyes.
     *
     * @param diceSpriteSheet
     */
    public TwoAbstractDice(final DiceSpriteSheet diceSpriteSheet) {
        super(diceSpriteSheet);
        drawableDice = diceSpriteSheet.getTwoDiceDrawable();
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
