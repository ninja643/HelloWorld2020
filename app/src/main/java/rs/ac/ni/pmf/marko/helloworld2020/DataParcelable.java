package rs.ac.ni.pmf.marko.helloworld2020;

import android.os.Parcel;
import android.os.Parcelable;

public class DataParcelable implements Parcelable {
    private int x;
    private String data;

    public DataParcelable(int x, String data) {
        this.x = x;
        this.data = data;
    }

    protected DataParcelable(Parcel in) {
        x = in.readInt();
        data = in.readString();
    }

    public static final Creator<DataParcelable> CREATOR = new Creator<DataParcelable>() {
        @Override
        public DataParcelable createFromParcel(Parcel in) {
            return new DataParcelable(in);
        }

        @Override
        public DataParcelable[] newArray(int size) {
            return new DataParcelable[size];
        }
    };

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "DataParcelable{" +
                "x=" + x +
                ", data='" + data + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(x);
        dest.writeString(data);
    }
}
