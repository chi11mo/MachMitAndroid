package de.techfak.gse.dwenzel.validation;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import de.techfak.gse.dwenzel.exception.InvalidBoardLayoutException;
import de.techfak.gse.dwenzel.exception.InvalidFieldException;

/**
 * check if data has a Valid board.
 */
public class BoardValidation {
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
    /**
     * Board layout file.
     */
    private static InputStream boardFile;

    public BoardValidation() {

    }

    /**
     * Checks validation of right data. For the game Playground.
     *
     * @param file              file of data.
     * @param playgroundRowSize row size.
     * @param playgroundColSize column size.
     * @return exception of valid or invalid data.
     */
    public static String fileValidation(InputStream file, int playgroundRowSize, int playgroundColSize) {
        pgGrid = new String[playgroundRowSize][playgroundColSize];
        boardFile = file;
        String exception = null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(boardFile));

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

                if (rowCounter != playgroundRowSize) {
                    exception = boardException;
                    throw new InvalidBoardLayoutException(
                            boardException);
                }


                line = reader.readLine();
                colCounter++;
            }
            if (colCounter != playgroundColSize) {
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
