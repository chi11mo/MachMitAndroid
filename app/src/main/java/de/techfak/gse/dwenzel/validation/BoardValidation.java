package de.techfak.gse.dwenzel.validation;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import de.techfak.gse.dwenzel.exception.InvalidBoardLayoutException;
import de.techfak.gse.dwenzel.exception.InvalidFieldException;

public class BoardValidation {

    /**
     * Playground als Array.
     */
    private static String[][] pgGrid;
    /**
     * Board layout file.
     */
    private static InputStream boardFile;


    public static String fileValidation(InputStream file, int playgroundRowSize, int playgroundColSize) {
        pgGrid = new String[playgroundRowSize][playgroundColSize];
        boardFile = file;
        String exception = null;
        BufferedReader reader;
        int rowCounter;
        int colCounter = 0;
        try {

            reader = new BufferedReader(new InputStreamReader(boardFile));
            String line = reader.readLine();

            while (line != null) {
                rowCounter = 0;
                for (int i = 0; i < line.length(); i++) {
                    String letter = String.valueOf(line.charAt(i));


                    if (!letter.matches(".*([bBgGoOrRyY]).*")) {
                        exception = "InvalidFieldException";
                        throw new InvalidFieldException(
                                "InvalidFieldException");
                    }
                    if (Character.isLetter(line.charAt(i)))
                        rowCounter++;

                    //save Fields in 2d array pgGrid.
                    pgGrid[i][colCounter] = String.valueOf(line.charAt(i));


                }

                if (rowCounter != playgroundRowSize) {
                    exception = "InvalidBoardLayout";
                    throw new InvalidBoardLayoutException(
                            "InvalidBoardLayout");
                }


                line = reader.readLine();
                colCounter++;
            }
            if (colCounter != playgroundColSize) {
                exception = "InvalidBoardLayout";
                throw new InvalidBoardLayoutException(
                        "InvalidBoardLayout");
            }

        } catch (Exception e) {
            e.printStackTrace();
            // exit(EXITCODE);
            return exception;
        }
        return "Valid";
    }

}
