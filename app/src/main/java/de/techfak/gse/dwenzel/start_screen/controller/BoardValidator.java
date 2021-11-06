package de.techfak.gse.dwenzel.start_screen.controller;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import de.techfak.gse.dwenzel.exception.InvalidBoardLayoutException;
import de.techfak.gse.dwenzel.exception.InvalidFieldException;
import de.techfak.gse.dwenzel.start_screen.model.IBoardFile;

/**
 * check if data has a Valid board.
 */
public class BoardValidator implements IBoardValidator {
    /**
     * field Exception message.
     */
    private final static String fieldException = "InvalidFieldException";
    /**
     * board Exception message.
     */
    private final static String boardException = "InvalidBoardException";


    /**
     * BoardFile to check validation.
     */
    private final IBoardFile iBoardFile;

    public BoardValidator(IBoardFile boardFileInterface) {
        this.iBoardFile = boardFileInterface;

    }



    /**
     * Checks validation of right data. For the game Playground.
     *
     * @return exception of valid or invalid data.
     */
    public String isValidBoard() {
        String boardString = iBoardFile.getBoardString();
        String exception = null;
        int rowCounter = 0;

        String[] splited = boardString.split(",");

        try {
            if (splited.length != iBoardFile.getMaxCol()) {
                exception = boardException;
                throw new InvalidBoardLayoutException(
                        boardException);
            }
            for (String rowString : splited) {
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

                if (rowCounter != iBoardFile.getMaxRow()) {
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
