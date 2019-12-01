package com.example.c0765501_f2019_mad3125_midterm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
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

    final Calendar calendar = Calendar.getInstance();
    TextView txtDate;
    private Button button;

    private EditText sin;
    private EditText fname;
    private EditText lname;
    private EditText Age;
    //private EditText totalIncome;
    private RadioGroup rgGender;
    private RadioButton rbMale;
    private RadioButton rbFMale;
    private RadioButton rbOthers;

    String Radio = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.btn_calc);


        fname = findViewById(R.id.txtFName);
        sin = findViewById(R.id.sin_no);
        lname = findViewById(R.id.txtLName);
        Age = findViewById(R.id.txtAge);
      //  totalIncome = findViewById(R.id.txttotal_inc);
        rgGender = findViewById(R.id.rgGENDER);
        rbMale = findViewById(R.id.rbMale);
        rbFMale = findViewById(R.id.rbFemale);
        rbOthers = findViewById(R.id.rbOthers);

        RadioAction();
        FinalResult();

        txtDate = findViewById(R.id.txtDOB);
        txtDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new DatePickerDialog(MainActivity.this, date, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            }

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


            private void dateFormat() {
                String myFormat = "dd-MMM-yyyy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                txtDate.setText(sdf.format(calendar.getTime()));
            }


//            public void ResultActivity(View v) {
//
//                startActivity(new Intent(MainActivity.this, ResultActivity.class));
//
//
//            }
        });
    }

    public void RadioAction()
    {
        rgGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {

                if(checkedId==R.id.rbMale)
                {
                    Radio = rbMale.getText().toString();
                    Toast.makeText(getApplicationContext(),"Male",Toast.LENGTH_SHORT).show();
                    rbMale.setSelected(true);
                }
                else if(checkedId==R.id.rbFemale)
                {

                    Radio = rbFMale.getText().toString();
                    Toast.makeText(getApplicationContext(),"Female",Toast.LENGTH_SHORT).show();
                    rbFMale.setSelected(true);
                    //rbFMale.setText("Female!");
                }
                else if(checkedId==R.id.rbOthers)
                {

                    Radio = rbOthers.getText().toString();
                    Toast.makeText(getApplicationContext(),"Others",Toast.LENGTH_SHORT).show();
                    rbOthers.setSelected(true);
                    // rbOthers.setText("Others!");
                }
            }
        });
    }
    public void FinalResult()
    {

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)

            {


                CRACustomer cra = new CRACustomer(sin.getText().toString(),Age.getText().toString(),fname.getText().toString(), lname.getText().toString(),
                        rgGender.toString());

                Intent mIntent = new Intent(MainActivity.this, ResultActivity.class);
                mIntent.putExtra("CRACustomer", cra);
//                mIntent.putExtra("gender", radio);
                startActivity(mIntent);

            }
        });
    }

}


