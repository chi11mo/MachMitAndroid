package de.techfak.gse.dwenzel.game_screen.map;

public class MapLayout {
    public final int maxRow;
    public final int maxCol;
    private int[][] layout;
    private boolean[][] isCrossed;
    private String boardLayout;

    public MapLayout(String boardLayout, int maxRow, int maxCol) {
        this.maxRow = maxRow;
        this.maxCol = maxCol;
        this.boardLayout = boardLayout;
        initializeLayout();
    }

    private void initializeLayout() {
        layout = new int[maxRow][maxCol];
        isCrossed = new boolean[maxRow][maxCol];
        final String boardString = boardLayout;


        final String[] spliced = boardString.split(",");

        for (int iRow = 0; iRow < spliced[0].length(); iRow++) {

            for (int iCol = 0; iCol < spliced.length; iCol++) {

                Character letter = spliced[iCol].charAt(iRow);
                layout[iRow][iCol] =
                        getColorAsIndex(String.valueOf(letter));
                isCrossed[iRow][iCol] = Character.isUpperCase(letter);


            }


        }


    }
    /**
     * Images.
     * Yellow index 0;
     * Green index 1
     * Red index 2
     * Orange index 3
     * Blue index 4
     */
    /**
     * get write string for color.
     *
     * @param color single letter color.
     * @return right string.
     */
    private int getColorAsIndex(final String color) {
        int index = 0;
        if (color.equals("y") || color.equals("Y")) return index;
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

    public int[][] getLayout() {
        return layout;
    }

    public boolean[][] getIsCrossed() {
        return isCrossed;
    }
}
