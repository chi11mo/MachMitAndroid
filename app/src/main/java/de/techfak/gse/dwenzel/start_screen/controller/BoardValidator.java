package de.techfak.gse.dwenzel.start_screen.controller;

import android.util.Log;

import de.techfak.gse.dwenzel.exception.InvalidBoardLayoutException;
import de.techfak.gse.dwenzel.exception.InvalidFieldException;
import de.techfak.gse.dwenzel.start_screen.model.BoardFile;

/**
 * check if data has a Valid board.
 */
public class BoardValidator {

    /* field Exception message.*/           private final static String fieldException = "InvalidFieldException";

    /*board Exception message.*/            private final static String boardException = "InvalidBoardException";

    /*BoardFile to check validation.*/      private final BoardFile boardFile;

    /**
     * BoardValidator checck if board accept the board Rules.
     * @param boardFile baordfile to validate board value.
     */
    public BoardValidator(BoardFile boardFile) {
        this.boardFile = boardFile;

    }


    /**
     * Checks validation of right data. For the game Playground.
     *
     * @return exception of valid or invalid data.
     */
    public String isValidBoard() {
        String boardString = boardFile.getBoardString();
        String exception = null;
        int rowCounter;

        String[] spliced = boardString.split(",");

        try {
            if (spliced.length != boardFile.getMaxCol()) {
                exception = boardException;
                throw new InvalidBoardLayoutException(
                        boardException);
            }
            for (String rowString : spliced) {
                rowCounter = 0;
                for (int i = 0; i < rowString.length(); i++) {
                    String letter = String.valueOf(rowString.charAt(i));


                    if (!letter.matches(".*([bBgGoOrRyY]).*")) {
                        exception = fieldException;
                        throw new InvalidFieldException(
                                fieldException);
                    }
                    if (Character.isLetter(rowString.charAt(i))) {
                        rowCounter++;
                    }


                }

                if (rowCounter != boardFile.getMaxRow()) {
                    exception = boardException;
                    throw new InvalidBoardLayoutException(
                            boardException);
                }


            }


        } catch (Exception e) {
            Log.d("Exception", String.valueOf(e));
            return exception;
        }
        return "Valid";
    }
}
