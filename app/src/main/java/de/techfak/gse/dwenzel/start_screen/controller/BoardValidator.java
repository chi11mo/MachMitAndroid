package de.techfak.gse.dwenzel.start_screen.controller;

import android.util.Log;

import de.techfak.gse.dwenzel.exception.InvalidBoardLayoutException;
import de.techfak.gse.dwenzel.exception.InvalidFieldException;
import de.techfak.gse.dwenzel.start_screen.model.BoardFile;

/**
 * check if data has a Valid board.
 */
public class BoardValidator {

    /*BoardFile to check validation.*/      private final BoardFile boardFile;

    /**
     * BoardValidator check if board accept the board Rules.
     *
     * @param boardFile board file to validate board value.
     */
    public BoardValidator(final BoardFile boardFile) {
        this.boardFile = boardFile;

    }


    /**
     * Checks validation of right data. For the game Playground.
     *
     * @return exception of valid or invalid data.
     */
    public String boardValidation() {
        final String boardString = boardFile.getBoardString();
        String exception = null;

        final String[] spliced = boardString.split(",");

        /*board Exception message.*/
        final String boardException = "InvalidBoardException";
        try {
            for (String rowString : spliced) {
                if (rowString.length() != boardFile.getMaxRow()) {
                    exception = boardException;
                    throw new InvalidBoardLayoutException(
                            boardException);
                }
            }
            if (spliced.length != boardFile.getMaxCol()) {
                exception = boardException;

                throw new InvalidBoardLayoutException(
                        boardException);
            }
        } catch (InvalidBoardLayoutException exp) {
            Log.d("Exception", String.valueOf(exp));
            return exception;
        }
        try {

            for (String rowString : spliced) {
                for (int i = 0; i < rowString.length(); i++) {
                    String letter = String.valueOf(rowString.charAt(i));


                    if (!letter.matches(".*([bBgGoOrRyY]).*")) {
                        /* field Exception message.*/
                        final String fieldException = "InvalidFieldException";
                        exception = fieldException;
                        throw new InvalidFieldException(
                                fieldException);
                    }


                }


            }


        } catch (InvalidFieldException e) {
            Log.d("Exception", String.valueOf(e));
            return exception;
        }
        return "Valid";
    }
}
