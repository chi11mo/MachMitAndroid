package de.techfak.gse.dwenzel.game_screen.controller;

import de.techfak.gse.dwenzel.game_screen.model.Field;
import de.techfak.gse.dwenzel.start_screen.model.BoardFile;


/**
 * Read input string from start page to validate or save Board.
 */
public class BoardReader {

    /* field array for playground description.*/        private final Field[][] field;

    /*Playground als Array.*/                           private final String[][] pgGrid;

    /*Board file with file and maxRow and Col range.*/  private final BoardFile board;

    /**
     * Creates class for BoardFile.
     *
     * @param board board values as a class.
     */
    public BoardReader(final BoardFile board) {
        this.board = board;

        field = new Field[board.getMaxRow()][board.getMaxCol()];
        pgGrid = new String[board.getMaxRow()][board.getMaxCol()];

        readBoard();
        fieldInit();
    }

    /**
     * Init the fields.
     */
    private void fieldInit() {
        // let's loop through array to populate board
        for (int row = 0; row < pgGrid.length; row++) {
            for (int col = 0; col < pgGrid[row].length; col++) {

                final char letter = pgGrid[row][col].charAt(0);

                field[row][col] = new Field(getColorString(pgGrid[row][col]), Character.isUpperCase(letter));

            }
        }
    }

    /**
     * get write string for color.
     *
     * @param color single letter color.
     * @return right string.
     */
    private String getColorString(final String color) {
        if (color.equals("y") || color.equals("Y")) {
            return "Yellow";
        }
        if (color.equals("r") || color.equals("R")) {
            return "Red";
        }
        if (color.equals("o") || color.equals("O")) {
            return "Orange";
        }

        if (color.equals("b") || color.equals("B")) {
            return "Blue";
        }
        if (color.equals("g") || color.equals("G")) {
            return "Green";
        }
        return null;
    }


    /**
     * Checks validation of right data. For the game Playground.
     */
    private void readBoard() {
        final String boardString = board.getBoardString();
        int colCounter = 0;


        final String[] spliced = boardString.split(",");

        for (final String rowString : spliced) {

            for (int rowCounter = 0; rowCounter < rowString.length(); rowCounter++) {


                pgGrid[rowCounter][colCounter] = String.valueOf(rowString.charAt(rowCounter));


            }
            colCounter++;

        }


    }

    /**
     * to get the saved fields.
     *
     * @return saved fields from data.
     */
    public Field[][] getFields() {
        return field;
    }
}
