package de.techfak.gse.dwenzel.board;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.InputStream;

public class ParcelableClass implements Parcelable {
    public static InputStream minputStream;
    public ParcelableClass() {
    }
    public ParcelableClass(Parcel in) {
    }

    public static final Creator<ParcelableClass> CREATOR = new Creator<ParcelableClass>() {
        @Override
        public ParcelableClass createFromParcel(Parcel in) {
            return new ParcelableClass(in);
        }

        @Override
        public ParcelableClass[] newArray(int size) {
            return new ParcelableClass[size];
        }
    };

    public InputStream getMinputStream()
    {
        return minputStream;
    }

    public void setInPutStream(InputStream file) {
        minputStream=file;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
