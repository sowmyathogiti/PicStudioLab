package com.example.picstudiolab;

import android.os.Parcel;
import android.os.Parcelable;

public class Order implements Parcelable {
    private String name;
    private int noOfSelected;
    private double price;

    public Order(String name, int noOfSelected, double price) {
        this.name = name;
        this.noOfSelected = noOfSelected;
        this.price = price;
    }

    protected Order(Parcel in) {
        name = in.readString();
        noOfSelected = in.readInt();
        price = in.readDouble();
    }

    public static final Creator<Order> CREATOR = new Creator<Order>() {
        @Override
        public Order createFromParcel(Parcel in) {
            return new Order(in);
        }

        @Override
        public Order[] newArray(int size) {
            return new Order[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNoOfSelected() {
        return noOfSelected;
    }

    public void setNoOfSelected(int noOfSelected) {
        this.noOfSelected = noOfSelected;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Describe the kinds of special objects contained in this Parcelable
     * instance's marshaled representation. For example, if the object will
     * include a file descriptor in the output of {@link #writeToParcel(Parcel, int)},
     * the return value of this method must include the
     * {@link #CONTENTS_FILE_DESCRIPTOR} bit.
     *
     * @return a bitmask indicating the set of special object types marshaled
     * by this Parcelable object instance.
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Flatten this object in to a Parcel.
     *
     * @param dest  The Parcel in which the object should be written.
     * @param flags Additional flags about how the object should be written.
     *              May be 0 or {@link #PARCELABLE_WRITE_RETURN_VALUE}.
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(name);
        dest.writeInt(noOfSelected);
        dest.writeDouble(price);

    }

    @Override
    public String toString() {
        return "Order{" +
                "name='" + name + '\'' +
                ", noOfSelected=" + noOfSelected +
                ", price=" + price +
                '}';
    }
}
