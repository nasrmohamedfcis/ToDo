package com.example.nasrf.todo;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class AddTask extends AppCompatActivity {

    private EditText etTaskName, etTaskNote;
    private MyHelper tasks;
    private SQLiteDatabase db;
    private DatePickerDialog.OnDateSetListener dateListenerV;
    private EditText startDate, endDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        etTaskName = (EditText)findViewById(R.id.etTaskName);
        etTaskNote = (EditText) findViewById(R.id.etTaskNote);
        startDate = (EditText) findViewById(R.id.btnStartDate);
        endDate = (EditText) findViewById(R.id.btnEndDate);
        tasks = new MyHelper(this);
        db = tasks.getWritableDatabase();

    }



    public void addTask(View view) {
        //adding task to database
        String taskName,taskNote,sDate,eDate;
        taskName = etTaskName.getText().toString();
        taskNote = etTaskNote.getText().toString();
        sDate = startDate.getText().toString();
        eDate = endDate.getText().toString();

        ContentValues row = new ContentValues();
        row.put("task_name",taskName);
        row.put("note",taskNote);
        row.put("start_date",sDate);
        row.put("end_date",eDate);

        long id = db.insert("tasks",null,row);

        if(id<0){
            Toast.makeText(this,"Couldn't save ...",Toast.LENGTH_LONG).show();
            finish();
        }
        else{
            Toast.makeText(this, "Task saved, Task number: " + id , Toast.LENGTH_LONG).show();
            finish();
        }

    } //end of add task


}
