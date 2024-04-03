package com.majestica.catlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    private RecyclerView recyclerView;
    private ArrayList<Category> categoryList;
    private CategoryAdapter categoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        categoryList = new ArrayList<>();
        categoryList.add(new Category("doingchores", "Household chores"));
        categoryList.add(new Category("socializing", "Family and friends"));
        categoryList.add(new Category("studying", "Studies"));
        categoryList.add(new Category("working", "Work"));

        categoryAdapter = new CategoryAdapter(categoryList, this);

        recyclerView.setAdapter(categoryAdapter);


    }
}