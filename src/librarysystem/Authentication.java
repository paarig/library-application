/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarysystem;

/**
 *
 * @author paari
 */
public class Authentication {

    public Student studentSignup(String username, String password) throws Exception {
        Student newStudent = new Student();
        newStudent = newStudent.getStudent(username);
        if (newStudent != null) {
            return null;
        } else {
            newStudent = new Student(username, password);
            return newStudent.saveStudent();
        }
    }

    public Admin adminSignup(String username, String password) throws Exception {
        Admin newAdmin = new Admin();
        newAdmin = newAdmin.getAdmin(username);
        if (newAdmin != null) {
            return null;
        } else {
            newAdmin = new Admin(username, password);
            return newAdmin.saveAdmin();
        }
    }

    public Student studentSignin(String username, String password) throws Exception {
        Student newStudent = new Student();
        newStudent = newStudent.getStudent(username);
        if (newStudent.getStudent(username) == null || !(password.equals(newStudent.getpassword()))) {
            return null;
        } else {
            return newStudent;
        }
    }

    public Admin adminSignin(String username, String password) throws Exception {
        Admin newAdmin = new Admin();
        newAdmin = newAdmin.getAdmin(username);
        if (newAdmin.getAdmin(username) == null || !(password.equals(newAdmin.getpassword()))) {
            return null;
        } else {          
            return newAdmin;
        }
    }
}
