/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetable.render;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;


public class SubjectCellEditor extends DefaultCellEditor {

    public SubjectCellEditor(JComboBox comboBox) {
        super(comboBox);
        //((JComboBox) editorComponent).setRenderer(new SubjectDropDownCellRenderer());
    }
}
