package de.techfak.gse.dwenzel.game_screen.map;

import android.graphics.drawable.Drawable;

import de.techfak.gse.dwenzel.game_screen.model.ButtonSpriteSheet;

public class RedAbstractField extends AbstractField {
    private final Drawable drawableField;

    /**
     * Method to create field.
     *
     * @param isCrossed         test is field cross.
     * @param buttonSpriteSheet button image sheet.
     */
    public RedAbstractField(final boolean isCrossed,
                            final ButtonSpriteSheet buttonSpriteSheet) {
        super(isCrossed, buttonSpriteSheet);
        drawableField = buttonSpriteSheet.getRedFieldDrawable(isCrossed);
    }

    /**
     * Method to ge drawable to setting up ImageButtons.
     *
     * @return drawable for button.
     */
    public Drawable getDrawableField() {
        return drawableField;
    }

    @Override
    public Drawable getDrawableField(final boolean isCrossed) {
        return buttonSpriteSheet.getRedFieldDrawable(isCrossed);
    }
}