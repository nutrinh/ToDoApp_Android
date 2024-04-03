package com.majestica.catlist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class StudiesActivity extends AppCompatActivity
{

    EditText editText;
    Button addButton;
    ListView listView;

    ArrayList<String> itemList = new ArrayList<>();
    ItemAdapter itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_design);

        editText = findViewById(R.id.editText);
        addButton = findViewById(R.id.button);
        listView = findViewById(R.id.list);


        itemList = FileHelper.readData(this);

        itemAdapter = new ItemAdapter(itemList, this);
        listView.setAdapter(itemAdapter);


        addButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                String itemName = editText.getText().toString();
                itemList.add(itemName);

                editText.setText("");

                FileHelper.writeData(itemList, getApplicationContext());

                itemAdapter.notifyDataSetChanged();
            }
        });
    }
}