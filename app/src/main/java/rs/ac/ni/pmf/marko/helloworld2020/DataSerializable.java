package rs.ac.ni.pmf.marko.helloworld2020;

import java.io.Serializable;

public class DataSerializable implements Serializable {

    private int x;
    private String data;

    public DataSerializable(int x, String data) {
        this.x = x;
        this.data = data;
    }

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
        return "DataSerializable{" +
                "x=" + x +
                ", data='" + data + '\'' +
                '}';
    }
}
