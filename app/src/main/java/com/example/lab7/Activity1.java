package com.example.lab7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class Activity1 extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);
        Button allStudents = (Button) findViewById(R.id.allList);
        Button cmpeStudents = (Button) findViewById(R.id.cmpeList);
        Button cmseStudents = (Button) findViewById(R.id.cmseList);
        Button blgmStudents = (Button) findViewById(R.id.blgmList);

        ArrayList<Student> studentList = new ArrayList<>();
        ArrayList<Student> cmpeList = new ArrayList<>();
        ArrayList<Student> cmseList = new ArrayList<>();
        ArrayList<Student> blgmList = new ArrayList<>();

        MyDB db=new MyDB(this);

        Cursor cmpec=db.GetCMPEData();
        if (cmpec.moveToNext()){

            do{
                Student student = new Student(
                        cmpec.getInt(0),
                        cmpec.getString(1),
                        cmpec.getString(2),
                        cmpec.getString(3));

                studentList.add(student);


            }while(cmpec.moveToNext());
        }

        Cursor cmsec=db.GetCMSEData();
        if (cmsec.moveToNext()){

            do{
                Student student = new Student(
                        cmsec.getInt(0),
                        cmsec.getString(1),
                        cmsec.getString(2),
                        cmsec.getString(3));

                studentList.add(student);


            }while(cmsec.moveToNext());
        }

        Cursor blgmc=db.GetBLGMData();
        if (blgmc.moveToNext()){

            do{
                Student student = new Student(
                        blgmc.getInt(0),
                        blgmc.getString(1),
                        blgmc.getString(2),
                        blgmc.getString(3));

                studentList.add(student);


            }while(blgmc.moveToNext());
        }



// Loop through the studentList and add each student to the corresponding program ArrayList
        for (Student student : studentList) {
            if (student.getProgram().equals("CMPE")) {
                cmpeList.add(student);
            } else if (student.getProgram().equals("CMSE")) {
                cmseList.add(student);
            } else if (student.getProgram().equals("BLGM")) {
                blgmList.add(student);
            }
        }

        allStudents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("Students", studentList);
                Intent intent = new Intent(Activity1.this, Activity2.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        cmpeStudents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("Students", cmpeList);
                Intent intent = new Intent(Activity1.this, Activity2.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        cmseStudents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("Students", cmseList);
                Intent intent = new Intent(Activity1.this, Activity2.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        blgmStudents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("Students", blgmList);
                Intent intent = new Intent(Activity1.this, Activity2.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });






    }
}