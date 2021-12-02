package de.techfak.gse.dwenzel.start_screen.controller;

import android.util.Log;

import de.techfak.gse.dwenzel.exception.InvalidBoardLayoutException;
import de.techfak.gse.dwenzel.exception.InvalidFieldException;
import de.techfak.gse.dwenzel.start_screen.model.BoardFile;

/**
 * check if data has a Valid board.
 */
public class BoardValidator {
    /*Exception title*/ private static final String EXCEPTIONTITLE = "Exception";
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


        final String[] spliced = boardString.split("\\n");
        String exception;
        exception = boardValidator(spliced);
        if (exception != null) {
            return exception;
        }
        exception = fieldValidator(spliced);
        if (exception != null) {
            return exception;
        }
        return "Valid";


    }

    /**
     * Checing all field are valid.
     *
     * @param spliced fields.
     * @return exception if field isn't valid.
     */
    private String fieldValidator(final String... spliced) {
        String exception = null;
        try {

            for (final String rowString : spliced) {
                for (int i = 0; i < rowString.length(); i++) {
                    final String letter = String.valueOf(rowString.charAt(i));


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
            Log.d(EXCEPTIONTITLE, String.valueOf(e));
            return exception;
        }
        return null;
    }

    /**
     * checkin board size is valid.
     *
     * @param spliced board as a string.
     * @return exception if it isn't valid.
     */
    private String boardValidator(final String... spliced) {
        String exception = null;
        /*board Exception message.*/
        final String boardException = "InvalidBoardException";
        try {
            for (final String rowString : spliced) {
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
            Log.d(EXCEPTIONTITLE, String.valueOf(exp));
            return exception;
        }
        return null;
    }
}
