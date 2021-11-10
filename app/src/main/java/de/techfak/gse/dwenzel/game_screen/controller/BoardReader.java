package de.techfak.gse.dwenzel.game_screen.controller;

import de.techfak.gse.dwenzel.game_screen.model.Field;
import de.techfak.gse.dwenzel.start_screen.model.BoardFile;

public class BoardReader {

    /* field array for playground description.*/        private final Field[][] field;

    /*Playground als Array.*/                           private final String[][] pgGrid;

    /*Board file with file and maxRow and Col range.*/  private final BoardFile board;

    /**
     * Creates class for BoardFile.
     * @param board board values as a class.
     */
    public BoardReader(BoardFile board) {
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

                char letter = pgGrid[row][col].charAt(0);

                field[row][col] = new Field(pgGrid[row][col], Character.isUpperCase(letter));

            }
        }
    }


    /**
     * Checks validation of right data. For the game Playground.
     *
     * @return exception of valid or invalid data.
     */
    public String readBoard() {
        String boardString = board.getBoardString();
        int colCounter = 0;


        String[] spliced = boardString.split(",");

        for (String rowString : spliced) {

            for (int rowCounter = 0; rowCounter < rowString.length(); rowCounter++) {


                pgGrid[rowCounter][colCounter] = String.valueOf(rowString.charAt(rowCounter));


            }
            colCounter++;

        }


        return "Valid";
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
