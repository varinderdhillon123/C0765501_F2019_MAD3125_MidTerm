package com.example.c0765501_f2019_mad3125_midterm;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private TextView calc_fullname;
    private TextView calc_sin;
    private TextView calc_age;
    //private TextView calc_total;

    private TextView calc_gender;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        calc_fullname = findViewById(R.id.calFullName);
        calc_sin = findViewById(R.id.caLSin_no);
        calc_age = findViewById(R.id.calAge);
      //  calc_total = findViewById(R.id.txttotal_inc);

        calc_gender = findViewById(R.id.calGender);


        backButton();

        Intent intent = getIntent();
        CRACustomer calCRA = intent.getParcelableExtra("CRACustomer");

        calc_sin.setText("SIN=" +calCRA.getSin_no());
        calc_fullname.setText("FULLNAME=" +calCRA.getFullName());
        calc_age.setText("AGE=" +calCRA.getAge());
     //   calc_total.setText("TOTAL=" +calCRA.getTotal());


        calc_gender.setText("GENDER=" + calCRA.getGender());


    }

    public void backButton()
    {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {

            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }

    public boolean onOptionstemsSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        return true;
    }

}
