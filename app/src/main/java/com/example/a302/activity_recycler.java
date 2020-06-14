package com.example.a302;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class activity_recycler extends AppCompatActivity implements recycle_adapter.OnNoteListener {

    private ArrayList<Car> carListFull;

    private ArrayList<Car> carsList;
    private RecyclerView recyclerView;

    private ImageButton backButtonList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        //initialize back button
        backButtonList = (ImageButton) findViewById(R.id.backButtonList);
        backButtonList.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                GoToMainActivity();
            }
        });

        //initialize view image slider
        recyclerView = findViewById(R.id.recycler);
        carsList = new ArrayList<>();
        setCarInfo();
        setAdapter();
    }

    public void GoToMainActivity() { //used to go back to main page
        Intent intent = new Intent(this, activity_main.class);
        startActivity(intent);
    }

    private void setAdapter() { //this sets the adaptor and the layout manager
        recycle_adapter adapter = new recycle_adapter(carsList, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void setCarInfo() {
        if (getIntent().getExtras() != null) { //if nothing was passed to the recycler when starting. ie a search
            Bundle bundle = getIntent().getExtras();
            carsList = DataProvider.generateDataSearch(bundle.getString("Search").toLowerCase());
            if (carsList.size() == 0) { //If no cars are found the this search show the no results notification
                TextView noResultsText = (TextView) findViewById(R.id.NoResultText);
                noResultsText.setVisibility(View.VISIBLE);
                noResultsText.setText(String.format("No results found for:\n'%s'", bundle.getString("Search")));
            }
        }
        else { //if there was no search, then just open the whole list
            carsList = DataProvider.generateData();
        }
    }

    @Override
    public void onNoteClick(int position) { //this allows us to find the element within the recycler that was clicked
        OpenDetailActivity(carsList.get(position).getCarId()); //pass id of chosen car to the detail activity
    }

    public void OpenDetailActivity(int id) { //opens detail activity with referance to a car id of which to show
        Intent intent = new Intent(this, activity_detail.class);
        intent.putExtra("Id", id); //pass the car id to the detail activity
        startActivity(intent);
    }
}