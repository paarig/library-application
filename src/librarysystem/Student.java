/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarysystem;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author paari
 */
public class Student extends User {

    private double fines;

    public Student() {
        super();
        this.fines = 0;
    }

    public Student(String userName, String password) {
        super(userName, password, 0, UserType.STUDENT);
        this.fines = 0;
    }

    public void setfines(int fines) {
        this.fines = fines;
    }

    public void clearfines() {
        this.fines = 0;
    }

    public double getfines() {
        return this.fines;
    }

    public Student saveStudent() {
        User newUser;
        try {
            newUser = super.save();
        } catch (Exception ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }

        return this;
    }

    public Student getStudent(String userName) throws Exception {
        User newUser = super.read(userName);
        if(newUser == null){
            return null;
        }
        Student newStudent = new Student(newUser.getusername(), newUser.getpassword());
        return newStudent;
    }
}
