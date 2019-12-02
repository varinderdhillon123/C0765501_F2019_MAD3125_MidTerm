package com.example.c0765501_f2019_mad3125_midterm;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.Editable;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;

public class CRACustomer implements Parcelable {

    String sno;
    String fName;
    String lName;
    String Fullnm;
    String Gender;
    Date dob;
    Date FilingDate;
    int Age;
    double Total;
    double Fedtax;
    double Provtax;
    double EmpIns;
    double RRSPcont;
    double RRSPFwd;
    double taxable_inc;
    double paid_tax;

    public CRACustomer(String sno, String fName,
                       String lName, String Gender, double Total, double RRSPcont) {
        this.sno = sno;
        this.fName = fName;
        this.lName = lName;
        this.Fullnm = Fullnm ;
        this.Gender = Gender;
        this.Total = Total;
        this.RRSPcont = RRSPcont;
    }


    public String getSinNumber() {
        return sno;
    }

    public String getFirstName()
    {
        return fName;
    }

    public String getLastName() {
        return lName;
    }

    public String getFullName() {

        return lName.toUpperCase() + ", " +
                fName.substring(0, 1).toUpperCase() + fName.substring(1);
    }

    public String getGender() {
        return Gender;
    }

    public Date getBirthDate() {
        return dob;
    }

    public int getAge() {
        return Age;
    }

    public Date getFilingDate() {
        return FilingDate;
    }

    public double getGrossIncome() {
        return Total;
    }

    public double getEmpInsurance() {
        return EmpIns;
    }

    public double getRrspContri() {
        return RRSPcont;
    }

    public double getRrspCarryForward() {
        return RRSPFwd;
    }

    public double getTaxableIncome() {
        return taxable_inc;
    }

    public double getTaxPaid() {
        return paid_tax;
    }

    public double getFederalTax() {
        return Fedtax;
    }

    public double getProvicialTax() {
        return Provtax;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(sno);
        dest.writeString(fName);
        dest.writeString(lName);
        dest.writeString(Fullnm);
        dest.writeString(Gender);
        dest.writeDouble(Total);
        dest.writeDouble(RRSPcont);

    }

    public CRACustomer(Parcel parcel) {
        sno = parcel.readString();
        fName = parcel.readString();
        lName = parcel.readString();
        Fullnm = parcel.readString();
        Gender = parcel.readString();
        Total = parcel.readDouble();
        RRSPcont = parcel.readDouble();

    }


    public static final Parcelable.Creator<CRACustomer> CREATOR = new Creator<CRACustomer>() {
        @Override
        public CRACustomer createFromParcel(Parcel parcel) {
            return new CRACustomer(parcel);
        }

        @Override
        public CRACustomer[] newArray(int size) {
            return new CRACustomer[size];
        }
    };
}

