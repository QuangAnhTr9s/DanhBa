package com.tqa.btdanhba.model;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.io.Serializable;
import java.util.Objects;

public class Contact implements Parcelable {
    //avatar de theo gioi tinh nen ta khai bao thuoc tinh isMale dang boolean
    private boolean isMale;
    private String mName;
    private String mPhoneNumber;

    public Contact(boolean isMale, String mName, String mPhoneNumber) {
        this.isMale = isMale;
        this.mName = mName;
        this.mPhoneNumber = mPhoneNumber;
    }

    protected Contact(Parcel in) {
        isMale = in.readByte() != 0;
        mName = in.readString();
        mPhoneNumber = in.readString();
    }

    public static final Creator<Contact> CREATOR = new Creator<Contact>() {
        @Override
        public Contact createFromParcel(Parcel in) {
            return new Contact(in);
        }

        @Override
        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };

    public boolean isMale() {
        return isMale;
    }

    public void setMale(boolean male) {
        isMale = male;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmPhoneNumber() {
        return mPhoneNumber;
    }

    public void setmPhoneNumber(String mPhoneNumber) {
        this.mPhoneNumber = mPhoneNumber;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeByte((byte) (isMale ? 1 : 0));
        dest.writeInt(isMale ? 1 : 0);
        dest.writeString(mName);
        dest.writeString(mPhoneNumber);
    }
}
