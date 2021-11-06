package de.techfak.gse.dwenzel.start_screen.model;

import java.io.InputStream;

public class BoardFile implements IBoardFile {
   private final String boardString;
   private final int maxRow;
   private final int maxCol;


    public BoardFile(String boardString, final int maxRow,final int maxCol) {
        this.boardString = boardString;
        this.maxRow = maxRow;
        this.maxCol = maxCol;
    }



    public int getMaxRow() {
        return maxRow;
    }


    public int getMaxCol() {
        return maxCol;
    }

    @Override
    public String getBoardString() {
        return boardString;
    }
}
