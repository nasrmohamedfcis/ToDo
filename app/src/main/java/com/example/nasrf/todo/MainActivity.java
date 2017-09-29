package com.example.nasrf.todo;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView tvWelcome,tvTodaysTasks;
    private EditText edSearch;
    private MyHelper tasks;
    private SQLiteDatabase db;
    private String currentDate;
    private ListView lvTasks;
    private AdapterView adapter;
    private List<Tasks> myTasks;
    private ImageButton btnAllTasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialization
        currentDate = getDate();
        tvWelcome = (TextView)findViewById(R.id.tvWelcome);
        edSearch = (EditText) findViewById(R.id.edSearch);
        //edSearch = (EditText) findViewById(R.id.edSearch);
        tvWelcome.setText(currentDate);
        tvTodaysTasks = (TextView) findViewById(R.id.tvTodaysTasks);
        btnAllTasks = (ImageButton) findViewById(R.id.btnUpComing);
        lvTasks = (ListView) findViewById(R.id.lvTasks);
        myTasks = new ArrayList<>();

        currentTask(currentDate);
        //db.delete("tasks",null,null);

    }// end of on create

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return true; //return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()== R.id.action_add){
            Intent in = new Intent(this,AddTask.class);
            startActivity(in);
        }

        return super.onOptionsItemSelected(item);
    }


    //returns current date
    public String getDate(){
        Calendar c = Calendar.getInstance();
        int month = c.get(Calendar.MONTH);
        month = month+1;
        String m = Integer.toString(month);
        String cDate = c.get(Calendar.DAY_OF_MONTH) + "/"
                + m + "/" + c.get(Calendar.YEAR) ;
        return cDate;
    }// end of get date

    public void currentTask(String cDate){

        String s_date = getDate();
        //tvTodaysTasks.setText("here is your  Task coming on " + s_date);
        tasks = new MyHelper(MainActivity.this);
        db = tasks.getWritableDatabase();


        Cursor pointer = db.query("tasks",new String[]{"task_name","note","start_date","end_date"},"start_date = ?",new String[]{s_date},null,null,null);
        String name="", desc="", sDate="",eDate="";

        if(pointer.getCount()==0){
            tvTodaysTasks.setText("no tasks for today. ");
        }
        else{
            tvTodaysTasks.setText("Your task(s) for today. ");
        }

        while(pointer.moveToNext()){
            name = pointer.getString(0);
            desc = pointer.getString(1);
            sDate = pointer.getString(2);
            eDate = pointer.getString(3);
            myTasks.add(new Tasks(name,desc,sDate,eDate));
        }

        adapter = new AdapterView(getApplicationContext(),myTasks);
        lvTasks.setAdapter(adapter);


    }

    public void searchTask(View view) {
        myTasks.clear();
        //Toast.makeText(this, "button clicked", Toast.LENGTH_SHORT).show();
        String s_date = edSearch.getText().toString();
        tvTodaysTasks.setText("here is your  Task coming on " + s_date);
        tasks = new MyHelper(MainActivity.this);
        db = tasks.getWritableDatabase();


        Cursor pointer = db.query("tasks",new String[]{"task_name","note","start_date","end_date"},"start_date = ?",new String[]{s_date},null,null,null);
        String name="", desc="", sDate="",eDate="";

        if(pointer.getCount()==0){
            tvTodaysTasks.setText("no tasks on " + s_date);
        }
        while(pointer.moveToNext()){
            name = pointer.getString(0);
            desc = pointer.getString(1);
            sDate = pointer.getString(2);
            eDate = pointer.getString(3);
            myTasks.add(new Tasks(name,desc,sDate,eDate));
        }

        adapter = new AdapterView(getApplicationContext(),myTasks);
        lvTasks.setAdapter(adapter);


    }

    public void allTasks(View view) {
        myTasks.clear();
        tvTodaysTasks.setText("Here is your Upcoming Tasks");
        tasks = new MyHelper(MainActivity.this);
        db = tasks.getWritableDatabase();

        //presenting the data
        Cursor pointer = db.query("tasks",null,null,null,null,null,"id");
        String name="",note="",startDate="",endDate="";


        while(pointer.moveToNext()){
            //rows += pointer.getInt(0) + " " + pointer.getInt(1) + " " +pointer.getInt(3) +" " + pointer.getInt(4) +" ";
            name = pointer.getString(1);
            note = pointer.getString(2);
            startDate = pointer.getString(3);
            endDate = pointer.getString(4);
            myTasks.add(new Tasks(name,note,startDate,endDate));
        }

        //init Adapter
        adapter = new AdapterView(getApplicationContext(),myTasks);
        lvTasks.setAdapter(adapter);
    }

}


