package de.techfak.gse.dwenzel.playground;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.File;
import java.io.InputStream;


public class Playground implements Parcelable {
    private Field[][] field;


    public Playground(InputStream boardFile) {
        ReadBoardLayout readBoardLayout = new ReadBoardLayout(boardFile);
        field = readBoardLayout.getFields();

    }

    public String getFieldColor(int row, int col) {
        return field[row][col].getFieldColor();
    }

    protected Playground(Parcel in) {
    }

    public static final Creator<Playground> CREATOR = new Creator<Playground>() {
        @Override
        public Playground createFromParcel(Parcel in) {
            return new Playground(in);
        }

        @Override
        public Playground[] newArray(int size) {
            return new Playground[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

}
