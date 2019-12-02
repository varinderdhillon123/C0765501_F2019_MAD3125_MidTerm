package com.example.c0765501_f2019_mad3125_midterm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    CRACustomer cust;
    TextView sin;
    TextView fullName;
    TextView gender;
    TextView calc_total;
    TextView calc_taxFilingDate;
    TextView calc_federalTax;
    TextView calc_provincialTax;
    TextView calc_cpp;
    TextView calc_EmpIns;
    TextView calc_RRSP;
    TextView calc_CfRRSP;
    TextView calc_TaxableIncome;
    TextView calc_TaxPaid;
    double CPP = 0, ei = 0;
    double RRSP = 0, RRSPCRY_FWD = 0, TAX_INCOME, FTAX,
            PTAX, TOT_TAX;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        sin = findViewById(R.id.txtsin);
        fullName = findViewById(R.id.txtfullName);
        gender =   findViewById(R.id.txtgender);
        calc_total = findViewById(R.id.txtgrossIncome);
        calc_RRSP = findViewById(R.id.txtRRSPCont);
        calc_cpp = findViewById(R.id.txtcpp);
        calc_EmpIns = findViewById(R.id.txtempIns);
        calc_CfRRSP = findViewById(R.id.txtRRSPFWD);
        calc_TaxableIncome = findViewById(R.id.txttaxableInc);
        calc_federalTax = findViewById(R.id.txtfederalTax);
        calc_provincialTax = findViewById(R.id.txtprovincialTax);
        calc_TaxPaid = findViewById(R.id.txttaxPayed);

        //Back Button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //collecting intent
        Intent mIntent = getIntent();
        CRACustomer cust = mIntent.getParcelableExtra("CRACustomer");

        sin.setText(" SIN: \t" + cust.getSinNumber());
        fullName.setText(" FULL NAME: \t" + cust.getFullName());
        gender.setText(" GENDER: \t" + cust.getGender());
        calc_total.setText(" GROSS INCOME: \t" + cust.getGrossIncome());
        calc_RRSP.setText("RRSP Contributed: \t" + cust.getRrspContri());

        // calculate  cpp

        if(cust.getGrossIncome() > 57400.00){
            CPP = (57400.00 * 0.051);
        } else {
            CPP = (cust.getGrossIncome() * 0.051);
        }
        calc_cpp.setText("CPP COntribution in Year:\t" + CPP);
        // calculate employement insurance
        if(cust.getGrossIncome() > 53100){
            ei = (53100 * (1.62 / 100));
        }else{
            ei = (cust.getGrossIncome() * (1.62/100));
        }
        calc_EmpIns.setText("Employeement Insurance: \t" + ei);
        // calculate RRSP
        RRSP = cust.getRrspContri();
        double maxRRSP = (cust.getGrossIncome() * (18 /100));
        if(cust.getRrspContri() > maxRRSP ){
            RRSPCRY_FWD = RRSP - maxRRSP;
        }else{
            RRSP = maxRRSP;
        }
        calc_CfRRSP.setText("RRSP Carry forward: \t"+ RRSPCRY_FWD);
        //taxable income
        TAX_INCOME = (CPP - ei - RRSP);
        //Toast.makeText(this, "(Double)taxableIncome" + taxableIncome, Toast.LENGTH_SHORT).show();
        calc_TaxableIncome.setText("Taxable income:\t" + (double) TAX_INCOME);
        //federal tax
        double calFederal = calcFedralTax();
        calc_federalTax.setText("Federal Tax: \t" + calFederal);
        // Provincial Tax
        double calProvincial = calcProvincialTax();
        calc_provincialTax.setText("Provincial Tax:\t" + calProvincial);
        // total tax paid
        double taxpaid = calTaxPaid();
        calc_TaxPaid.setText("Total tax Paid:\t" + taxpaid);




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
        if(cust.getGrossIncome() > 57000.00){
            CPP = (57000.00 * (5.10 / 100));
        } else {
            CPP = (cust.getGrossIncome() * (5.10 / 100));
        }
        return CPP;
    }
    public double calcFedralTax(){
        //calculate federal tax

        if(TAX_INCOME < 12069.00){
            FTAX = 0;
            //taxableIncome = taxableIncome - 12069.00;
        }else{
            FTAX = TAX_INCOME - 1;
        }
        return FTAX;
    }
    public  double calcProvincialTax(){
        //calculate provincial tax
        return PTAX;
    }
    public  double calTaxPaid(){
        return TOT_TAX;
    }

}
