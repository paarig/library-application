/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarysystem;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author paari
 */
public class LibrarySystemAppGUI extends Application {

    Authentication authentication = new Authentication();
    Student currentStudent = new Student();
    Admin currentAdmin = new Admin();
    AudioBook currentAudioBook = new AudioBook();
    Book currentBook = new Book();
    Dvd currentDVD = new Dvd();
    Ebook currentEBook = new Ebook();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Paari's Library Application");
        homePage(primaryStage);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public void homePage(Stage stage) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Scene scene = new Scene(grid, 600, 550);
        stage.setScene(scene);

        Label welcome = new Label("Log in or sign up below to get started");
        grid.add(welcome, 0, 1);

        Label newUser = new Label("Not a user yet?");
        grid.add(newUser, 0, 2);

        Button createNewUser = new Button("Sign Up");
        grid.add(createNewUser, 1, 2);

        createNewUser.setOnAction((event) -> {
            createUser(stage);
        });

        Label choiceBox = new Label("Type of User:");
        grid.add(choiceBox, 0, 3);

        ChoiceBox cb = new ChoiceBox(FXCollections.observableArrayList(
                "Student", "Admin")
        );
        grid.add(cb, 1, 3);

        Label userName = new Label("User Name:");
        grid.add(userName, 0, 4);

        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 4);

        Label passWord = new Label("Password:");
        grid.add(passWord, 0, 5);

        PasswordField passWordField = new PasswordField();
        grid.add(passWordField, 1, 5);

        Button confirm = new Button("Log In");
        grid.add(confirm, 0, 6);

        confirm.setOnAction((ActionEvent event) -> {
            try {
                if (cb.getValue().toString().equals("Student")) {
                    currentStudent = authentication.studentSignin(userTextField.getText(), passWordField.getText());
                    studentHomepage(stage);
                } else if (cb.getValue().toString().equals("Admin")) {
                    currentAdmin = authentication.adminSignin(userTextField.getText(), passWordField.getText());
                    adminHomepage(stage);
                } else {
                    failedLogin(stage);
                }
            } catch (Exception ex) {
                failedLogin(stage);
            }
        });

    }

    public void createUser(Stage stage) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Scene scene = new Scene(grid, 600, 550);
        stage.setScene(scene);

        Label choiceBox = new Label("Type of User:");
        grid.add(choiceBox, 0, 1);

        ChoiceBox cb = new ChoiceBox(FXCollections.observableArrayList(
                "Student", "Admin")
        );
        grid.add(cb, 1, 1);

        Label userName = new Label("User Name:");
        grid.add(userName, 0, 2);

        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 2);

        Label passWord = new Label("Password:");
        grid.add(passWord, 0, 3);

        PasswordField passWordField = new PasswordField();
        grid.add(passWordField, 1, 3);

        Label confirmPassWord = new Label("Confirm Password:");
        grid.add(confirmPassWord, 0, 4);

        PasswordField confirmPassWordField = new PasswordField();
        grid.add(confirmPassWordField, 1, 4);

        Button confirm = new Button("Sign Up");
        grid.add(confirm, 0, 5);

        confirm.setOnAction((ActionEvent event) -> {
            try {
                if (cb.getValue().toString().equals("Student") && passWordField.getText().equals(confirmPassWordField.getText())) {
                    Student temp = authentication.studentSignup(userTextField.getText(), passWordField.getText());
                    successfulSignup(stage);
                } else if (cb.getValue().toString().equals("Admin") && passWordField.getText().equals(confirmPassWordField.getText())) {
                    Admin temp = new Admin();
                    temp = authentication.adminSignup(userTextField.getText(), passWordField.getText());
                    successfulSignup(stage);
                }
            } catch (Exception ex) {
                failedSignup(stage);
            }
        });

    }

    public void failedLogin(Stage stage) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Scene scene = new Scene(grid, 600, 550);
        stage.setScene(scene);

        Label welcome = new Label("Log in or sign up below to get started");
        grid.add(welcome, 0, 1);

        Label newUser = new Label("Not a user yet?");
        grid.add(newUser, 0, 2);

        Button createNewUser = new Button("Sign Up");
        grid.add(createNewUser, 1, 2);

        createNewUser.setOnAction((event) -> {
            createUser(stage);
        });

        Label choiceBox = new Label("Type of User:");
        grid.add(choiceBox, 0, 3);

        ChoiceBox cb = new ChoiceBox(FXCollections.observableArrayList(
                "Student", "Admin")
        );
        grid.add(cb, 1, 3);

        Label userName = new Label("User Name:");
        grid.add(userName, 0, 4);

        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 4);

        Label passWord = new Label("Password:");
        grid.add(passWord, 0, 5);

        PasswordField passWordField = new PasswordField();
        grid.add(passWordField, 1, 5);

        Button confirm = new Button("Log In");
        grid.add(confirm, 0, 6);

        confirm.setOnAction((event) -> {
            try {
                if (cb.getValue().toString().equals("Student")) {
                    currentStudent = authentication.studentSignin(userTextField.getText(), passWordField.getText());
                    studentHomepage(stage);
                } else if (cb.getValue().toString().equals("Admin")) {
                    currentAdmin = authentication.adminSignin(userTextField.getText(), passWordField.getText());
                    adminHomepage(stage);
                } else {
                    failedLogin(stage);
                }
            } catch (Exception ex) {
                failedLogin(stage);
            }
        });

        Text fail = new Text("Incorrect user type, password, or username.");
        fail.setFill(Color.RED);
        grid.add(fail, 0, 7);

    }

    public void failedSignup(Stage stage) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Scene scene = new Scene(grid, 600, 550);
        stage.setScene(scene);

        Label choiceBox = new Label("Type of User:");
        grid.add(choiceBox, 0, 1);

        ChoiceBox cb = new ChoiceBox(FXCollections.observableArrayList(
                "Student", "Admin")
        );
        grid.add(cb, 1, 1);

        Label userName = new Label("User Name:");
        grid.add(userName, 0, 2);

        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 2);

        Label passWord = new Label("Password:");
        grid.add(passWord, 0, 3);

        PasswordField passWordField = new PasswordField();
        grid.add(passWordField, 1, 3);

        Label confirmPassWord = new Label("Confirm Password:");
        grid.add(confirmPassWord, 0, 4);

        PasswordField confirmPassWordField = new PasswordField();
        grid.add(confirmPassWordField, 1, 4);

        Button confirm = new Button("Sign Up");
        grid.add(confirm, 0, 5);

        confirm.setOnAction((event) -> {
            try {
                if (cb.getValue().toString().equals("Student") && passWordField.getText().equals(confirmPassWordField.getText())) {
                    Student temp = authentication.studentSignup(userTextField.getText(), passWordField.getText());
                    successfulSignup(stage);
                } else if (cb.getValue().toString().equals("Admin") && passWordField.getText().equals(confirmPassWordField.getText())) {
                    Admin temp = authentication.adminSignup(userTextField.getText(), passWordField.getText());
                    successfulSignup(stage);
                } else {
                    failedSignup(stage);
                }
            } catch (Exception ex) {
                failedSignup(stage);
            }
        });

        Text fail = new Text("Password has already been used or not all info is filled. Please try again");
        fail.setFill(Color.RED);
        grid.add(fail, 0, 6);

    }

    public void successfulSignup(Stage stage) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Scene scene = new Scene(grid, 600, 550);
        stage.setScene(scene);

        Label welcome = new Label("Log in or sign up below to get started");
        grid.add(welcome, 0, 1);

        Label newUser = new Label("Not a user yet?");
        grid.add(newUser, 0, 2);

        Button createNewUser = new Button("Sign Up");
        grid.add(createNewUser, 1, 2);

        createNewUser.setOnAction((event) -> {
            createUser(stage);
        });

        Label choiceBox = new Label("Type of User:");
        grid.add(choiceBox, 0, 3);

        ChoiceBox cb = new ChoiceBox(FXCollections.observableArrayList(
                "Student", "Admin")
        );
        grid.add(cb, 1, 3);

        Label userName = new Label("User Name:");
        grid.add(userName, 0, 4);

        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 4);

        Label passWord = new Label("Password:");
        grid.add(passWord, 0, 5);

        PasswordField passWordField = new PasswordField();
        grid.add(passWordField, 1, 5);

        Button confirm = new Button("Log In");
        grid.add(confirm, 0, 6);

        confirm.setOnAction((event) -> {
            try {
                if (cb.getValue().toString().equals("Student")) {
                    currentStudent = authentication.studentSignin(userTextField.getText(), passWordField.getText());
                    studentHomepage(stage);
                } else if (cb.getValue().toString().equals("Admin")) {
                    currentAdmin = authentication.adminSignin(userTextField.getText(), passWordField.getText());
                    adminHomepage(stage);
                } else {
                    failedLogin(stage);
                }
            } catch (Exception ex) {
                failedLogin(stage);
            }
        });
        Text success = new Text("Your account was successfully created.");
        success.setFill(Color.GREEN);
        grid.add(success, 0, 7);
    }

    public void adminHomepage(Stage stage) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Scene scene = new Scene(grid, 600, 550);
        stage.setScene(scene);

        Text welcome = new Text("Welcome " + currentAdmin.getusername() + "! Pick an action below to get started.");
        grid.add(welcome, 0, 1);

        ChoiceBox itemType = new ChoiceBox(FXCollections.observableArrayList(
                "Add Item")
        );
        grid.add(itemType, 0, 2);

        Button go = new Button("Perform Action");
        grid.add(go, 0, 3);

        go.setOnAction((ActionEvent event) -> {
            if (itemType.getValue().toString().equals("Add Item")) {
                addItem(stage);
            } else {
                failedAdminHomepage(stage);
            }
        });

    }

    public void failedAdminHomepage(Stage stage) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Scene scene = new Scene(grid, 600, 550);
        stage.setScene(scene);

        Text welcome = new Text("Welcome " + currentAdmin.getusername() + "! Pick an action below to get started.");
        grid.add(welcome, 0, 1);

        ChoiceBox itemType = new ChoiceBox(FXCollections.observableArrayList(
                "Add Item")
        );
        grid.add(itemType, 0, 2);

        Button go = new Button("Perform Action");
        grid.add(go, 0, 3);

        go.setOnAction((ActionEvent event) -> {
            if (itemType.getValue().toString().equals("Add Item")) {
                addItem(stage);
            } else if (itemType.getValue().toString().equals("Search Catalog")) {
                searchItems(stage);
            }
        });

        Text fail = new Text("You made an error. Please try again.");
        fail.setFill(Color.RED);
        grid.add(fail, 0, 4);
    }

    public void studentHomepage(Stage stage) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Scene scene = new Scene(grid, 600, 550);
        stage.setScene(scene);

        Text welcome = new Text("Welcome " + currentStudent.getusername() + "! Pick an action below to get started.");
        grid.add(welcome, 0, 1);

        ChoiceBox itemType = new ChoiceBox(FXCollections.observableArrayList(
                "Checked Out Items", "Search Catalog", "Check Out Item", "Return Item", "Place Hold", "Placed Holds")
        );
        grid.add(itemType, 0, 2);

        Button go = new Button("Perform Action");
        grid.add(go, 0, 3);

        go.setOnAction((ActionEvent event) -> {
            if (itemType.getValue().toString().equals("Checked Out Items")) {
                try {
                    checkedOutItems(stage);
                } catch (Exception ex) {
                    Logger.getLogger(LibrarySystemAppGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (itemType.getValue().toString().equals("Search Catalog")) {
                searchItems(stage);
            } else if (itemType.getValue().toString().equals("Check Out Item")) {
                checkOutItem(stage);
            } else if (itemType.getValue().toString().equals("Return Item")) {
                checkInItem(stage);
            } else if (itemType.getValue().toString().equals("Place Hold")) {
                placeHold(stage);
            } else if (itemType.getValue().toString().equals("Placed Holds")) {
                try {
                    holdItems(stage);
                } catch (Exception ex) {
                    Logger.getLogger(LibrarySystemAppGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                failedStudentHomepage(stage);
            }
        });

    }

    public void failedStudentHomepage(Stage stage) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Scene scene = new Scene(grid, 600, 550);
        stage.setScene(scene);

        Text welcome = new Text("Welcome " + currentStudent.getusername() + "! Pick an action below to get started.");
        grid.add(welcome, 0, 1);

        ChoiceBox itemType = new ChoiceBox(FXCollections.observableArrayList(
                "Checked Out Items", "Search Catalog", "Check Out Item", "Check In Item", "Place Hold")
        );
        grid.add(itemType, 0, 2);

        Button go = new Button("Perform Action");
        grid.add(go, 0, 3);

        Text fail = new Text("You didn't pick an action! Please try again");
        fail.setFill(Color.RED);
        grid.add(fail, 0, 4);

        go.setOnAction((ActionEvent event) -> {
            if (itemType.getValue().toString().equals("Checked Out Items")) {
                try {
                    checkedOutItems(stage);
                } catch (Exception ex) {
                    Logger.getLogger(LibrarySystemAppGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (itemType.getValue().toString().equals("Search Catalog")) {
                searchItems(stage);
            } else if (itemType.getValue().toString().equals("Check Out Item")) {
                checkOutItem(stage);
            } else if (itemType.getValue().toString().equals("Check In Item")) {
                checkInItem(stage);
            } else if (itemType.getValue().toString().equals("Place Hold")) {
                placeHold(stage);
            } else if (itemType.getValue().toString().equals("Placed Holds")) {
                try {
                    holdItems(stage);
                } catch (Exception ex) {
                    Logger.getLogger(LibrarySystemAppGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                failedStudentHomepage(stage);
            }
        });

    }

    public void checkedOutItems(Stage stage) throws Exception {
        TableView<LibraryItem> table = new TableView<>();

        LibraryItem temp = new LibraryItem();

        ArrayList<LibraryItem> itemlist = temp.checkedOutItems(currentStudent.getidnum());

        ObservableList<LibraryItem> items = FXCollections.observableArrayList(itemlist);

        Scene scene = new Scene(new Group());

        stage.setWidth(600);
        stage.setHeight(550);

        final Label label = new Label("Checked Out Books");
        label.setFont(new Font("Arial", 20));

        table.setEditable(true);

        TableColumn titleCol = new TableColumn("Title");
        titleCol.setCellValueFactory(new PropertyValueFactory<>("titleProperty"));
        TableColumn isbnCol = new TableColumn("ISBN");
        isbnCol.setCellValueFactory(new PropertyValueFactory<>("isbnProperty"));
        TableColumn yearCol = new TableColumn("Year");
        yearCol.setCellValueFactory(new PropertyValueFactory<>("yearProperty"));
        TableColumn publisherCol = new TableColumn("Publisher");
        publisherCol.setCellValueFactory(new PropertyValueFactory<>("publisherProperty"));
        TableColumn countCol = new TableColumn("Total Copies");
        countCol.setCellValueFactory(new PropertyValueFactory<>("countProperty"));
        TableColumn authorCol = new TableColumn("Author");
        authorCol.setCellValueFactory(new PropertyValueFactory<>("authorProperty"));
        TableColumn dueDateCol = new TableColumn("Due Date");
        dueDateCol.setCellValueFactory(new PropertyValueFactory<>("dueDateProperty"));

        table.setItems(items);
        table.getColumns().addAll(titleCol, isbnCol, yearCol, publisherCol, countCol, authorCol, dueDateCol);

        Button homepage = new Button("Click here to go to the homepage");

        homepage.setOnAction((ActionEvent event) -> {
            studentHomepage(stage);
        });

        VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table);

        ((Group) scene.getRoot()).getChildren().addAll(vbox, homepage);
        stage.setScene(scene);
        stage.show();
    }

    public void holdItems(Stage stage) throws Exception {
        TableView<LibraryItem> table = new TableView<>();

        LibraryItem temp = new LibraryItem();

        ArrayList<LibraryItem> itemlist = temp.holdItems(currentStudent.getidnum());

        ObservableList<LibraryItem> items = FXCollections.observableArrayList(itemlist);

        Scene scene = new Scene(new Group());

        stage.setWidth(600);
        stage.setHeight(550);

        final Label label = new Label("Holds Placed");
        label.setFont(new Font("Arial", 20));

        table.setEditable(true);

        TableColumn titleCol = new TableColumn("Title");
        titleCol.setCellValueFactory(new PropertyValueFactory<>("titleProperty"));
        TableColumn isbnCol = new TableColumn("ISBN");
        isbnCol.setCellValueFactory(new PropertyValueFactory<>("isbnProperty"));
        TableColumn yearCol = new TableColumn("Year");
        yearCol.setCellValueFactory(new PropertyValueFactory<>("yearProperty"));
        TableColumn publisherCol = new TableColumn("Publisher");
        publisherCol.setCellValueFactory(new PropertyValueFactory<>("publisherProperty"));
        TableColumn countCol = new TableColumn("Total Copies");
        countCol.setCellValueFactory(new PropertyValueFactory<>("countProperty"));
        TableColumn authorCol = new TableColumn("Author");
        authorCol.setCellValueFactory(new PropertyValueFactory<>("authorProperty"));
        if (itemlist == null) {
            table.setItems(items);
        }
        table.getColumns().addAll(titleCol, isbnCol, yearCol, publisherCol, countCol, authorCol);

        Button homepage = new Button("Click here to go to the homepage");

        homepage.setOnAction((ActionEvent event) -> {
            studentHomepage(stage);
        });

        VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table);

        ((Group) scene.getRoot()).getChildren().addAll(vbox, homepage);
        stage.setScene(scene);
        stage.show();
    }

    public void searchItems(Stage stage) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Scene scene = new Scene(grid, 600, 550);
        stage.setScene(scene);

        Text search = new Text("Search for items below by title, ISBN, or author");
        grid.add(search, 0, 1);

        Label itemLabel = new Label("Select the type of item you're looking for.");
        grid.add(itemLabel, 0, 2);

        ChoiceBox itemType = new ChoiceBox(FXCollections.observableArrayList(
                "Audiobook", "Book", "DVD", "Ebook")
        );
        grid.add(itemType, 1, 2);

        Label searchLabel = new Label("Search by ISBN, Title, Publisher, or Author.");
        grid.add(searchLabel, 0, 3);

        ChoiceBox searchType = new ChoiceBox(FXCollections.observableArrayList(
                "ISBN", "Title", "Author", "Publisher")
        );
        grid.add(searchType, 1, 3);

        Label searchFieldLabel = new Label("Type the search query.");
        grid.add(searchFieldLabel, 0, 4);

        TextField searchField = new TextField();
        grid.add(searchField, 1, 4);

        Button confirmSearch = new Button("Search");
        grid.add(confirmSearch, 0, 5);

        confirmSearch.setOnAction((ActionEvent event) -> {
            try {
                if (itemType.getValue().equals("Audiobook")) {
                    if (searchType.getValue().equals("ISBN") && searchField.getText().matches("\\d+")) {
                        AudioBook temp = new AudioBook();
                        searchAudiobook(stage, temp.searchAudiobookByISBN(Long.valueOf(searchField.getText())));
                    } else if (searchType.getValue().equals("Title")) {
                        AudioBook temp = new AudioBook();
                        searchAudiobook(stage, temp.searchAudiobookByTitle(searchField.getText()));
                    } else if (searchType.getValue().equals("Author")) {
                        AudioBook temp = new AudioBook();
                        searchAudiobook(stage, temp.searchAudiobookByAuthor(searchField.getText()));
                    } else if (searchType.getValue().equals("Publisher")) {
                        AudioBook temp = new AudioBook();
                        searchAudiobook(stage, temp.searchAudiobookByPublisher(searchField.getText()));
                    }
                } else if (itemType.getValue().equals("Book")) {
                    if (searchType.getValue().equals("ISBN") && searchField.getText().matches("\\d+")) {
                        Book temp = new Book();
                        searchBook(stage, temp.searchBookByISBN(Long.valueOf(searchField.getText())));
                    } else if (searchType.getValue().equals("Title")) {
                        Book temp = new Book();
                        searchBook(stage, temp.searchBookByTitle(searchField.getText()));
                    } else if (searchType.getValue().equals("Author")) {
                        Book temp = new Book();
                        searchBook(stage, temp.searchBookByAuthor(searchField.getText()));
                    } else if (searchType.getValue().equals("Publisher")) {
                        Book temp = new Book();
                        searchBook(stage, temp.searchBookByPublisher(searchField.getText()));
                    }
                } else if (itemType.getValue().equals("DVD")) {
                    if (searchType.getValue().equals("ISBN") && searchField.getText().matches("\\d+")) {
                        Dvd temp = new Dvd();
                        searchDvd(stage, temp.searchDvdByISBN(Long.valueOf(searchField.getText())));
                    } else if (searchType.getValue().equals("Title")) {
                        Dvd temp = new Dvd();
                        searchDvd(stage, temp.searchDvdByTitle(searchField.getText()));
                    } else if (searchType.getValue().equals("Author")) {
                        Dvd temp = new Dvd();
                        searchDvd(stage, temp.searchDvdByAuthor(searchField.getText()));
                    } else if (searchType.getValue().equals("Publisher")) {
                        Dvd temp = new Dvd();
                        searchDvd(stage, temp.searchDvdByPublisher(searchField.getText()));
                    }
                } else if (itemType.getValue().equals("Ebook")) {
                    if (searchType.getValue().equals("ISBN") && searchField.getText().matches("\\d+")) {
                        Ebook temp = new Ebook();
                        searchEbook(stage, temp.searchEbookByISBN(Long.valueOf(searchField.getText())));
                    } else if (searchType.getValue().equals("Title")) {
                        Ebook temp = new Ebook();
                        searchEbook(stage, temp.searchEbookByTitle(searchField.getText()));
                    } else if (searchType.getValue().equals("Author")) {
                        Ebook temp = new Ebook();
                        searchEbook(stage, temp.searchEbookByAuthor(searchField.getText()));
                    } else if (searchType.getValue().equals("Publisher")) {
                        Ebook temp = new Ebook();
                        searchEbook(stage, temp.searchEbookByPublisher(searchField.getText()));
                    }
                } else {
                    failedSearchItems(stage);
                }

            } catch (Exception ex) {
                failedSearchItems(stage);
            }
        });

    }

    public void failedSearchItems(Stage stage) {

    }

    public void searchAudiobook(Stage stage, ArrayList<AudioBook> itemlist) {
        TableView<AudioBook> table = new TableView<>();

        ObservableList<AudioBook> audioBooks = FXCollections.observableArrayList(itemlist);

        Scene scene = new Scene(new Group());

        stage.setWidth(600);
        stage.setHeight(550);

        final Label label = new Label("Audiobooks");
        label.setFont(new Font("Arial", 20));

        table.setEditable(true);

        TableColumn titleCol = new TableColumn("Title");
        titleCol.setCellValueFactory(new PropertyValueFactory<>("titleProperty"));
        TableColumn isbnCol = new TableColumn("ISBN");
        isbnCol.setCellValueFactory(new PropertyValueFactory<>("isbnProperty"));
        TableColumn yearCol = new TableColumn("Year");
        yearCol.setCellValueFactory(new PropertyValueFactory<>("yearProperty"));
        TableColumn publisherCol = new TableColumn("Publisher");
        publisherCol.setCellValueFactory(new PropertyValueFactory<>("publisherProperty"));
        TableColumn countCol = new TableColumn("Total Copies");
        countCol.setCellValueFactory(new PropertyValueFactory<>("countProperty"));
        TableColumn authorCol = new TableColumn("Author");
        authorCol.setCellValueFactory(new PropertyValueFactory<>("authorProperty"));

        table.setItems(audioBooks);
        table.getColumns().addAll(titleCol, isbnCol, yearCol, publisherCol, countCol, authorCol);

        Button goback = new Button("Click here to search again");

        goback.setOnAction((ActionEvent event) -> {
            searchItems(stage);
        });

        Button homepage = new Button("Click here to go to the homepage");

        homepage.setOnAction((ActionEvent event) -> {
            studentHomepage(stage);
        });

        VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table);

        ((Group) scene.getRoot()).getChildren().addAll(vbox, goback, homepage);
        stage.setScene(scene);
        stage.show();

    }

    public void searchBook(Stage stage, ArrayList<Book> itemlist) {
        TableView<Book> table = new TableView<>();

        ObservableList<Book> Books = FXCollections.observableArrayList(itemlist);

        Scene scene = new Scene(new Group());

        stage.setWidth(600);
        stage.setHeight(550);

        final Label label = new Label("Books");
        label.setFont(new Font("Arial", 20));

        table.setEditable(true);

        TableColumn titleCol = new TableColumn("Title");
        titleCol.setCellValueFactory(new PropertyValueFactory<>("titleProperty"));
        TableColumn isbnCol = new TableColumn("ISBN");
        isbnCol.setCellValueFactory(new PropertyValueFactory<>("isbnProperty"));
        TableColumn yearCol = new TableColumn("Year");
        isbnCol.setCellValueFactory(new PropertyValueFactory<>("yearProperty"));
        TableColumn publisherCol = new TableColumn("Publisher");
        isbnCol.setCellValueFactory(new PropertyValueFactory<>("publisherProperty"));
        TableColumn countCol = new TableColumn("Total Copies");
        isbnCol.setCellValueFactory(new PropertyValueFactory<>("countProperty"));
        TableColumn authorCol = new TableColumn("Author");
        isbnCol.setCellValueFactory(new PropertyValueFactory<>("authorProperty"));

        table.setItems(Books);
        table.getColumns().addAll(titleCol, isbnCol, yearCol, publisherCol, countCol, authorCol);

        Button goback = new Button("Click here to search again");

        goback.setOnAction((ActionEvent event) -> {
            searchItems(stage);
        });

        Button homepage = new Button("Click here to go to the homepage");

        homepage.setOnAction((ActionEvent event) -> {
            studentHomepage(stage);
        });

        VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table);

        ((Group) scene.getRoot()).getChildren().addAll(vbox, goback, homepage);
        stage.setScene(scene);
        stage.show();
    }

    public void searchDvd(Stage stage, ArrayList<Dvd> itemlist) {
        TableView<Dvd> table = new TableView<>();

        ObservableList<Dvd> dvds = FXCollections.observableArrayList(itemlist);

        Scene scene = new Scene(new Group());

        stage.setWidth(600);
        stage.setHeight(550);

        final Label label = new Label("Dvds");
        label.setFont(new Font("Arial", 20));

        table.setEditable(true);

        TableColumn titleCol = new TableColumn("Title");
        titleCol.setCellValueFactory(new PropertyValueFactory<>("titleProperty"));
        TableColumn isbnCol = new TableColumn("ISBN");
        isbnCol.setCellValueFactory(new PropertyValueFactory<>("isbnProperty"));
        TableColumn yearCol = new TableColumn("Year");
        isbnCol.setCellValueFactory(new PropertyValueFactory<>("yearProperty"));
        TableColumn publisherCol = new TableColumn("Publisher");
        isbnCol.setCellValueFactory(new PropertyValueFactory<>("publisherProperty"));
        TableColumn countCol = new TableColumn("Total Copies");
        isbnCol.setCellValueFactory(new PropertyValueFactory<>("countProperty"));
        TableColumn authorCol = new TableColumn("Author");
        isbnCol.setCellValueFactory(new PropertyValueFactory<>("authorProperty"));

        table.setItems(dvds);
        table.getColumns().addAll(titleCol, isbnCol, yearCol, publisherCol, countCol, authorCol);

        Button goback = new Button("Click here to search again");

        goback.setOnAction((ActionEvent event) -> {
            searchItems(stage);
        });

        Button homepage = new Button("Click here to go to the homepage");

        homepage.setOnAction((ActionEvent event) -> {
            studentHomepage(stage);
        });

        VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table);

        ((Group) scene.getRoot()).getChildren().addAll(vbox, goback, homepage);
        stage.setScene(scene);
        stage.show();
    }

    public void searchEbook(Stage stage, ArrayList<Ebook> itemlist) {
        TableView<Ebook> table = new TableView<>();

        ObservableList<Ebook> eBooks = FXCollections.observableArrayList(itemlist);

        Scene scene = new Scene(new Group());

        stage.setWidth(600);
        stage.setHeight(550);

        final Label label = new Label("Ebooks");
        label.setFont(new Font("Arial", 20));

        table.setEditable(true);

        TableColumn titleCol = new TableColumn("Title");
        titleCol.setCellValueFactory(new PropertyValueFactory<>("titleProperty"));
        TableColumn isbnCol = new TableColumn("ISBN");
        isbnCol.setCellValueFactory(new PropertyValueFactory<>("isbnProperty"));
        TableColumn yearCol = new TableColumn("Year");
        isbnCol.setCellValueFactory(new PropertyValueFactory<>("yearProperty"));
        TableColumn publisherCol = new TableColumn("Publisher");
        isbnCol.setCellValueFactory(new PropertyValueFactory<>("publisherProperty"));
        TableColumn countCol = new TableColumn("Total Copies");
        isbnCol.setCellValueFactory(new PropertyValueFactory<>("countProperty"));
        TableColumn authorCol = new TableColumn("Author");
        isbnCol.setCellValueFactory(new PropertyValueFactory<>("authorProperty"));

        table.getColumns().addAll(titleCol, isbnCol, yearCol, publisherCol, countCol, authorCol);

        Button goback = new Button("Click here to search again");

        goback.setOnAction((ActionEvent event) -> {
            searchItems(stage);
        });

        Button homepage = new Button("Click here to go to the homepage");

        homepage.setOnAction((ActionEvent event) -> {
            studentHomepage(stage);
        });

        VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table);

        ((Group) scene.getRoot()).getChildren().addAll(vbox, goback, homepage);
        stage.setScene(scene);
        stage.show();
    }

    public void addItem(Stage stage) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Scene scene = new Scene(grid, 600, 550);
        stage.setScene(scene);

        Label whatType = new Label("What type of item do you want to add to the catalog?");
        grid.add(whatType, 0, 1);

        ChoiceBox itemType = new ChoiceBox(FXCollections.observableArrayList(
                "Audiobook", "Book", "DVD", "Ebook")
        );
        grid.add(itemType, 1, 1);

        Label title = new Label("Title:");
        grid.add(title, 0, 2);

        TextField titleField = new TextField();
        grid.add(titleField, 1, 2);

        Label isbn = new Label("ISBN:");
        grid.add(isbn, 0, 3);

        TextField isbnField = new TextField();
        grid.add(isbnField, 1, 3);

        Label author = new Label("Author:");
        grid.add(author, 0, 4);

        TextField authorField = new TextField();
        grid.add(authorField, 1, 4);

        Label year = new Label("Year:");
        grid.add(year, 0, 5);

        TextField yearField = new TextField();
        grid.add(yearField, 1, 5);

        Label publisher = new Label("Publisher:");
        grid.add(publisher, 0, 6);

        TextField publisherField = new TextField();
        grid.add(publisherField, 1, 6);

        Label count = new Label("Count:");
        grid.add(count, 0, 7);

        TextField countField = new TextField();
        grid.add(countField, 1, 7);

        Button addItem = new Button("Click here to add item");
        grid.add(addItem, 0, 8);

        addItem.setOnAction((ActionEvent event) -> {
            try {
                if (!isbnField.getText().matches("\\d+")) {
                    failedAddItem(stage);
                } else if (!yearField.getText().matches("\\d+")) {
                    failedAddItem(stage);
                } else if (!countField.getText().matches("\\d+")) {
                    failedAddItem(stage);
                } else if (itemType.getValue().toString().equals("Audiobook")) {
                    currentAudioBook = new AudioBook(titleField.getText(), Long.valueOf(isbnField.getText()), Integer.valueOf(yearField.getText()), publisherField.getText(), authorField.getText(), Integer.valueOf(countField.getText()));
                    AudioBook temp = new AudioBook();
                    temp = currentAudioBook.saveAudioBook();
                    successfulAddItem(stage);
                } else if (itemType.getValue().toString().equals("Book")) {
                    currentBook = new Book(titleField.getText(), Long.valueOf(isbnField.getText()), Integer.valueOf(yearField.getText()), publisherField.getText(), authorField.getText(), Integer.valueOf(countField.getText()));
                    Book temp = new Book();
                    temp = currentBook.saveBook();
                    successfulAddItem(stage);
                } else if (itemType.getValue().toString().equals("DVD")) {
                    currentDVD = new Dvd(titleField.getText(), Long.valueOf(isbnField.getText()), Integer.valueOf(yearField.getText()), publisherField.getText(), authorField.getText(), Integer.valueOf(countField.getText()));
                    Dvd temp = new Dvd();
                    temp = currentDVD.saveDvd();
                    successfulAddItem(stage);
                } else if (itemType.getValue().toString().equals("Ebook")) {
                    currentEBook = new Ebook(titleField.getText(), Long.valueOf(isbnField.getText()), Integer.valueOf(yearField.getText()), publisherField.getText(), authorField.getText(), Integer.valueOf(countField.getText()));
                    Ebook temp = new Ebook();
                    temp = currentEBook.saveEbook();
                    successfulAddItem(stage);
                } else {
                    failedAddItem(stage);
                }
            } catch (Exception ex) {
                failedAddItem(stage);
            }
        });

    }

    public void checkOutItem(Stage stage) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Scene scene = new Scene(grid, 600, 550);
        stage.setScene(scene);

        Text search = new Text("Type the ISBN of the item you want to check out.");
        grid.add(search, 0, 1);

        TextField isbn = new TextField();
        grid.add(isbn, 0, 2);

        Button checkout = new Button("Checkout Item");
        grid.add(checkout, 0, 3);

        checkout.setOnAction((ActionEvent event) -> {
            try {
                if (isbn.getText().matches("\\d+")) {
                    LibraryItem temp = new LibraryItem();
                    LibraryItem real = new LibraryItem();
                    real = temp.read(Long.valueOf(isbn.getText()));
                    real.addCheckout(currentStudent.getidnum());
                    checkOutItem(stage);
                } else {
                    checkOutItem(stage);
                }
            } catch (Exception ex) {
                checkOutItem(stage);
            }
        });

        Button returnhome = new Button("Go to homepage");
        grid.add(returnhome, 0, 4);

        returnhome.setOnAction((ActionEvent event) -> {
            studentHomepage(stage);
        });

        Button logout = new Button("Log out");
        grid.add(logout, 0, 5);

        logout.setOnAction((ActionEvent event) -> {

        });

    }

    public void failedCheckOutItem(Stage stage) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Scene scene = new Scene(grid, 600, 550);
        stage.setScene(scene);

        Text search = new Text("Type the ISBN of the item you want to check out.");
        grid.add(search, 0, 1);

        TextField isbn = new TextField();
        grid.add(isbn, 0, 2);

        Button checkout = new Button("Checkout Item");
        grid.add(checkout, 0, 3);

        checkout.setOnAction((ActionEvent event) -> {
            try {
                if (isbn.getText().matches("\\d+")) {
                    LibraryItem temp = new LibraryItem();
                    LibraryItem real = new LibraryItem();
                    real = temp.read(Long.valueOf(isbn.getText()));
                    real.addCheckout(currentStudent.getidnum());
                    successfulCheckOutItem(stage);
                } else {
                    failedCheckOutItem(stage);
                }
            } catch (Exception ex) {
                failedCheckOutItem(stage);
            }
        });

        Button returnhome = new Button("Go to homepage");
        grid.add(returnhome, 0, 4);

        returnhome.setOnAction((ActionEvent event) -> {
            studentHomepage(stage);
        });

        Button logout = new Button("Log out");
        grid.add(logout, 0, 5);

        logout.setOnAction((ActionEvent event) -> {
            homePage(stage);
        });

        Text fail = new Text("Either this item is not available or you typed the incorrect ISBN.");
        fail.setFill(Color.RED);
        grid.add(fail, 0, 6);

        Text fail2 = new Text("Please try again or contact a librarian.");
        fail.setFill(Color.RED);
        grid.add(fail2, 0, 7);
    }

    public void successfulCheckOutItem(Stage stage) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Scene scene = new Scene(grid, 600, 550);
        stage.setScene(scene);

        Text search = new Text("Type the ISBN of the item you want to check out.");
        grid.add(search, 0, 1);

        TextField isbn = new TextField();
        grid.add(search, 0, 2);

        Button checkout = new Button("Checkout Item");
        grid.add(checkout, 0, 3);

        checkout.setOnAction((ActionEvent event) -> {
            try {
                if (isbn.getText().matches("\\d+")) {
                    LibraryItem temp = new LibraryItem();
                    LibraryItem real = new LibraryItem();
                    real = temp.read(Long.valueOf(isbn.getText()));
                    real.addCheckout(currentStudent.getidnum());
                    successfulCheckOutItem(stage);
                } else {
                    failedCheckOutItem(stage);
                }
            } catch (Exception ex) {
                failedCheckOutItem(stage);
            }
        });

        Button returnhome = new Button("Go to homepage");
        grid.add(returnhome, 0, 4);

        returnhome.setOnAction((ActionEvent event) -> {
            studentHomepage(stage);
        });

        Button logout = new Button("Log out");
        grid.add(logout, 0, 5);

        logout.setOnAction((ActionEvent event) -> {
            homePage(stage);
        });

        Text fail = new Text("Your item was successfully checked out.");
        fail.setFill(Color.GREEN);
        grid.add(fail, 0, 6);
    }

    public void failedAddItem(Stage stage) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Scene scene = new Scene(grid, 600, 550);
        stage.setScene(scene);

        Label whatType = new Label("What type of item do you want to add to the catalog?");
        grid.add(whatType, 0, 1);

        ChoiceBox itemType = new ChoiceBox(FXCollections.observableArrayList(
                "Audiobook", "Book", "DVD", "Ebook")
        );
        grid.add(itemType, 1, 1);

        Label title = new Label("Title:");
        grid.add(title, 0, 2);

        TextField titleField = new TextField();
        grid.add(titleField, 1, 2);

        Label isbn = new Label("ISBN:");
        grid.add(isbn, 0, 3);

        TextField isbnField = new TextField();
        grid.add(isbnField, 1, 3);

        Label author = new Label("Author:");
        grid.add(author, 0, 4);

        TextField authorField = new TextField();
        grid.add(authorField, 1, 4);

        Label year = new Label("Year:");
        grid.add(year, 0, 5);

        TextField yearField = new TextField();
        grid.add(yearField, 1, 5);

        Label publisher = new Label("Publisher:");
        grid.add(publisher, 0, 6);

        TextField publisherField = new TextField();
        grid.add(publisherField, 1, 6);

        Label count = new Label("Count:");
        grid.add(count, 0, 7);

        TextField countField = new TextField();
        grid.add(countField, 1, 7);

        Button addItem = new Button("Click here to add item");
        grid.add(addItem, 0, 8);

        addItem.setOnAction((ActionEvent event) -> {
            try {
                if (!isbnField.getText().matches("\\d+")) {
                    failedAddItem(stage);
                } else if (!yearField.getText().matches("\\d+")) {
                    failedAddItem(stage);
                } else if (!countField.getText().matches("\\d+")) {
                    failedAddItem(stage);
                } else if (itemType.getValue().toString().equals("Audiobook")) {
                    currentAudioBook = new AudioBook(titleField.getText(), Long.valueOf(isbnField.getText()), Integer.valueOf(yearField.getText()), publisherField.getText(), authorField.getText(), Integer.valueOf(countField.getText()));
                    AudioBook temp = new AudioBook();
                    temp = currentAudioBook.saveAudioBook();
                    successfulAddItem(stage);
                } else if (itemType.getValue().toString().equals("Book")) {
                    currentBook = new Book(titleField.getText(), Long.valueOf(isbnField.getText()), Integer.valueOf(yearField.getText()), publisherField.getText(), authorField.getText(), Integer.valueOf(countField.getText()));
                    Book temp = new Book();
                    temp = currentBook.saveBook();
                    successfulAddItem(stage);
                } else if (itemType.getValue().toString().equals("DVD")) {
                    currentDVD = new Dvd(titleField.getText(), Long.valueOf(isbnField.getText()), Integer.valueOf(yearField.getText()), publisherField.getText(), authorField.getText(), Integer.valueOf(countField.getText()));
                    Dvd temp = new Dvd();
                    temp = currentDVD.saveDvd();
                    successfulAddItem(stage);
                } else if (itemType.getValue().toString().equals("Ebook")) {
                    currentEBook = new Ebook(titleField.getText(), Long.valueOf(isbnField.getText()), Integer.valueOf(yearField.getText()), publisherField.getText(), authorField.getText(), Integer.valueOf(countField.getText()));
                    Ebook temp = new Ebook();
                    temp = currentEBook.saveEbook();
                    successfulAddItem(stage);
                } else {
                    failedAddItem(stage);
                }
            } catch (Exception ex) {
                failedAddItem(stage);
            }
        });
        Text fail = new Text("You incorrectly inputted some information. Please try again.");
        fail.setFill(Color.RED);
        grid.add(fail, 0, 9);

    }

    public void successfulAddItem(Stage stage) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Scene scene = new Scene(grid, 600, 550);
        stage.setScene(scene);

        Text welcome = new Text("Welcome " + currentAdmin.getusername() + "! Pick an action below to get started.");
        grid.add(welcome, 0, 1);

        ChoiceBox itemType = new ChoiceBox(FXCollections.observableArrayList(
                "Add Item", "Search Catalog")
        );
        grid.add(itemType, 0, 2);

        Button go = new Button("Perform Action");
        grid.add(go, 0, 3);

        go.setOnAction((ActionEvent event) -> {
            if (itemType.getValue().toString().equals("Add Item")) {
                addItem(stage);
            } else if (itemType.getValue().toString().equals("Search Catalog")) {
                searchItems(stage);
            }
        });

        Text fail = new Text("Your action was successfully completed!");
        fail.setFill(Color.GREEN);
        grid.add(fail, 0, 4);

    }

    public void checkInItem(Stage stage) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Scene scene = new Scene(grid, 600, 550);
        stage.setScene(scene);

        Text search = new Text("Type the ISBN of the item you want to return.");
        grid.add(search, 0, 1);

        TextField isbn = new TextField();
        grid.add(isbn, 0, 2);

        Button checkout = new Button("Return Item");
        grid.add(checkout, 0, 3);

        checkout.setOnAction((ActionEvent event) -> {
            try {
                if (isbn.getText().matches("\\d+")) {
                    LibraryItem temp = new LibraryItem();
                    LibraryItem real = new LibraryItem();
                    real = temp.read(Long.valueOf(isbn.getText()));
                    real.removeCheckout(currentStudent.getidnum());
                    successfulCheckInItem(stage);
                } else {
                    failedCheckInItem(stage);
                }
            } catch (Exception ex) {
                failedCheckInItem(stage);
            }
        });

        Button returnhome = new Button("Go to homepage");
        grid.add(returnhome, 0, 4);

        returnhome.setOnAction((ActionEvent event) -> {
            studentHomepage(stage);
        });

        Button logout = new Button("Log out");
        grid.add(logout, 0, 5);

        logout.setOnAction((ActionEvent event) -> {
            homePage(stage);
        });

    }

    public void successfulCheckInItem(Stage stage) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Scene scene = new Scene(grid, 600, 550);
        stage.setScene(scene);

        Text search = new Text("Type the ISBN of the item you want to return.");
        grid.add(search, 0, 1);

        TextField isbn = new TextField();
        grid.add(isbn, 0, 2);

        Button checkout = new Button("Return Item");
        grid.add(checkout, 0, 3);

        checkout.setOnAction((ActionEvent event) -> {
            try {
                if (isbn.getText().matches("\\d+")) {
                    LibraryItem temp = new LibraryItem();
                    LibraryItem real = new LibraryItem();
                    real = temp.read(Long.valueOf(isbn.getText()));
                    real.removeCheckout(currentStudent.getidnum());
                    successfulCheckInItem(stage);
                } else {
                    failedCheckInItem(stage);
                }
            } catch (Exception ex) {
                failedCheckInItem(stage);
            }
        });

        Button returnhome = new Button("Go to homepage");
        grid.add(returnhome, 0, 4);

        returnhome.setOnAction((ActionEvent event) -> {
            studentHomepage(stage);
        });

        Button logout = new Button("Log out");
        grid.add(logout, 0, 5);

        logout.setOnAction((ActionEvent event) -> {
            homePage(stage);
        });

        Text success = new Text("Your item was successfully returned.");
        success.setFill(Color.GREEN);
        grid.add(success, 0, 6);
    }

    public void failedCheckInItem(Stage stage) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Scene scene = new Scene(grid, 600, 550);
        stage.setScene(scene);

        Text search = new Text("Type the ISBN of the item you want to return.");
        grid.add(search, 0, 1);

        TextField isbn = new TextField();
        grid.add(isbn, 0, 2);

        Button checkout = new Button("Return Item");
        grid.add(checkout, 0, 3);

        checkout.setOnAction((ActionEvent event) -> {
            try {
                if (isbn.getText().matches("\\d+")) {
                    LibraryItem temp = new LibraryItem();
                    LibraryItem real = new LibraryItem();
                    real = temp.read(Long.valueOf(isbn.getText()));
                    real.removeCheckout(currentStudent.getidnum());
                    successfulCheckInItem(stage);
                } else {
                    failedCheckInItem(stage);
                }
            } catch (Exception ex) {
                failedCheckInItem(stage);
            }
        });

        Button returnhome = new Button("Go to homepage");
        grid.add(returnhome, 0, 4);

        returnhome.setOnAction((ActionEvent event) -> {
            studentHomepage(stage);
        });

        Button logout = new Button("Log out");
        grid.add(logout, 0, 5);

        logout.setOnAction((ActionEvent event) -> {
            homePage(stage);
        });

        Text fail = new Text("Incorrect ISBN or you have not checked out this book.");
        fail.setFill(Color.RED);
        grid.add(fail, 0, 6);

        Text fail2 = new Text("Please try again or contact a librarian.");
        fail2.setFill(Color.RED);
        grid.add(fail2, 0, 7);
    }

    public void placeHold(Stage stage) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Scene scene = new Scene(grid, 600, 550);
        stage.setScene(scene);

        Text search = new Text("Type the ISBN of the item you want to place a hold on.");
        grid.add(search, 0, 1);

        TextField isbn = new TextField();
        grid.add(isbn, 0, 2);

        Button checkout = new Button("Place Hold");
        grid.add(checkout, 0, 3);

        checkout.setOnAction((ActionEvent event) -> {
            try {
                if (isbn.getText().matches("\\d+")) {
                    LibraryItem temp = new LibraryItem();
                    LibraryItem real = new LibraryItem();
                    real = temp.read(Long.valueOf(isbn.getText()));
                    real.addHold(currentStudent.getidnum());
                    successfulPlaceHold(stage);
                } else {
                    failedPlaceHold(stage);
                }
            } catch (Exception ex) {
                failedPlaceHold(stage);
            }
        });

        Button returnhome = new Button("Go to homepage");
        grid.add(returnhome, 0, 4);

        returnhome.setOnAction((ActionEvent event) -> {
            studentHomepage(stage);
        });

        Button logout = new Button("Log out");
        grid.add(logout, 0, 5);

        logout.setOnAction((ActionEvent event) -> {
            homePage(stage);
        });
    }

    public void successfulPlaceHold(Stage stage) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Scene scene = new Scene(grid, 600, 550);
        stage.setScene(scene);

        Text search = new Text("Type the ISBN of the item you want to place a hold on.");
        grid.add(search, 0, 1);

        TextField isbn = new TextField();
        grid.add(isbn, 0, 2);

        Button checkout = new Button("Place Hold");
        grid.add(checkout, 0, 3);

        checkout.setOnAction((ActionEvent event) -> {
            try {
                if (isbn.getText().matches("\\d+")) {
                    LibraryItem temp = new LibraryItem();
                    LibraryItem real = new LibraryItem();
                    real = temp.read(Long.valueOf(isbn.getText()));
                    real.addHold(currentStudent.getidnum());
                    successfulPlaceHold(stage);
                } else {
                    failedPlaceHold(stage);
                }
            } catch (Exception ex) {
                failedPlaceHold(stage);
            }
        });

        Button returnhome = new Button("Go to homepage");
        grid.add(returnhome, 0, 4);

        returnhome.setOnAction((ActionEvent event) -> {
            studentHomepage(stage);
        });

        Button logout = new Button("Log out");
        grid.add(logout, 0, 5);

        logout.setOnAction((ActionEvent event) -> {
            homePage(stage);
        });
        Text fail = new Text("Your hold was successfully placed.");
        fail.setFill(Color.GREEN);
        grid.add(fail, 0, 6);
    }

    public void failedPlaceHold(Stage stage) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Scene scene = new Scene(grid, 600, 550);
        stage.setScene(scene);

        Text search = new Text("Type the ISBN of the item you want to place a hold on.");
        grid.add(search, 0, 1);

        TextField isbn = new TextField();
        grid.add(isbn, 0, 2);

        Button checkout = new Button("Place Hold");
        grid.add(checkout, 0, 3);

        checkout.setOnAction((ActionEvent event) -> {
            try {
                if (isbn.getText().matches("\\d+")) {
                    LibraryItem temp = new LibraryItem();
                    LibraryItem real = new LibraryItem();
                    real = temp.read(Long.valueOf(isbn.getText()));
                    real.addHold(currentStudent.getidnum());
                    successfulPlaceHold(stage);
                } else {
                    failedPlaceHold(stage);
                }
            } catch (Exception ex) {
                failedPlaceHold(stage);
            }
        });

        Button returnhome = new Button("Go to homepage");
        grid.add(returnhome, 0, 4);

        returnhome.setOnAction((ActionEvent event) -> {
            studentHomepage(stage);
        });

        Button logout = new Button("Log out");
        grid.add(logout, 0, 5);

        logout.setOnAction((ActionEvent event) -> {
            homePage(stage);
        });
        Text fail = new Text("Incorrect ISBN.");
        fail.setFill(Color.RED);
        grid.add(fail, 0, 6);

        Text fail2 = new Text("Please try again or contact a librarian.");
        fail2.setFill(Color.RED);
        grid.add(fail2, 0, 7);
    }

}
