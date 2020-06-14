package com.example.a302;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class activity_detail extends AppCompatActivity {

    ViewPager viewPager;
    ImageButton backButton;
    TextView nameText;
    TextView yearText;
    TextView descText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //initialize back button
        backButton = (ImageButton) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                GoToMainActivity();
            }
        });

        Bundle bundle = getIntent().getExtras(); //Data recived

        //initialize image slider
        viewPager = (ViewPager)findViewById(R.id.myViewPager);
        view_pager_adapter viewpageradapter = new view_pager_adapter(this, DataProvider.carImages[bundle.getInt("Id")]);
        viewPager.setAdapter(viewpageradapter);

        //set name, year and description text
        nameText = (TextView) findViewById(R.id.DetailNameText);
        nameText.setText(DataProvider.carNames[bundle.getInt("Id")]);

        yearText = (TextView) findViewById(R.id.DetailYearText);
        yearText.setText(DataProvider.carYears[bundle.getInt("Id")]);

        descText = (TextView) findViewById(R.id.DetailDescText);
        descText.setText(DataProvider.carDescriptions[bundle.getInt("Id")]);

        //Increment view count of car
        DataProvider.carViews[bundle.getInt("Id")]++;
    }

    public void GoToMainActivity() {
        Intent intent = new Intent(this, activity_main.class);
        startActivity(intent);
    }
}