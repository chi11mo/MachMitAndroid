package de.techfak.gse.dwenzel.game_screen.model;

import android.util.Log;

public class MapLayout {
    private final int maxRow;
    private final int maxCol;
    private final String boardLayout;
    private int[][] layout;
    private boolean[][] isCrossed;


    /**
     * Map layout loads map layout with the max Row and column cord.
     * and takes GameStart input and loaded into a grid layout in Fields
     *
     * @param boardLayout string input from game start to create the map.
     * @param maxRow      max Row cord.
     * @param maxCol      max Column cord.
     */
    public MapLayout(final String boardLayout, final int maxRow, final int maxCol) {
        this.maxRow = maxRow;
        this.maxCol = maxCol;
        this.boardLayout = boardLayout;
        initializeLayout();
    }

    /**
     * init the layout.
     */
    private void initializeLayout() {
        layout = new int[maxRow][maxCol];
        isCrossed = new boolean[maxRow][maxCol];
Log.d("String layout",boardLayout);

        final String[] spliced = boardLayout.split("\\n");

        for (int iRow = 0; iRow < spliced[0].length(); iRow++) {

            for (int iCol = 0; iCol < spliced.length; iCol++) {

                final Character letter = spliced[iCol].charAt(iRow);
                layout[iRow][iCol] =
                        getColorAsIndex(String.valueOf(letter));
                isCrossed[iRow][iCol] = Character.isUpperCase(letter);


            }


        }


    }

    /**
     * get write string for color.
     * Images.
     * Yellow index 0;
     * Green index 1
     * Red index 2
     * Orange index 3
     * Blue index 4¬
     *
     * @param color single letter color.
     * @return right string.
     */
    private int getColorAsIndex(final String color) {
        int index = 0;
        if (color.equals("y") || color.equals("Y")) {
            return index;
        }
        index++;
        if (color.equals("g") || color.equals("G")) {
            return index;
        }
        index++;

        if (color.equals("r") || color.equals("R")) {
            return index;
        }
        index++;
        if (color.equals("o") || color.equals("O")) {
            return index;
        }
        index++;
        return index;
    }

    /**
     * getting the map layout.
     *
     * @return map layout.
     */
    public int[][] getLayout() {
        return layout;
    }

    /**
     * is field crossed.
     *
     * @return field cross.
     */
    public boolean[][] getIsCrossed() {
        return isCrossed;
    }
}
