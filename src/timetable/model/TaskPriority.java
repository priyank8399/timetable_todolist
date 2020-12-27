/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetable.model;

import java.awt.Color;

public enum TaskPriority {

    LOW(0, Color.GREEN), MEDIUM(1, Color.BLUE), HIGH(2, Color.RED);

    private final int priority;
    private final Color priorityColor;

    private TaskPriority(int priority, Color priorityColor) {
        this.priority = priority;
        this.priorityColor = priorityColor;
    }

    public Color getPriorityColor() {
        return priorityColor;
    }
    
    

    public int getPriority() {
        return priority;
    }

    public static TaskPriority getPriority(int selectedIndex) {
        TaskPriority priority = LOW;
        for (TaskPriority pr : values()) {
            if (pr.getPriority() == selectedIndex) {
                priority = pr;
            }
        }
        return priority;
    }
}
