/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarysystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author paari
 */
public class User {

    protected String userName;
    protected String password;
    protected int idnum;
    protected UserType userType;
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public User() {
        this.userName = null;
        this.password = null;
        this.idnum = 0;
        this.userType = UserType.STUDENT;
    }

    public User(String userName, String password, int idNum, UserType userType) {
        this.userName = userName;
        this.password = password;
        this.idnum = idNum;
        this.userType = userType;
    }

    public void setusername(String username) {
        this.userName = username;
    }

    public void setpassword(String password) {
        this.password = password;
    }

    public void setidnum(int idnum) {
        this.idnum = idnum;
    }

    public String getusername() {
        return this.userName;
    }

    public String getpassword() {
        return this.password;
    }

    public int getidnum() {
        return this.idnum;
    }

    public User save() throws Exception {
        try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.jdbc.Driver");
            // Setup the connection with the DB
            connect = DriverManager
                    .getConnection(Global.MySQLConnectionString);

            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();

            // PreparedStatements can use variables and are more efficient
            preparedStatement = connect
                    .prepareStatement("insert into  library.Users values (default, ?, ?, ?)");
            // "myuser, webpage, datum, summary, COMMENTS from feedback.comments");
            // Parameters start with 1
            preparedStatement.setString(1, this.userName);
            preparedStatement.setString(2, this.password);
            preparedStatement.setInt(3, this.userType.getIndex());
            preparedStatement.executeUpdate();

            User savedUser = read(this.userName);
            this.idnum = savedUser.idnum;
        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }

        return this;
    }

    private void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {

        }
    }

    public User read(String userName) throws Exception {
        try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.jdbc.Driver");
            // Setup the connection with the DB
            connect = DriverManager
                    .getConnection(Global.MySQLConnectionString);

            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();

            // Result set get the result of the SQL query
            resultSet = statement
                    .executeQuery("select * from library.Users where USERNAME=\"" + userName + "\";");
            
            while (resultSet.next()) {
                String password = resultSet.getString("PASSCODE");
                int idNum = resultSet.getInt("idNum");
                UserType userType = UserType.values()[resultSet.getInt("USERTYPE")-1];
                return new User(userName, password, idNum, userType);
            }
            

        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
        return null;
    }
}
