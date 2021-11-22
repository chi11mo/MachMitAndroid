package de.techfak.gse.dwenzel.game_screen.map;

import android.graphics.drawable.Drawable;

import de.techfak.gse.dwenzel.game_screen.model.ButtonSpriteSheet;

public class BlueField extends Field {
    private final Drawable drawableField;

    /**
     * Method to create field.
     *
     * @param isCrossed         test is field cross.
     * @param buttonSpriteSheet button image sheet.
     */
    public BlueField(final boolean isCrossed,
                     final ButtonSpriteSheet buttonSpriteSheet) {
        super(isCrossed, buttonSpriteSheet);
        drawableField = buttonSpriteSheet.getBlueFieldDrawable(isCrossed);
    }

    /**
     * Method to ge drawable tosettingup ImageButtons.
     *
     * @return drawable for button.
     */
    public Drawable getDrawableField() {
        return drawableField;
    }

    @Override
    public Drawable getDrawableField(boolean isCrossed) {
        return buttonSpriteSheet.getBlueFieldDrawable(isCrossed);
    }
}
