/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetable.helper;

import java.awt.Image;
import javax.swing.ImageIcon;


public class ResourceLoader {
    
    public static Image loadImage(String imageName) {
        return new ImageIcon(ClassLoader.getSystemResource("timetable/assets/" + imageName)).getImage();
    }
}
