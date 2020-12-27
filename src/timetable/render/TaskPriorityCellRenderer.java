/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetable.render;

import java.awt.Color;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import timetable.model.Task;


public class TaskPriorityCellRenderer extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        Task task = (Task) value;
        JLabel taskLabel = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        taskLabel.setBackground(task.getTaskPriority().getPriorityColor());
        taskLabel.setForeground(Color.WHITE);
        return taskLabel;
    }
}
