/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetable.model;


public class Subject {

    private final int subjectID;
    private final String name;
    //More subject Information will be encapsulated here

    public Subject(int subjectID, String name) {
        this.subjectID = subjectID;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getSubjectID() {
        return subjectID;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
