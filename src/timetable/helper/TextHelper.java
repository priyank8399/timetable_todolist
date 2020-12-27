/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetable.helper;


public class TextHelper {
    
    public static boolean isEmpty(String text) {
        return text == null ? true : text.isEmpty();
    }
    
    public static boolean isEqual(String one, String two) {
        return one == null && two == null ? true
                : one != null ? one.equals(two) : false ? one.equalsIgnoreCase(two)  : false;
    }
}
