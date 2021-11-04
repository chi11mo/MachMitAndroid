package de.techfak.gse.dwenzel.game_screen.model;

import android.widget.Button;

public interface IPlaygroundModel {
    void setFieldButton(Button button, int row, int col);
    Button getFieldButton(int row, int col);
    String getFieldColor(int row, int col);
    boolean isFieldCrossed(int row,int col);
}
