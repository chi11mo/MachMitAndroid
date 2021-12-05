package de.techfak.gse.dwenzel.game_screen.model;

import android.content.Context;
import android.graphics.drawable.Drawable;

public class DiceSpriteSheet {
    private DataLoader dataLoader = new DataLoader();
    private Context context;


    public DiceSpriteSheet(final Context context) {
        this.context = context;
    }


    /**
     * get the right drawable and loaded it from png resources.
     *
     * @return the loaded drawable.
     */
    public Drawable getOneDiceDrawable() {

        return dataLoader.loadDrawableFromAssets(context, "dice/dice-1.png");
    }
    /**
     * get the right drawable and loaded it from png resources.
     *
     * @return the loaded drawable.
     */
    public Drawable getTwoDiceDrawable() {

        return dataLoader.loadDrawableFromAssets(context, "dice/dice-2.png");
    }
    /**
     * get the right drawable and loaded it from png resources.
     *
     * @return the loaded drawable.
     */
    public Drawable getThreeDiceDrawable() {

        return dataLoader.loadDrawableFromAssets(context, "dice/dice-3.png");
    }
    /**
     * get the right drawable and loaded it from png resources.
     *
     * @return the loaded drawable.
     */
    public Drawable getFourDiceDrawable() {

        return dataLoader.loadDrawableFromAssets(context, "dice/dice-4.png");
    }
    /**
     * get the right drawable and loaded it from png resources.
     *
     * @return the loaded drawable.
     */
    public Drawable getFiveDiceDrawable() {

        return dataLoader.loadDrawableFromAssets(context, "dice/dice-5.png");
    }
    /**
     * get the right drawable and loaded it from png resources.
     *
     * @return the loaded drawable.
     */
    public Drawable getSixDiceDrawable() {

        return dataLoader.loadDrawableFromAssets(context, "dice/dice-6.png");
    }
}
