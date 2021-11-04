package de.techfak.gse.dwenzel.start_screen.model;

import java.io.InputStream;

public class BoardFile implements BoardFileInterface {
   private InputStream boardFile;
   private int maxRow;
   private int maxCol;

    public BoardFile(InputStream boardFile, int maxRow, int maxCol) {
        this.boardFile = boardFile;
        this.maxRow = maxRow;
        this.maxCol = maxCol;
    }

    public InputStream getBoardFile() {
        return boardFile;
    }


    public int getMaxRow() {
        return maxRow;
    }


    public int getMaxCol() {
        return maxCol;
    }
}
