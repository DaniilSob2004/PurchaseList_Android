package com.example.purchaselist.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Product implements Parcelable {
    private int id;
    private String name;
    private float count;
    private int idList;
    private int checked;
    private int idType;

    public Product(int id, String name, float count, int idList, int checked, int idType) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.idList = idList;
        this.checked = checked;
        this.idType = idType;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int flags) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeFloat(count);
        parcel.writeInt(idList);
        parcel.writeInt(checked);
        parcel.writeInt(idType);
    }

    // CREATOR для воссоздания объекта из Parcel
    public static final Parcelable.Creator<Product> CREATOR = new Parcelable.Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel parcel) {
            int id = parcel.readInt();
            String name = parcel.readString();
            float count = parcel.readFloat();
            int idList = parcel.readInt();
            int checked = parcel.readInt();
            int idType = parcel.readInt();
            return new Product(id, name, count, idList, checked, idType);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getCount() {
        return count;
    }

    public int getIdList() {
        return idList;
    }

    public int getChecked() {
        return checked;
    }

    public int getIdType() {
        return idType;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCount(float count) {
        this.count = count;
    }

    public void setIdList(int idList) {
        this.idList = idList;
    }

    public void setChecked(int checked) {
        this.checked = checked;
    }

    public void setIdType(int idType) {
        this.idType = idType;
    }

    @Override
    public String toString() {
        return "Product{ id=" + id + ", name=" + name + ", count=" + count + ", idList=" + idList + ", checked=" + checked + ", idType=" + idType + " }";
    }
}
