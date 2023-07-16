package com.example.lab7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText et_number, et_name, et_surname;
    RadioGroup programRadioGroup;
    Button saveButton, listButton;
    ListView lv_main;

    private String selectedProgram;


    private ArrayList<Student> studentList;
//    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyDB db = new MyDB(this);

        studentList = new ArrayList<Student>();
        saveButton = findViewById(R.id.save_button);
        listButton = findViewById(R.id.list_button);
        et_number = findViewById(R.id.et_number);
        et_name = findViewById(R.id.et_name);
        et_surname = findViewById(R.id.et_surname);
        programRadioGroup = findViewById(R.id.GroupButton);

        RadioButton cmpeButton = (RadioButton) findViewById(R.id.cmpeButton);
        RadioButton cmseButton = (RadioButton) findViewById(R.id.cmseButton);
        RadioButton blgmButton = (RadioButton) findViewById(R.id.blgmButton);
//        studentList.add(new Student(100, "John", "Doe", "CMPE"));
//        studentList.add(new Student(101, "Lorem", "Ipsum", "CMSE"));
//        studentList.add(new Student(102, "Jam", "Artykov", "CMPE"));
//        studentList.add(new Student(103, "N", "D", "BLGM"));




        programRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.cmpeButton) {
                    selectedProgram = "CMPE";
                } else if (checkedId == R.id.cmseButton) {
                    selectedProgram = "CMSE";
                } else if (checkedId == R.id.blgmButton) {
                    selectedProgram = "BLGM";
                }

            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    int id = Integer.parseInt(et_number.getText().toString());
                    String name = et_name.getText().toString();
                    String surname = et_surname.getText().toString();

                    if (cmpeButton.isChecked()) {
                        db.AddCMPEStu(id,name,surname,selectedProgram);
                    } else if (cmseButton.isChecked()) {
                        db.AddCMSEStu(id,name,surname,selectedProgram);
                    } else if (blgmButton.isChecked()) {
                        db.AddBLGMStu(id,name,surname,selectedProgram);
                    }

//               old code #lab6    db.AddStu(id,name,surname,selectedProgram);


//                    Student student = new Student (id,name,surname,selectedProgram);
//                    studentList.add(student);

                    et_name.setText("");
                    et_number.setText("");
                    et_surname.setText("");
                    programRadioGroup.clearCheck();

                    Toast.makeText(MainActivity.this, "Student Added to DB", Toast.LENGTH_SHORT).show();
//                    adapter= new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, studentList);
//                    lv_main.setAdapter(adapter);
                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Fill the Fields", Toast.LENGTH_SHORT).show();
                }


            }
        });

        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Bundle bundle = new Bundle();
                bundle.putSerializable("Students", studentList);
                Intent intent = new Intent(MainActivity.this, Activity1.class);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });


    }


}






