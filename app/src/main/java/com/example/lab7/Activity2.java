package com.example.lab7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;



public class Activity2 extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        ArrayList<Student> studentList = (ArrayList<Student>) getIntent().getSerializableExtra("Students");

        ListView lv = (ListView) findViewById(R.id.listView);
        ArrayAdapter adapter= new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, studentList);
        lv.setAdapter(adapter);


        Button backButton = (Button) findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button searchButton = (Button) findViewById(R.id.button2);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("Students", studentList);
                Intent intent = new Intent(Activity2.this, Activity3.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }
}