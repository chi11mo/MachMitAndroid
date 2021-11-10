package de.techfak.gse.dwenzel.start_screen.model;

/**
 * Creates a class to save all the board Information.
 */
public class BoardFile {
    /*Board fields as a string*/    private final String boardString;
    /*Max row size board*/          private final int maxRow;
    /*Max column size board*/       private final int maxCol;

    /**
     * creates a Board file .
     *
     * @param boardString board as a string.
     * @param maxRow      max row size of the Board.
     * @param maxCol      max column size of the Board.
     */
    public BoardFile(final String boardString, final int maxRow, final int maxCol) {
        this.boardString = boardString;
        this.maxRow = maxRow;
        this.maxCol = maxCol;
    }

    /**
     * getting the Max Row of the playground.
     * @return get Max Row size of the board.
     */
    public int getMaxRow() {
        return maxRow;
    }

    /**
     *  getting the Max Column of the playground.
     * @return get Max Columnsize of the board.
     */
    public int getMaxCol() {
        return maxCol;
    }

    /**
     * get string of board  values.
     * @return get string of board  values.
     */
    public String getBoardString() {
        return boardString;
    }
}
