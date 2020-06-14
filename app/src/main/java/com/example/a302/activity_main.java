package com.example.a302;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SearchView;

public class activity_main extends AppCompatActivity {

    private ImageButton listButton;
    private SearchView searchView;

    private ImageView topPickButton1;
    private ImageView topPickButton2;
    private ImageView topPickButton3;

    private CardView CatElectric;
    private CardView CatHybrid;
    private CardView CatPetrol;

    private int[] topThreeCars;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize search element
        searchView = (SearchView) findViewById(R.id.Search);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                OpenCategoryList(s); //passes the entered string to the search function
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        //Find top 3 viewed cars
        topThreeCars = DataProvider.getTopThreeViewedCars();

        //initialize top pick buttons
        //Most viewed
        topPickButton1 = (ImageButton) findViewById(R.id.TopPick1);
        topPickButton1.setBackgroundResource(DataProvider.carImages[topThreeCars[0]][0]); //Gets the first picture of the most viewed car
        topPickButton1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                OpenDetailActivity(topThreeCars[0]); //passes the id of the desired car to detail activity
            }
        });

        //Second most viewed
        topPickButton2 = (ImageButton) findViewById(R.id.TopPick2);
        topPickButton2.setBackgroundResource(DataProvider.carImages[topThreeCars[1]][0]); //Gets the first picture of the second most viewed car
        topPickButton2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                OpenDetailActivity(topThreeCars[1]); //passes the id of the desired car to detail activity
            }
        });

        //Third most viewed
        topPickButton3 = (ImageButton) findViewById(R.id.TopPick3);
        topPickButton3.setBackgroundResource(DataProvider.carImages[topThreeCars[2]][0]); //Gets the first picture of the third most viewed car
        topPickButton3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                OpenDetailActivity(topThreeCars[2]); //passes the id of the desired car to detail activity
            }
        });

        //initialize full list button
        listButton = (ImageButton) findViewById(R.id.ListButton);
        listButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                OpenFullList();
            }
        });

        //initialize Category buttons
        CatElectric = (CardView) findViewById(R.id.CatElectric);
        CatElectric.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                OpenCategoryList("electric");
            }
        });

        CatHybrid = (CardView) findViewById(R.id.CatHybrid);
        CatHybrid.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                OpenCategoryList("hybrid");
            }
        });

        CatPetrol = (CardView) findViewById(R.id.CatPetrol);
        CatPetrol.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                OpenCategoryList("petrol");
            }
        });
    }

    public void OpenFullList() {
        Intent intent = new Intent(this, activity_recycler.class);
        startActivity(intent);
    }

    public void OpenCategoryList(String search) {
        Intent intent = new Intent(this, activity_recycler.class);
        intent.putExtra("Search", search); //passes the search string to the new activity before starting
        startActivity(intent);
    }

    public void OpenDetailActivity(int index) {
        Intent intent = new Intent(this, activity_detail.class);
        intent.putExtra("Id", index); //passes the car id  to the detail activity before starting
        startActivity(intent);
    }
}