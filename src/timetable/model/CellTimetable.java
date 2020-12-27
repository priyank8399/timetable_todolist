/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetable.model;


public class CellTimetable {

    private int subjectID;
    private int cellRow;
    private int cellColumn;

    public void setSubjectId(int subjectID) {
        this.subjectID = subjectID;
    }

    public void setCellRow(int cellRow) {
        this.cellRow = cellRow;
    }

    public void setCellColumn(int cellColumn) {
        this.cellColumn = cellColumn;
    }

    public int getSubjectID() {
        return subjectID;
    }

    public int getCellRow() {
        return cellRow;
    }

    public int getCellColumn() {
        return cellColumn;
    }
}
