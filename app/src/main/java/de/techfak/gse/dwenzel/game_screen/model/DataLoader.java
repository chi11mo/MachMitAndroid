package de.techfak.gse.dwenzel.game_screen.model;

import android.content.Context;
import android.graphics.drawable.Drawable;

import java.io.InputStream;

/**
 * Data Loader just collection of methods to load something from assets.
 */
public class DataLoader {
    /**
     * This method loads Drawables from the Assets folder.
     *
     * @param context context is Activity
     * @param path    path where drawable is.
     * @return return drawable if possible.
     */
    public Drawable loadDrawableFromAssets(final Context context, final String path) {
        try (InputStream stream = context.getAssets().open(path)) {
            return Drawable.createFromStream(stream, null);
        } catch (Exception ignored) {
        }
        return null;
    }
}
