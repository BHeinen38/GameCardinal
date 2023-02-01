package com.example.recycleview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<String> names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.RecyclerView);
        names= new ArrayList<>();
        names.add("Mohna");
        names.add("Rania");
        names.add("Bailey");
        names.add("Eric");
        names.add("Jacob");

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        CustomAdapter customAdapter = new CustomAdapter((ArrayList<String>) names, MainActivity.this);
        recyclerView.setAdapter(customAdapter);

    }
}