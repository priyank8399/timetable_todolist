/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetable.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import timetable.helper.DatabaseUtils;
import timetable.helper.JOptionPaneHelper;
import timetable.model.CellTimetable;
import timetable.model.DatabaseConfigs;
import timetable.model.Subject;
import timetable.model.Task;
import timetable.model.TaskPriority;


public class DatabaseManager implements DatabaseConfigs {

    private Connection connection;
    private Statement statement;

    private DatabaseManager() {
    }//Purposely made private to restrict multiple instance creation

    private static class InstanceHolder {

        private final static DatabaseManager INSTANCE = new DatabaseManager();

        static {
            INSTANCE.openConnection();
        }
    }

    public static DatabaseManager getInstance() {
        return InstanceHolder.INSTANCE;
    }

    private void openConnection() {
        try {
            System.out.println("Step 1 - Initialization of the database connection..");
            Class.forName("org.h2.Driver");
            this.connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            System.out.println("Step 2 - Connection established successfully.");
            this.statement = connection.createStatement();
            DatabaseUtils.createTable(this.statement, CREATE_SUBJECTS_TABLE, "Step 3 - Subjects table created successfully.");
            DatabaseUtils.createTable(this.statement, CREATE_TASKS_TABLE, "Step 4 - Tasks table created successfully.");
            DatabaseUtils.createTable(this.statement, CREATE_TIMETABLE_TABLE, "Step 5 - Timetable table created successfully.");
        } catch (Exception ex) {
            System.err.println("Ignore this message -> " + ex.getMessage());
        }
    }

    public void emptyTimetables() {
        try {
            String sql = "DELETE FROM " + TABLE_NAME_TIMETABLE;
            this.statement.execute(sql);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public boolean saveCellTimeTable(Subject selectedSubject, int selectedColumn, int selectedRow) {
        try {
            String sql = "MERGE INTO " + TABLE_NAME_TIMETABLE + " ("
                    + COLUMN_SUBJECT_ID + ","
                    + COLUMN_TIMETABLE_ROW + ","
                    + COLUMN_TIMETABLE_COL + ") VALUES (" + selectedSubject.getSubjectID() + ","
                    + selectedRow + ", "
                    + selectedColumn + ");";
            return this.statement.execute(sql);
        } catch (SQLException ex) {
            JOptionPaneHelper.showErrorMessage(null, ex.getMessage(), "Error");
        }
        return false;
    }

    public List<CellTimetable> getAllTimetables() {
        List<CellTimetable> timetables = new ArrayList<>();
        try {
            String sql = "SELECT * FROM " + TABLE_NAME_TIMETABLE;
            ResultSet rs = this.statement.executeQuery(sql);
            while (rs.next()) {
                CellTimetable timetable = new CellTimetable();
                timetable.setSubjectId(rs.getInt(COLUMN_SUBJECT_ID));
                timetable.setCellRow(rs.getInt(COLUMN_TIMETABLE_ROW));
                timetable.setCellColumn(rs.getInt(COLUMN_TIMETABLE_COL));
                timetables.add(timetable);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return timetables;
    }

    public boolean saveTask(Task task) {
        try {
            String sql = "INSERT INTO " + TABLE_NAME_TASK + " ("
                    + COLUMN_TASK_NAME + ","
                    + COLUMN_TASK_DATE + ","
                    + COLUMN_TASK_PRIORITY + ","
                    + COLUMN_TASK_SUBJECT_NAME
                    + ") VALUES ('" + task.getTaskName() + "',"
                    + " '" + task.getTaskDate() + "', "
                    + task.getTaskPriority().getPriority() + ", '"
                    + task.getTaskSubject() + "')";
            return this.statement.execute(sql);
        } catch (SQLException ex) {
            JOptionPaneHelper.showErrorMessage(null, ex.getMessage(), "Error");
        }
        return false;
    }

    public boolean saveSubject(String subjectName) {
        boolean result = false;
        try {
            String sql = "INSERT INTO " + TABLE_NAME_SUBJECT + " ("
                    + COLUMN_SUBJECT_NAME
                    + ") VALUES ('" + subjectName + "')";
            result = this.statement.execute(sql);
        } catch (SQLException ex) {
            JOptionPaneHelper.showErrorMessage(null, ex.getMessage(), "Error");
        }
        return result;
    }

    /**
     * This method is used to get all subjects
     *
     * @return
     */
    public List<Subject> getAllSubjects() {
        List<Subject> subjects = new ArrayList<>();
        try {
            String sql = "SELECT * FROM " + TABLE_NAME_SUBJECT;
            ResultSet rs = this.statement.executeQuery(sql);
            while (rs.next()) {
                subjects.add(new Subject(rs.getInt(COLUMN_SUBJECT_ID), rs.getString(COLUMN_SUBJECT_NAME)));
            }
            return subjects;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return subjects;
    }

    /**
     * This method is used to get all task list
     *
     * @return
     */
    public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<>();
        try {
            String sql = "SELECT * FROM " + TABLE_NAME_TASK;
            ResultSet rs = this.statement.executeQuery(sql);
            while (rs.next()) {
                Task task = new Task();
                task.setTaskID(rs.getInt(COLUMN_TASK_ID));
                task.setTaskName(rs.getString(COLUMN_TASK_NAME));
                task.setTaskDate(rs.getString(COLUMN_TASK_DATE));
                task.setTaskPriority(TaskPriority.getPriority(rs.getInt(COLUMN_TASK_PRIORITY)));
                task.setTaskSubject(rs.getString(COLUMN_TASK_SUBJECT_NAME));
                tasks.add(task);
            }
            return tasks;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return tasks;
    }

    public void updateTask(Task task) {
        try {
            String sql = "UPDATE " + TABLE_NAME_TASK + " set " + COLUMN_TASK_NAME + " = '" + task.getTaskName() + "',"
                    + " " + COLUMN_TASK_DATE + " = '" + task.getTaskDate() + "',"
                    + " " + COLUMN_TASK_SUBJECT_NAME + " = '" + task.getTaskSubject() + "',"
                    + COLUMN_TASK_PRIORITY + " = '" + task.getTaskPriority().getPriority() + "' where " + COLUMN_TASK_ID + " =  '" + task.getTaskID() + "'";
            this.statement.execute(sql);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void removeSubject(int id) {
        try {
            String sql = "DELETE FROM " + TABLE_NAME_SUBJECT + " where " + COLUMN_SUBJECT_ID + " = '" + id + "'";
            this.statement.execute(sql);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void removeTask(int taskId) {
        try {
            String sql = "DELETE FROM " + TABLE_NAME_TASK + " where " + COLUMN_TASK_ID + " = '" + taskId + "'";
            this.statement.execute(sql);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
