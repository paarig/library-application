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
import java.util.ArrayList;
import java.util.Calendar;
import static java.util.Calendar.MONTH;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author paari
 */
public class LibraryItem {

    protected String title;
    protected long isbn;
    protected int year;
    protected int count;
    protected String publisher;
    protected String author;
    protected Date dueDate;
    public final SimpleStringProperty titleProperty;
    public final SimpleLongProperty isbnProperty;
    public final SimpleIntegerProperty yearProperty;
    public final SimpleIntegerProperty countProperty;
    public final SimpleStringProperty publisherProperty;
    public final SimpleStringProperty authorProperty;
    public final SimpleStringProperty dueDateProperty;
    protected LibraryItemType libraryItemType;
    private Queue<Integer> holdq;
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public LibraryItem() {
        title = "";
        isbn = 0;
        year = 0;
        count = 1;
        publisher = "";
        dueDate = new Date();
        holdq = new LinkedList<>();
        titleProperty = new SimpleStringProperty(title);
        isbnProperty = new SimpleLongProperty(isbn);
        yearProperty = new SimpleIntegerProperty(year);
        countProperty = new SimpleIntegerProperty(count);
        publisherProperty = new SimpleStringProperty(publisher);
        authorProperty = new SimpleStringProperty(author);
        dueDateProperty = new SimpleStringProperty(dueDate.toString());
    }

    public LibraryItem(String title, long isbn, int year, String publisher, int count, String author, LibraryItemType libraryItemType) throws Exception {
        this.title = title;
        this.isbn = isbn;
        this.year = year;
        this.publisher = publisher;
        this.count = count;
        this.author = author;
        this.libraryItemType = libraryItemType;
        dueDate = new Date();
        titleProperty = new SimpleStringProperty(title);
        isbnProperty = new SimpleLongProperty(isbn);
        yearProperty = new SimpleIntegerProperty(year);
        countProperty = new SimpleIntegerProperty(count);
        publisherProperty = new SimpleStringProperty(publisher);
        authorProperty = new SimpleStringProperty(author);
        dueDateProperty = new SimpleStringProperty(dueDate.toString());        
        holdq = new LinkedList<>();
        readFromHold();
    }

    public void setTitleProperty(String title) {
        titleProperty.set(title);
    }

    public String getTitleProperty() {
        return titleProperty.get();
    }

    public void setIsbnProperty(long isbn) {
        isbnProperty.set(isbn);
    }

    public Long getIsbnProperty() {
        return isbnProperty.get();
    }

    public void setYearProperty(int year) {
        yearProperty.set(year);
    }

    public int getYearProperty() {
        return yearProperty.get();
    }

    public void setCountProperty(int count) {
        countProperty.set(count);
    }

    public int getCountProperty() {
        return countProperty.get();
    }

    public void setPublisherProperty(String publisher) {
        publisherProperty.set(publisher);
    }

    public String getPublisherProperty() {
        return publisherProperty.get();
    }

    public void setAuthorProperty(String author) {
        authorProperty.set(author);
    }

    public String getAuthorProperty() {
        return authorProperty.get();
    }
    
    public void setDueDateProperty(Date dueDate) {
        this.dueDate = dueDate;
        dueDateProperty.set(dueDate.toString());
    }
    
    public String getDueDateProperty() {
        return dueDateProperty.get();
    }

    public void settitle(String title) {
        this.title = title;
    }

    public void setisbn(long isbn) {
        this.isbn = isbn;
    }

    public void setyear(int year) {
        this.year = year;
    }

    public void setcount(int count) {
        this.count = count;
    }

    public void setpublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setauthor(String author) {
        this.author = author;
    }

    public String gettitle() {
        return this.title;
    }

    public long getisbn() {
        return this.isbn;
    }

    public int getyear() {
        return this.year;
    }

    public int getcount() {
        return this.count;
    }

    public String getauthor() {
        return this.author;
    }

    public String getpublisher() {
        return this.publisher;
    }

    public void addHold(int idnum) throws Exception {
        addHoldToDB(idnum);
        holdq.add(idnum);
    }

    public void removeHold(int idnum) throws Exception {
        removeHoldFromDB(idnum);
        Queue<Integer> temp = new LinkedList<>();
        while (!holdq.isEmpty()) {
            if (holdq.peek().equals(idnum)) {
                holdq.poll();
            } else {
                temp.add(holdq.poll());
            }
        }
        while (!temp.isEmpty()) {
            holdq.add(temp.poll());
        }
    }

    public int nexthold() {
        return holdq.poll();
    }

    public boolean holdsareempty() {
        return holdq.isEmpty();
    }

    public int placeInQueue(int userID) {
        int count = 0;
        int place = 0;
        Queue<Integer> temp = new LinkedList<>();
        while (!holdq.isEmpty()) {
            count++;
            if (holdq.peek().equals(userID)) {
                place = count;
            }
            temp.add(holdq.poll());
        }
        while (!temp.isEmpty()) {
            holdq.add(temp.poll());
        }
        return place;
    }

    public LibraryItem save() throws Exception {
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
                    .prepareStatement("insert into  library.LibraryItems values (?, ?, ?, ?, ?, ?, ?)");
            // "myuser, webpage, datum, summary, COMMENTS from feedback.comments");
            // Parameters start with 1
            preparedStatement.setLong(1, this.isbn);
            preparedStatement.setString(2, this.title);
            preparedStatement.setInt(3, this.year);
            preparedStatement.setInt(4, this.count);
            preparedStatement.setString(5, this.publisher);
            preparedStatement.setString(6, this.author);
            preparedStatement.setInt(7, this.libraryItemType.getIndex());
            preparedStatement.executeUpdate();
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

    public LibraryItem read(long isbn) throws Exception {
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
                    .executeQuery("select * from library.LibraryItems where ISBN=\"" + isbn + "\";");

            while (resultSet.next()) {
                String title = resultSet.getString("TITLE");
                int year = resultSet.getInt("PUBLICATIONYEAR");
                int count = resultSet.getInt("ITEMCOUNT");
                String publisher = resultSet.getString("PUBLISHER");
                String author = resultSet.getString("AUTHOR");
                LibraryItemType libraryItemType = LibraryItemType.values()[resultSet.getInt("LIBRARYITEMTYPE") - 1];

                return new LibraryItem(title, isbn, year, publisher, count, author, libraryItemType);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
        return null;
    }

    public int availableCopiesCount() throws Exception {
        return count - checkedCopiesCount();
    }

    public int checkedCopiesCount() throws Exception {
        int checkedCopies = 0;
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
                    .executeQuery("select count(*) as CHECKEDCOPIES from library.Checkout where ISBN=\"" + isbn + "\";");

            while (resultSet.next()) {
                checkedCopies = resultSet.getInt("CHECKEDCOPIES");
            }

        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
        return checkedCopies;
    }

    public void addCheckout(int userID) throws Exception {
        boolean isUserInQueue = placeInQueue(userID) > 0;
        if (availableCopiesCount() <= 0) {
            throw new Exception("No copies available");
        }

        if (isUserInQueue && placeInQueue(userID) > availableCopiesCount()) {
            throw new Exception("No copies available");
        }
        if (!isUserInQueue && availableCopiesCount() <= holdq.size()) {
            throw new Exception("No copies available");
        }

        try {
            Calendar cal = Calendar.getInstance();
            Date checkout = cal.getTime();
            cal.set(MONTH, cal.get(MONTH) + 1);
            Date turnin = cal.getTime();
            java.sql.Date checkoutDate = new java.sql.Date(checkout.getTime());
            java.sql.Date turninDate = new java.sql.Date(turnin.getTime());
            
            dueDate = turninDate;
            setDueDateProperty(dueDate);

            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.jdbc.Driver");
            // Setup the connection with the DB
            connect = DriverManager
                    .getConnection(Global.MySQLConnectionString);

            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();

            // PreparedStatements can use variables and are more efficient
            preparedStatement = connect
                    .prepareStatement("insert into  library.Checkout values (?, ?, ?, ?)");
            // "myuser, webpage, datum, summary, COMMENTS from feedback.comments");
            // Parameters start with 1
            preparedStatement.setInt(1, userID);
            preparedStatement.setLong(2, isbn);
            preparedStatement.setDate(3, checkoutDate);
            preparedStatement.setDate(4, turninDate);
            preparedStatement.executeUpdate();
            if (isUserInQueue) {
                removeHold(userID);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }

    }

    public void removeCheckout(int userID) throws Exception {
        try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.jdbc.Driver");
            // Setup the connection with the DB
            connect = DriverManager
                    .getConnection(Global.MySQLConnectionString);

            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();

            // PreparedStatements can use variables and are more efficient
            // Parameters start with 1
            preparedStatement = connect
                    .prepareStatement("delete from library.Checkout where USERID= ? and ISBN = ? ; ");
            preparedStatement.setInt(1, userID);
            preparedStatement.setLong(2, isbn);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
    }

    public void addHoldToDB(int userID) throws Exception {
        try {
            java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.jdbc.Driver");
            // Setup the connection with the DB
            connect = DriverManager
                    .getConnection(Global.MySQLConnectionString);

            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();

            // PreparedStatements can use variables and are more efficient
            preparedStatement = connect
                    .prepareStatement("insert into  library.Hold values (?, ?, ?)");
            // "myuser, webpage, datum, summary, COMMENTS from feedback.comments");
            // Parameters start with 1
            preparedStatement.setInt(1, userID);
            preparedStatement.setLong(2, isbn);
            preparedStatement.setTimestamp(3, date);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
    }

    public void readFromHold() throws Exception {
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
                    .executeQuery("select * from library.Hold where ISBN=\"" + isbn + "\" order by HOLDDATE;");

            while (resultSet.next()) {
                holdq.add(resultSet.getInt("USERID"));
            }

        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
    }

    public void removeHoldFromDB(int userID) throws Exception {
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
                    .prepareStatement("delete from library.Hold where USERID= ? and ISBN = ? ; ");
            preparedStatement.setInt(1, userID);
            preparedStatement.setLong(2, isbn);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
    }

    public ArrayList<LibraryItem> checkedOutItems(int userID) throws Exception {
        ArrayList<LibraryItem> checkedOut = new ArrayList<>();
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
                    .executeQuery("select l.*, c.DUEDATE from library.Checkout c join library.LibraryItems l on c.ISBN = l.ISBN where USERID=\"" + userID + "\";");

            while (resultSet.next()) {
                String title = resultSet.getString("TITLE");
                int year = resultSet.getInt("PUBLICATIONYEAR");
                int count = resultSet.getInt("ITEMCOUNT");
                String publisher = resultSet.getString("PUBLISHER");
                String author = resultSet.getString("AUTHOR");
                LibraryItemType libraryItemType = LibraryItemType.values()[resultSet.getInt("LIBRARYITEMTYPE") - 1];

                LibraryItem l = new LibraryItem(title, isbn, year, publisher, count, author, libraryItemType);
                l.setDueDateProperty(resultSet.getDate("DUEDATE"));
                checkedOut.add(l);
            }
            return checkedOut;

        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
    }
    
    public ArrayList<LibraryItem> holdItems(int userID) throws Exception {
        ArrayList<LibraryItem> hold = new ArrayList<>();
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
                    .executeQuery("select h.* from library.Checkout c join library.Hold h on c.ISBN = h.ISBN where c.USERID=\"" + userID + "\";");

            while (resultSet.next()) {
                String title = resultSet.getString("TITLE");
                int year = resultSet.getInt("PUBLICATIONYEAR");
                int count = resultSet.getInt("ITEMCOUNT");
                String publisher = resultSet.getString("PUBLISHER");
                String author = resultSet.getString("AUTHOR");
                LibraryItemType libraryItemType = LibraryItemType.values()[resultSet.getInt("LIBRARYITEMTYPE") - 1];

                hold.add(new LibraryItem(title, isbn, year, publisher, count, author, libraryItemType));
            }
            return hold;

        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
    }    

    public ArrayList<LibraryItem> searchByTitle(String search, LibraryItemType itemType) throws Exception {
        ArrayList<LibraryItem> libraryItems = new ArrayList<>();
        try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.jdbc.Driver");
            // Setup the connection with the DB
            connect = DriverManager
                    .getConnection(Global.MySQLConnectionString);

            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();

            preparedStatement = connect
                    .prepareStatement("select * from library.LibraryItems where TITLE like ? and LIBRARYITEMTYPE = ? ; ");
            preparedStatement.setString(1, "%" + search + "%");
            preparedStatement.setInt(2, itemType.getIndex());
            resultSet = preparedStatement.executeQuery();
            // Result set get the result of the SQL query

            while (resultSet.next()) {
                String title = resultSet.getString("TITLE");
                long isbn = resultSet.getLong("ISBN");
                int year = resultSet.getInt("PUBLICATIONYEAR");
                int count = resultSet.getInt("ITEMCOUNT");
                String publisher = resultSet.getString("PUBLISHER");
                String author = resultSet.getString("AUTHOR");
                LibraryItemType libraryItemType = LibraryItemType.values()[resultSet.getInt("LIBRARYITEMTYPE") - 1];
                libraryItems.add(new LibraryItem(title, isbn, year, publisher, count, author, libraryItemType));
            }

        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
        return libraryItems;
    }

    public ArrayList<LibraryItem> searchByISBN(long ISBN, LibraryItemType itemType) throws Exception {
        ArrayList<LibraryItem> libraryItems = new ArrayList<>();
        try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.jdbc.Driver");
            // Setup the connection with the DB
            connect = DriverManager
                    .getConnection(Global.MySQLConnectionString);

            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();

            preparedStatement = connect
                    .prepareStatement("select * from library.LibraryItems where ISBN = ? and LIBRARYITEMTYPE = ? ; ");
            preparedStatement.setLong(1, ISBN);
            preparedStatement.setInt(2, itemType.getIndex());
            resultSet = preparedStatement.executeQuery();
            // Result set get the result of the SQL query

            while (resultSet.next()) {
                String title = resultSet.getString("TITLE");
                long isbn = resultSet.getLong("ISBN");
                int year = resultSet.getInt("PUBLICATIONYEAR");
                int count = resultSet.getInt("ITEMCOUNT");
                String publisher = resultSet.getString("PUBLISHER");
                String author = resultSet.getString("AUTHOR");
                LibraryItemType libraryItemType = LibraryItemType.values()[resultSet.getInt("LIBRARYITEMTYPE") - 1];
                libraryItems.add(new LibraryItem(title, isbn, year, publisher, count, author, libraryItemType));
            }

        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
        return libraryItems;
    }

    public ArrayList<LibraryItem> searchByPublisher(String search, LibraryItemType itemType) throws Exception {
        ArrayList<LibraryItem> libraryItems = new ArrayList<>();
        try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.jdbc.Driver");
            // Setup the connection with the DB
            connect = DriverManager
                    .getConnection(Global.MySQLConnectionString);

            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();

            preparedStatement = connect
                    .prepareStatement("select * from library.LibraryItems where PUBLISHER like ? and LIBRARYITEMTYPE = ? ; ");
            preparedStatement.setString(1, "%" + search + "%");
            preparedStatement.setInt(2, itemType.getIndex());
            resultSet = preparedStatement.executeQuery();
            // Result set get the result of the SQL query

            while (resultSet.next()) {
                String title = resultSet.getString("TITLE");
                long isbn = resultSet.getLong("ISBN");
                int year = resultSet.getInt("PUBLICATIONYEAR");
                int count = resultSet.getInt("ITEMCOUNT");
                String publisher = resultSet.getString("PUBLISHER");
                String author = resultSet.getString("AUTHOR");
                LibraryItemType libraryItemType = LibraryItemType.values()[resultSet.getInt("LIBRARYITEMTYPE") - 1];
                libraryItems.add(new LibraryItem(title, isbn, year, publisher, count, author, libraryItemType));
            }

        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
        return libraryItems;
    }

    public ArrayList<LibraryItem> searchByAuthor(String search, LibraryItemType itemType) throws Exception {
        ArrayList<LibraryItem> libraryItems = new ArrayList<>();
        try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.jdbc.Driver");
            // Setup the connection with the DB
            connect = DriverManager
                    .getConnection(Global.MySQLConnectionString);

            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();

            preparedStatement = connect
                    .prepareStatement("select * from library.LibraryItems where AUTHOR like ? and LIBRARYITEMTYPE = ? ; ");
            preparedStatement.setString(1, "%" + search + "%");
            preparedStatement.setInt(2, itemType.getIndex());
            resultSet = preparedStatement.executeQuery();
            // Result set get the result of the SQL query

            while (resultSet.next()) {
                String title = resultSet.getString("TITLE");
                long isbn = resultSet.getLong("ISBN");
                int year = resultSet.getInt("PUBLICATIONYEAR");
                int count = resultSet.getInt("ITEMCOUNT");
                String publisher = resultSet.getString("PUBLISHER");
                String author = resultSet.getString("AUTHOR");
                LibraryItemType libraryItemType = LibraryItemType.values()[resultSet.getInt("LIBRARYITEMTYPE") - 1];
                libraryItems.add(new LibraryItem(title, isbn, year, publisher, count, author, libraryItemType));
            }

        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
        return libraryItems;
    }
}
