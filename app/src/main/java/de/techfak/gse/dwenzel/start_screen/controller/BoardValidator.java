package de.techfak.gse.dwenzel.start_screen.controller;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import de.techfak.gse.dwenzel.exception.InvalidBoardLayoutException;
import de.techfak.gse.dwenzel.exception.InvalidFieldException;
import de.techfak.gse.dwenzel.start_screen.model.BoardFileInterface;

/**
 * check if data has a Valid board.
 */
public class BoardValidator implements BoardValidatorInterface {
    /**
     * field Exception message.
     */
    private final static String fieldException = "InvalidFieldException";
    /**
     * board Exception message.
     */
    private final static String boardException = "InvalidBoardException";
    /**
     * Playground als Array.
     */
    private static String[][] pgGrid;

    private BoardFileInterface boardFileInterface;

    public BoardValidator(BoardFileInterface boardFileInterface) {
        this.boardFileInterface = boardFileInterface;

    }


    /**
     * Checks validation of right data. For the game Playground.
     *
     * @param boardFileInterface file of data.
     * @return exception of valid or invalid data.
     */
    @Override
    public String isValidBoardFile() {
        pgGrid = new String[boardFileInterface.getMaxRow()][boardFileInterface.getMaxCol()];
        InputStream boardFileIS = boardFileInterface.getBoardFile();
        String exception = null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(boardFileIS));

        int rowCounter;
        int colCounter = 0;
        try {

            String line = reader.readLine();

            while (line != null) {
                rowCounter = 0;
                for (int i = 0; i < line.length(); i++) {
                    String letter = String.valueOf(line.charAt(i));


                    if (!letter.matches(".*([bBgGoOrRyY]).*")) {
                        exception = fieldException;
                        throw new InvalidFieldException(
                                fieldException);
                    }
                    if (Character.isLetter(line.charAt(i))) {
                        rowCounter++;
                    }

                    //save Fields in 2d array pgGrid.
                    pgGrid[i][colCounter] = String.valueOf(line.charAt(i));


                }

                if (rowCounter != boardFileInterface.getMaxRow()) {
                    exception = boardException;
                    throw new InvalidBoardLayoutException(
                            boardException);
                }


                line = reader.readLine();
                colCounter++;
            }
            if (colCounter != boardFileInterface.getMaxCol()) {
                exception = boardException;
                throw new InvalidBoardLayoutException(
                        boardException);
            }

        } catch (Exception e) {
            Log.d("Exception", String.valueOf(e));
            return exception;
        } finally {
            try {
                reader.close(); //Here it says unreported exception IOException; must be caught or declared to be thrown
            } catch (Exception exp) {
                Log.d("Exception", String.valueOf(exp));
            }
        }
        return "Valid";
    }
}
