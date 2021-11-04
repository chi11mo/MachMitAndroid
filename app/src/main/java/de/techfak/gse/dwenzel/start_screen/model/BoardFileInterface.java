package de.techfak.gse.dwenzel.start_screen.model;

import java.io.InputStream;

public interface BoardFileInterface {
    InputStream getBoardFile();
    int getMaxRow();
    int getMaxCol();
}
