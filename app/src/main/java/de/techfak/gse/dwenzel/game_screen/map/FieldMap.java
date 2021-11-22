package de.techfak.gse.dwenzel.game_screen.map;

import android.graphics.drawable.Drawable;
import android.util.Log;

import de.techfak.gse.dwenzel.game_screen.model.ButtonSpriteSheet;

public class FieldMap {

    public final int maxRow;
    public final int maxCol;
    private final ButtonSpriteSheet buttonSpriteSheet;
    private MapLayout mapLayout;
    private Field[][] fields;
private Drawable drawable;
    public FieldMap(final ButtonSpriteSheet buttonSpriteSheet, String boardLayout, final int maxRow, final int maxCol) {

        this.buttonSpriteSheet = buttonSpriteSheet;
        this.maxRow = maxRow;
        this.maxCol = maxCol;
        mapLayout = new MapLayout(boardLayout, maxRow, maxCol);
        fields = new Field[maxRow][maxCol];
        initializeFieldMap();
    }


    private void initializeFieldMap() {
        final int[][] layout = mapLayout.getLayout();
        final boolean[][] isCrossed = mapLayout.getIsCrossed();

        for (int iRow = 0; iRow < maxRow; iRow++) {
            for (int iCol = 0; iCol < maxCol; iCol++) {
                Log.d("init FIeld map field map row", String.valueOf(iRow));
                fields[iRow][iCol] = Field.getField(layout[iRow][iCol],
                        buttonSpriteSheet, isCrossed[iRow][iCol]);
            }
        }
    }

    public Field[][] getFields() {
        return fields;
    }

    public Drawable getDrawable(final int rowCord,final int colCord) {
        fields[rowCord][colCord].getDrawableField();
        return drawable;
    }
}
