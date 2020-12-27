/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetable.helper;

import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseUtils {
    
    private DatabaseUtils() {}
    
    public static void createTable(Statement statement, String query, String logMessage) {
        try {
            statement.execute(query);
            System.out.println(logMessage);
        } catch (SQLException ex) {
            System.err.println("Ignore this message -> " + ex.getMessage());
        }
    }
}
