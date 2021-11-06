package de.techfak.gse.dwenzel.game_screen.model;

import android.content.Context;
import android.graphics.drawable.Drawable;

import java.io.InputStream;

public class DataLoader {
    public Drawable loadDrawableFromAssets(Context context, String path)
    {
        InputStream stream = null;
        try
        {
            stream = context.getAssets().open(path);
            return Drawable.createFromStream(stream, null);
        }
        catch (Exception ignored) {} finally
        {
            try
            {
                if(stream != null)
                {
                    stream.close();
                }
            } catch (Exception ignored) {}
        }
        return null;
    }
}
