/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetable.model;


public interface DatabaseConfigs {

    String JDBC_DRIVER = "org.h2.Driver";
    String DB_URL = "jdbc:h2:~/timetableDB";
    String DB_USERNAME = "user";
    String DB_PASSWORD = "user";

    String TABLE_NAME_SUBJECT = "SUBJECT";
    String TABLE_NAME_TASK = "TASKS";
    String TABLE_NAME_TIMETABLE = "TIMETABLE";

    String COLUMN_SUBJECT_ID = "subjectID";
    String COLUMN_SUBJECT_NAME = "subjectName";
    String COLUMN_TASK_ID = "taskID";
    String COLUMN_TASK_NAME = "taskName";
    String COLUMN_TASK_DATE = "taskDate";
    String COLUMN_TASK_PRIORITY = "taskPriority";
    String COLUMN_TASK_SUBJECT_NAME = "taskSubjectName";
    String COLUMN_TASK_SUBJECT_ID = "taskSubjectID";

    String COLUMN_TIMETABLE_ID = "timetableID";
    String COLUMN_TIMETABLE_ROW = "timetableRow";
    String COLUMN_TIMETABLE_COL = "timetableCol";

    String CREATE_SUBJECTS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_SUBJECT
            + "(" + COLUMN_SUBJECT_ID + " bigint auto_increment NOT NULL PRIMARY KEY, "
            + COLUMN_SUBJECT_NAME + " VARCHAR(255))";

    String CREATE_TASKS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_TASK
            + "(" + COLUMN_TASK_ID + " bigint auto_increment NOT NULL , "
            + COLUMN_TASK_NAME + " VARCHAR(255), "
            + COLUMN_TASK_DATE + " VARCHAR(255), "
            + COLUMN_TASK_PRIORITY + " INT, "
            + COLUMN_TASK_SUBJECT_NAME + " VARCHAR(255), "
            + COLUMN_TASK_SUBJECT_ID + "  VARCHAR(255),"
            + "PRIMARY KEY(" + COLUMN_TASK_ID + ", " + COLUMN_TASK_NAME + " ))";

    String CREATE_TIMETABLE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_TIMETABLE +"("
            + COLUMN_SUBJECT_ID + " bigint, "
            + COLUMN_TIMETABLE_ROW + " INT, "
            + COLUMN_TIMETABLE_COL + " INT, "
            + "PRIMARY KEY(" + COLUMN_TIMETABLE_ROW + ", " + COLUMN_TIMETABLE_COL + " ),"
            + "CONSTRAINT fk_insubject_id FOREIGN KEY (" + COLUMN_SUBJECT_ID + ") REFERENCES " + TABLE_NAME_SUBJECT + " (" + COLUMN_SUBJECT_ID + ") ON DELETE CASCADE );";
}
