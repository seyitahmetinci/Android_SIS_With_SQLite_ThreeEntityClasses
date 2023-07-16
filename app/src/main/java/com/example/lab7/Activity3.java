package com.example.lab7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class Activity3 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);
        EditText et_no = (EditText) findViewById(R.id.stdNo);
        EditText et_name = (EditText) findViewById(R.id.stdName);
        EditText et_surname = (EditText) findViewById(R.id.stdSurname);
        RadioButton cmpeButton2 = (RadioButton) findViewById(R.id.cmpeButton2);
        RadioButton cmseButton2 = (RadioButton) findViewById(R.id.cmseButton2);
        RadioButton blgmButton2 = (RadioButton) findViewById(R.id.blgmButton2);


        ArrayList<Student> studentList = new ArrayList<>();
        MyDB db=new MyDB(this);




        Cursor cmpec=db.GetCMPEData();

        if (cmpec.moveToNext()){

            do{
                Student student = new Student(cmpec.getInt(0),cmpec.getString(1),cmpec.getString(2),cmpec.getString(3));
                studentList.add(student);


            }while(cmpec.moveToNext());
        }


        Cursor cmsec=db.GetCMSEData();

        if (cmsec.moveToNext()){

            do{
                Student student = new Student(cmsec.getInt(0),cmsec.getString(1),cmsec.getString(2),cmsec.getString(3));
                studentList.add(student);


            }while(cmsec.moveToNext());
        }



        Cursor blgmc=db.GetBLGMData();

        if (blgmc.moveToNext()){

            do{
                Student student = new Student(blgmc.getInt(0),blgmc.getString(1),blgmc.getString(2),blgmc.getString(3));
                studentList.add(student);


            }while(blgmc.moveToNext());
        }


        Button searchButton = (Button) findViewById(R.id.search_button);
        Button update_button = (Button) findViewById(R.id.update_button);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int id = Integer.parseInt(et_no.getText().toString());

                for (Student student : studentList){
                    if (student.getId() == id){

                        et_name.setText(student.getName());
                        et_surname.setText(student.getSurname());

                        if (student.getProgram().equals("CMPE")) {
                            cmpeButton2.setChecked(true);
                        } else if (student.getProgram().equals("CMSE")) {
                            cmseButton2.setChecked(true);
                        } else if (student.getProgram().equals("BLGM")) {
                            blgmButton2.setChecked(true);
                        }

                        update_button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                    String newName = et_name.getText().toString();
                                    student.setName(newName);
                                    String newSurname = et_surname.getText().toString();
                                    student.setSurname(newSurname);
                                    if (cmpeButton2.isChecked()) {
                                        student.setProgram("CMPE");
                                    } else if (cmseButton2.isChecked()) {
                                        student.setProgram("CMSE");
                                    } else if (blgmButton2.isChecked()) {
                                        student.setProgram("BLGM");
                                    }
                                    try {
//                                db.deleteCMPE(String.valueOf(student.getId()));
//                                db.deleteCMSE(String.valueOf(student.getId()));
//                                db.deleteBLGM(String.valueOf(student.getId()));

//                                    if (student.getProgram().equals("CMPE")) {
                                        db.UpdateStu(student.getId(), student.getName(), student.getSurname(), student.getProgram());
                                        Log.d("Tag1", student.getProgram());
//                                    } else if (student.getProgram().equals("CMSE")) {
//                                        db.UpdateCMSEStu(student.getId(), student.getName(), student.getSurname(), student.getProgram());
//                                        Log.d("Tag cmse", student.getProgram());
//                                    } else if (student.getProgram().equals("BLGM")) {
//                                        Log.d("Tag blgm", student.getProgram());
//                                        db.UpdateBLGMStu(student.getId(), student.getName(), student.getSurname(), student.getProgram());
//                                    }
                                } catch (Exception e) {
                                    Toast.makeText(Activity3.this, "Error while updating table", Toast.LENGTH_SHORT).show();

                                }

//         old code                       db.UpdateStu(student.getId(), student.getName(), student.getSurname(), student.getProgram());

                                Toast.makeText(Activity3.this, "Student info updated", Toast.LENGTH_SHORT).show();

                            }
                        });

                    }
                }

            }
        });

        Button list_button = (Button) findViewById(R.id.list_button);
        list_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity3.this, Activity1.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("Students", studentList);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });



    }
}