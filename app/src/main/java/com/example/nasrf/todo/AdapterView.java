package com.example.nasrf.todo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by nasrf on 15/9/2017.
 */

public class AdapterView extends BaseAdapter {

    private Context tContext;
    private List<Tasks> myTasks;

    //constructor


    public AdapterView(Context tContext, List<Tasks> myTasks) {
        this.tContext = tContext;
        this.myTasks = myTasks;
    }

    //methods
    @Override
    public int getCount() {return myTasks.size();}

    @Override
    public Object getItem(int position) {
        return myTasks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(tContext,R.layout.task_view,null);
        TextView taskName = (TextView) v.findViewById(R.id.tvResultedName);
        TextView taskDesc = (TextView) v.findViewById(R.id.tvResultedNote);
        TextView startDate = (TextView) v.findViewById(R.id.tvResultedStartDate);
        TextView endDate = (TextView) v.findViewById(R.id.tvResultedEndDate);

        //set text for the textViews
        taskName.setText(myTasks.get(position).getTaskName());
        taskDesc.setText(myTasks.get(position).getTaskDesc());
        startDate.setText(myTasks.get(position).getStartDate());
        endDate.setText(myTasks.get(position).getEndDate());
        return v;
    }
}
