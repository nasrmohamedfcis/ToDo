package com.example.nasrf.todo;

/**
 * Created by nasrf on 17/9/2017.
 */

public class Tasks {
    private String taskName, taskDesc, startDate, endDate;

    //cosntructor

    public Tasks(String taskName, String taskDesc, String startDate, String endDate) {
        this.taskName = taskName;
        this.taskDesc = taskDesc;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    //setter & getter

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

} // end of class Tasks
