package com.example.purchaselist.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class MyList implements Parcelable {
    private int id;
    private String name;
    private int date;
    private String description;

    public MyList(int id, String name, int date, String description) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.description = description;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int flags) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeInt(date);
        parcel.writeString(description);
    }

    // CREATOR для воссоздания объекта из Parcel
    public static final Parcelable.Creator<MyList> CREATOR = new Parcelable.Creator<MyList>() {
        @Override
        public MyList createFromParcel(Parcel parcel) {
            int id = parcel.readInt();
            String name = parcel.readString();
            int date = parcel.readInt();
            String description = parcel.readString();
            return new MyList(id, name, date, description);
        }

        @Override
        public MyList[] newArray(int size) {
            return new MyList[size];
        }
    };


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "MyList{ id=" + id + ", name=" + name + ", date=" + date + ", description=" + description + " }";
    }
}
