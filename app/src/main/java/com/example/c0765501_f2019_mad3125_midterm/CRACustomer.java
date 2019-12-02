package com.example.c0765501_f2019_mad3125_midterm;

import android.os.Parcel;
import android.os.Parcelable;

public class CRACustomer implements Parcelable
{


    private String sin_no,age;
    String First,Last;
    String FullName;
    String Gender;
    String Total;


    public String getGender()
    {
        return Gender;
    }

    public void setGender(String gender)
    {
        this.Gender = Gender;
    }

    public CRACustomer(String sin_no, String age, String first,String last, String gender, String Total)
    {
        this.sin_no = sin_no;
        this.age = age;
        this.Total = Total;
        this.First = first;
        this.Last = last;
        this.Gender = gender;


    }

    public static final Creator<CRACustomer> CREATOR = new Creator<CRACustomer>() {
        @Override
        public CRACustomer createFromParcel(Parcel in) {
            return new CRACustomer(in);
        }

        @Override
        public CRACustomer[] newArray(int size) {
            return new CRACustomer[size];
        }
    };

    public String getLast()
    {
        return Last;

    }

    public void setLast(String Last)
    {
        this.Last = Last;
    }



    public String getSin_no()
    {
        return sin_no;
    }

    public void setSin_no(String sin_no)
    {
        this.sin_no = sin_no;
    }

    public String getAge()
    {
        return age;
    }

    public void setAge(String age)
    {
        this.age = age;
    }

    public String getTotal()
     {
        return Total;
    }

    public void setTotal(String Total)
    {
        this.Total = Total;
    }

    public String getFullName()
    {
        return Last.toUpperCase() + " " + First;
    }

    public void setFullName(String Fullname)
    {
        this.FullName = Fullname;
    }

    public String getFirst()
    {
        return First;
    }

    public void setFirst(String First)
    {
        this.First = First;
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(First);
        dest.writeString(Last);
        dest.writeString(FullName);
        dest.writeString(sin_no);
        dest.writeString(age);
        dest.writeString(Total);

        dest.writeString(Gender);


    }

    public CRACustomer(Parcel parcel)
    {
        First = parcel.readString();
        Last = parcel.readString();
        FullName = parcel.readString();
        sin_no = parcel.readString();
        age = parcel.readString();
        Total = parcel.readString();
        Gender = parcel.readString();

    }
}
