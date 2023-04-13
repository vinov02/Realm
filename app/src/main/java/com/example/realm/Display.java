package com.example.realm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class Display extends AppCompatActivity {
    List<DataModel> dataModelList;
            Realm realm;
            RecyclerView recyclerView;
            RecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        realm = Realm.getDefaultInstance();
        recyclerView = findViewById(R.id.rc_course);

        dataModelList = new ArrayList<>();

        dataModelList = realm.where(DataModel.class).findAll();

        recyclerViewAdapter = new RecyclerViewAdapter(dataModelList,this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerViewAdapter);



    }
}