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
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    CRACustomer cra;
    final Calendar calendar = Calendar.getInstance();

    private EditText sin_no;
    private EditText first;
    private EditText last;
    private EditText Total;
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





        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
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
                Double grossIncome = Double.parseDouble(Total.getText().toString());
                Double rrsp = Double.parseDouble(RRSPCont.getText().toString());
                cra = new CRACustomer(sin_no.getText().toString(),
                        first.getText().toString(),
                        last.getText().toString(),
                        Radio, grossIncome, rrsp);
                Intent mIntent = new Intent(MainActivity.this, ResultActivity.class);
                mIntent.putExtra("CRACustomer", cra);
                startActivity(mIntent);
            }
        });

    };
    private void dateFormat() {
        String myFormat = "dd-MMM-yyyy"; //In which you need put here
        java.text.SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        txtDOB.setText(sdf.format(calendar.getTime()));
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


