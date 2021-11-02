package de.techfak.gse.dwenzel.playground;

import android.os.Parcel;
import android.os.Parcelable;


public class Playground implements Parcelable {
    private  Field[][] field;
    private final int maxRow;
    private final int maxCol;

    public Playground(int row, int col) {
        maxCol = col;
        maxRow = row;
        field = new Field[maxRow][maxCol];
        PlaygroundView pView = new PlaygroundView(maxRow, maxCol);
    }
    public int getMaxRow(){
        return maxRow;
    }
    public int getMaxCol(){
        return maxCol;
    }
    public  void addField(int row, int col, Field newField) {
        field[row][col] = newField;
    }

    public Field getField(int row,int col) {
        return field[row][col];
    }

    protected Playground(Parcel in) {
        maxRow = in.readInt();
        maxCol = in.readInt();
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
        dest.writeInt(maxRow);
        dest.writeInt(maxCol);
    }
}
