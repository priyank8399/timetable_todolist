/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetable;

import timetable.gui.Timetable;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import timetable.helper.ResourceLoader;
import timetable.model.AppConstants;

public class Main {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Timetable timetable = new Timetable();
                timetable.setTitle(AppConstants.APP_TITLE);
                timetable.setIconImage(ResourceLoader.loadImage("icon.png"));
                timetable.setLocationRelativeTo(null);
                timetable.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                timetable.setVisible(true);
            }
        });
    }
}
