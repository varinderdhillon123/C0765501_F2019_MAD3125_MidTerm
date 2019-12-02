package com.example.c0765501_f2019_mad3125_midterm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class ResultActivity extends AppCompatActivity {
    CRACustomer cra;
    TextView sin, First, txtgender, Total,
            filingdate, Fed_tax, Prov_tax, calc_Cpp,
            calc_empIns, calc_RRSP, calc_RRSP_fwd,
            calc_TotaltaxInc, calc_totalpay;
    double CPP = 0, EI = 0;  double RRSP = 0, RRSPCarry_FWD = 0, Tot_taxInc, fed_Tax,
            prov_Tax, Tot_Tax;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        sin = findViewById(R.id.sin_no);
        First = findViewById(R.id.txtFullName);
        txtgender =   findViewById(R.id.txtGender);
        Total = findViewById(R.id.txttotal_inc);
        calc_RRSP = findViewById(R.id.editRRSPContrI);
        calc_Cpp = findViewById(R.id.calCPP);
        calc_empIns = findViewById(R.id.calEmp_Ins);
        calc_RRSP_fwd = findViewById(R.id.calCarryForwardRRSP);
        calc_TotaltaxInc = findViewById(R.id.calTotalTaxableIncome);
        Fed_tax = findViewById(R.id.FederalTax);
        Prov_tax = findViewById(R.id.calProvincialTax);
        calc_totalpay = findViewById(R.id.calTotalTaxPayed);

        //Back Button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //collecting intent
        Intent mIntent = getIntent();
        CRACustomer customer = mIntent.getParcelableExtra("CRACustomer");

        sin.setText(" SIN: \t" + customer.getSinNumber());
        First.setText(" FULL NAME: \t" + customer.getFullName());
        txtgender.setText(" GENDER: \t" + customer.getGender());
        Total.setText(" GROSS INCOME: \t" + customer.getGrossIncome());
        calc_RRSP.setText("RRSP Contributed: \t" + customer.getRrspContri());

        // calculate  cpp
        if(customer.getGrossIncome() > 57000.00){
            CPP = (57000.00 * (5.10 / 100));
        } else {
            CPP = (customer.getGrossIncome() * (5.10 / 100));
        }
        calc_Cpp.setText("CPP COntribution in Year:\t" + CPP);
        // calculate employement insurance
        if(customer.getGrossIncome() > 53100){
            EI = (53100 * (1.62 / 100));
        }else{
            EI = (customer.getGrossIncome() * (1.62/100));
        }
        calc_empIns.setText("Employeement Insurance: \t" + EI);
        // calculate RRSP
        RRSP = customer.getRrspContri();
        double maxRRSP = (customer.getGrossIncome() * (18 /100));
        if(customer.getRrspContri() > maxRRSP ){
            RRSPCarry_FWD = RRSP - maxRRSP;
        }else{
            RRSP = maxRRSP;
        }
        calc_RRSP_fwd.setText("RRSP Carry forward: \t"+ RRSPCarry_FWD);
        //taxable income
        Tot_taxInc = (CPP - EI - RRSP);
        //Toast.makeText(this, "(Double)taxableIncome" + taxableIncome, Toast.LENGTH_SHORT).show();
        calc_TotaltaxInc.setText("Taxable income:\t" + (double) Tot_taxInc);
        //federal tax
        double calFederal = calcFedralTax();
        Fed_tax.setText("Federal Tax: \t" + calFederal);
        // Provincial Tax
        double calProvincial = calcProvincialTax();
        Prov_tax.setText("Provincial Tax:\t" + calProvincial);
        // total tax paid
        double taxpaid = calTaxPaid();
        calc_totalpay.setText("Total tax Paid:\t" + taxpaid);




    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
    public double calcCpp(){
        // calculate  cpp
        if(cra.getGrossIncome() > 57000.00){
            CPP = (57000.00 * (5.10 / 100));
        } else {
            CPP = (cra.getGrossIncome() * (5.10 / 100));
        }
        return CPP;
    }
    public double calcFedralTax(){
        //calculate federal tax

        if(Tot_taxInc < 12069.00){
            fed_Tax = 0;

        }else{
            fed_Tax = Tot_taxInc - 1;
        }
        return fed_Tax;
    }
    public  double calcProvincialTax(){
        //calculate provincial tax
        return prov_Tax;
    }
    public  double calTaxPaid(){
        return Tot_Tax;
    }



}
