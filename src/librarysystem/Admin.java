/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarysystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author paari
 */
public class Admin extends User{

    public Admin() {
        super();
    }

    public Admin(String username, String password) {
        super(username, password, 0, UserType.ADMIN);
    }
    
    public Admin saveAdmin() {
        User newUser;
        try {
            newUser = super.save();
        } catch (Exception ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }

        return this;
    }   
    
    public Admin getAdmin(String userName) throws Exception {
        User newUser = super.read(userName);
        if(newUser == null){
            return null;
        }
        Admin newAdmin = new Admin(newUser.getusername(), newUser.getpassword());
        return newAdmin;
    }    

}
