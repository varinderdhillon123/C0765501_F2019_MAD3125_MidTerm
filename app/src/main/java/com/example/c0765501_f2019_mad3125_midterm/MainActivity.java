package com.example.c0765501_f2019_mad3125_midterm;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    CRACustomer cra;
    final Calendar calendar = Calendar.getInstance();

    private EditText sin_no;
    private EditText first;
    private EditText last;
    private EditText Total;
    private EditText Age;
    private TextView RRSPCont;
    private TextView Fullname;
    private TextView txtgender;
    private RadioGroup rgGENDER;
    private RadioButton rbgender;
    private RadioButton rbMALE;
    private RadioButton rbFEMALE;
    private RadioButton rbOTHER;
    private Button button;
    private String Radio = "";
    private TextView txtDOB ;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sin_no = findViewById(R.id.sin_no);
        first = findViewById(R.id.txtFName);
        last = findViewById(R.id.txtLName);
        txtgender = findViewById(R.id.txtgender);
        rgGENDER = findViewById(R.id.rgGENDER);
        rbMALE = findViewById(R.id.rbMale);
        rbFEMALE = findViewById(R.id.rbFemale);
        rbOTHER = findViewById(R.id.rbOthers);
        Total = findViewById(R.id.txttotal_inc);
        button = findViewById(R.id.btn_calc);
        txtDOB = findViewById(R.id.txtDOB);
        RRSPCont = findViewById(R.id.edtRRSP);
        Age=findViewById(R.id.txtAge);





        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                dateFormat();
            }
        };
        txtDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(MainActivity.this, date, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        rgGENDER.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.rbMale){
                    Radio = rbMALE.getText().toString();
                }else if(checkedId == R.id.rbFemale){
                    Radio = rbFEMALE.getText().toString();
                }else {
                    Radio = rbOTHER.getText().toString();
                }
            }

        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sin_no.getText().toString().length() != 11 &&
                        first.getText().toString().length() == 0 &&
                        last.getText().toString().length() == 0 &&
                        Age.getText().toString().length()==0&&
                        txtDOB.getText().toString().length() == 0 &&
                        Total.getText().toString().length() == 0 &&
                        RRSPCont.getText().toString().length() == 0
                ) {
                    sin_no.setError("Sin Incorrect");
                    first.setError("This Field Cannot Be Empty");
                    last.setError("This Field Cannot Be Empty");
                    Age.setError("This Field cannot be Empty");
                    txtDOB.setError("This Field Cannot Be Empty");
                    Total.setError("This Field Cannot Be Empty");
                    RRSPCont.setError("This Field Cannot Be Empty");
                }
                if (sin_no.getText().toString().length() != 11)
                {
                    sin_no.setError("Sin Incorrect");
                }
                if (first.getText().toString().length() == 0)
                {
                    first.setError("This Field Cannot Be Empty");
                }


                if (last.getText().toString().length() == 0)
                {
                    last.setError("This Field Cannot Be Empty");
                }
                if (txtDOB.getText().toString().length() == 0)
                {
                    txtDOB.setError("This Field Cannot Be Empty");
                }
//                if (Age < 18) {
//                    txtDOB.setError("Not Eligible For filing tax");
//                    txtDOB.setTextColor(getResources().getColor(R.color.colorAccent));
//                }
                if (Total.getText().toString().length() == 0) {
                    Total.setError("This Field Cannot Be Empty");
                }
                if (RRSPCont.getText().toString().length() == 0) {
                    RRSPCont.setError("This Field Cannot Be Empty");
                }
                else
                    {


                    Double grossIncome = Double.parseDouble(Total.getText().toString());
                    Double rrsp = Double.parseDouble(RRSPCont.getText().toString());
                    cra = new CRACustomer(sin_no.getText().toString(),
                            first.getText().toString(),
                            Age.getText().toString(),
                            last.getText().toString(),
                            Radio, grossIncome, rrsp);
                    Intent mIntent = new Intent(MainActivity.this, ResultActivity.class);
                    mIntent.putExtra("CRACustomer", cra);
                    startActivity(mIntent);
                }
            }
        });

    };
    @RequiresApi(api = Build.VERSION_CODES.O)
    private String dateFormat() {
        String myFormat = "dd-MMM-yyyy"; //In which you need put here
        java.text.SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        txtDOB.setText(sdf.format(calendar.getTime()));
        LocalDate l = LocalDate.of(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        LocalDate now = LocalDate.now(); //gets localDate
        Period diff = Period.between(l, now); //difference between the dates is calculated
        System.out.println(diff.getYears() + "years" + diff.getMonths() + "months" + diff.getDays() + "days");
        String n1=String.valueOf(diff.getYears());
        String n2=String.valueOf(diff.getMonths());
        String n3=String.valueOf(diff.getDays());
        String age="Age: "+n1+"Years"+n2+"Months"+n3+"Days";
        Age.setText(age);
        return  n1;

    }

    public  void checkedButton(View view) {
        int radioId = rgGENDER.getCheckedRadioButtonId();
        rgGENDER.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rbMale) {
                    Radio = rbMALE.getText().toString();
                } else if (checkedId == R.id.rbFemale) {
                    Radio = rbFEMALE.getText().toString();
                } else {
                    Radio = rbOTHER.getText().toString();
                }
            }
        });
    }

}


