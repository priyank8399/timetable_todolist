/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetable.model;


public class Task {
    
    private int taskID;
    private String taskName;
    private String taskDate;
    private TaskPriority taskPriority;
    private String taskSubject;

    @Override
    public String toString() {
        return taskName; //To change body of generated methods, choose Tools | Templates.
    }

    public Task() {
    }

    public int getTaskID() {
        return taskID;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    public Task(String taskName, String taskDate, TaskPriority taskPriority, String taskSubject) {
        this.taskName = taskName;
        this.taskDate = taskDate;
        this.taskPriority = taskPriority;
        this.taskSubject = taskSubject;
    }

    /**
     * @return the taskName
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * @param taskName the taskName to set
     */
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    /**
     * @return the taskDate
     */
    public String getTaskDate() {
        return taskDate;
    }

    /**
     * @param taskDate the taskDate to set
     */
    public void setTaskDate(String taskDate) {
        this.taskDate = taskDate;
    }

    /**
     * @return the taskPriority
     */
    public TaskPriority getTaskPriority() {
        return taskPriority;
    }

    /**
     * @param taskPriority the taskPriority to set
     */
    public void setTaskPriority(TaskPriority taskPriority) {
        this.taskPriority = taskPriority;
    }

    /**
     * @return the taskSubject
     */
    public String getTaskSubject() {
        return taskSubject;
    }

    /**
     * @param taskSubject the taskSubject to set
     */
    public void setTaskSubject(String taskSubject) {
        this.taskSubject = taskSubject;
    }
    

}
