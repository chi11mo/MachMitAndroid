package de.techfak.gse.dwenzel.game_screen.model;

import android.content.Context;
import android.graphics.drawable.Drawable;

public class ButtonSpriteSheet {
    DataLoader dataLoader = new DataLoader();
    Context context;

    public ButtonSpriteSheet(Context context) {
        this.context = context;
    }

    public Drawable getYellowFieldDrawable(boolean isPressed) {
        if (isPressed) {
            return dataLoader.loadDrawableFromAssets(context, "icon_pressed_yellow.png");
        }
        return dataLoader.loadDrawableFromAssets(context, "icon_notPressed_yellow.png");
    }


    public Drawable getGreenFieldDrawable(boolean isCrossed) {
        if (isCrossed) {
            return dataLoader.loadDrawableFromAssets(context, "icon_pressed_green.png");
        }
        return dataLoader.loadDrawableFromAssets(context, "icon_notPressed_green.png");
    }

    public Drawable getRedFieldDrawable(boolean isCrossed) {
        if (isCrossed) {
            return dataLoader.loadDrawableFromAssets(context, "icon_pressed_red.png");
        }
        return dataLoader.loadDrawableFromAssets(context, "icon_notPressed_red.png");
    }

    public Drawable getOrangeFieldDrawable(boolean isCrossed) {
        if (isCrossed) {
            return dataLoader.loadDrawableFromAssets(context, "icon_pressed_orange.png");
        }
        return dataLoader.loadDrawableFromAssets(context, "icon_notPressed_orange.png");
    }

    public Drawable getBlueFieldDrawable(boolean isCrossed) {
        if (isCrossed) {
            return dataLoader.loadDrawableFromAssets(context, "icon_pressed_blue.png");
        }
        return dataLoader.loadDrawableFromAssets(context, "icon_notPressed_blue.png");
    }
}
