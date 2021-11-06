package de.techfak.gse.dwenzel.start_screen.model;

import java.io.InputStream;

public interface IBoardFile {
    int getMaxRow();
    int getMaxCol();
    String getBoardString();
}
