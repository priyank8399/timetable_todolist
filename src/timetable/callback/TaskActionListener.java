/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetable.callback;

import timetable.model.Task;


public interface TaskActionListener {

    interface AddTaskActionListener {

        void onTaskAdded(Task task);
    }

    interface ModifyTaskActionListener {

        void onTaskUpdated(Task task);
        
        void onTaskDeleted(int taskId);
    }
}
