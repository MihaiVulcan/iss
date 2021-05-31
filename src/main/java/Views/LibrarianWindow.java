package Views;

import Controller.LibrarianController;
import Controller.LoginControl;
import Controller.UserController;
import Model.Book;
import Model.BookCategory;
import Model.Librarian;
import Model.User;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.UnknownServiceException;
import java.util.List;

public class LibrarianWindow extends Application {
    private LoginControl loginControl;
    private UserController userController;
    private LibrarianController librarianController;
    private Librarian librarian;
    private Stage stage;

    public void setControl(Librarian librarian, Stage stage, LoginControl loginControl, UserController userController, LibrarianController librarianController){
        this.librarian = librarian;
        this.stage = stage;
        this.loginControl = loginControl;
        this.userController = userController;
        this.librarianController = librarianController;
        loadBooks();
        loadUsers();
    }

    @FXML
    private TextField txtReturn;
    @FXML
    private TableView<Book> tableBooks;
    @FXML
    private TableColumn<Book, String> colTitle;
    @FXML
    private TableColumn<Book, String> colAuthor;
    @FXML
    private TableColumn<Book, String> colCode;
    @FXML
    private TextField txtCode;
    @FXML
    private TextField txtTitle;
    @FXML
    private TextField txtAuthor;
    @FXML
    private TextField txtCategory;
    @FXML
    private TextArea txtDescription;
    @FXML
    private TextArea txtParagraph;
    @FXML
    private TextField txtAva;

    @FXML
    private TableView<User> tableUsers;
    @FXML
    private TableColumn<User, String> colCnp;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtCnp;
    @FXML
    private TextField txtAdress;
    @FXML
    private TextField txtPhone;
    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtPassword;


    public void initialize(){
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        tableBooks.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Book book = tableBooks.getSelectionModel().getSelectedItem();
                txtCode.setText(book.getCode());
                txtTitle.setText(book.getTitle());
                txtAuthor.setText(book.getAuthor());
                txtCategory.setText(book.getCategory().name());
                txtDescription.setText(book.getDescription());
                txtParagraph.setText(book.getPagesFromBook());
                txtAva.setText(book.getAvailable().toString());
            }
        });
        colCnp.setCellValueFactory(new PropertyValueFactory<>("cnp"));
        tableUsers.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                User user = tableUsers.getSelectionModel().getSelectedItem();
                txtName.setText(user.getName());
                txtCnp.setText(user.getCnp());
                txtAdress.setText(user.getAddress());
                txtPhone.setText(user.getPhone());
                txtUsername.setText(user.getUsername());
                txtPassword.setText(user.getPassword());
            }
        });
    }

    public void loadBooks(){
        List<Book> books =  librarianController.getAllBooks();
        tableBooks.getItems().clear();
        for(Book book: books){
            tableBooks.getItems().add(book);
        }
    }

    public void updateBook(){
        String code = txtCode.getText();
        String text = txtTitle.getText();
        String author = txtAuthor.getText();
        BookCategory category = BookCategory.valueOf(txtCategory.getText());
        String desc = txtDescription.getText();
        String para =  txtParagraph.getText();
        Boolean ava = Boolean.getBoolean(txtAva.getText());
        librarianController.updateBook(new Book(code, text, author, desc, para, category, ava));
        loadBooks();
    }

    public void returnBook(){
        String bookCode = txtReturn.getText();
        if(bookCode!=""){
            try{
                librarianController.returnBook(bookCode);
                loadBooks();
            }catch (Exception e){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.getDialogPane().setContent(new ScrollPane(new Label(e.getMessage())));
                alert.show();
            }

        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.getDialogPane().setContent(new ScrollPane(new Label(new String("Insert a book code first"))));
            alert.show();
        }
    }

    public void saveBook(){
        String code = txtCode.getText();
        String text = txtTitle.getText();
        String author = txtAuthor.getText();
        BookCategory category = BookCategory.valueOf(txtCategory.getText());
        String desc = txtDescription.getText();
        String para =  txtParagraph.getText();
        try{
            librarianController.saveBook(new Book(code, text, author, desc, para, category, true));
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.getDialogPane().setContent(new ScrollPane(new Label(e.getMessage())));
            alert.show();
        }

        loadBooks();
    }

    public void deleteBook(){
        String code = txtCode.getText();
        librarianController.deleteBook(code);
        loadBooks();
    }

    public void loadUsers(){
        List<User> users = librarianController.getUsers();
        tableUsers.getItems().clear();
        for(User u : users){
            tableUsers.getItems().add(u);
        }
    }

    public void addUser(){
        String cnp = txtCnp.getText();
        String name = txtName.getText();
        String username = txtUsername.getText();
        String pass = txtPassword.getText();
        String adress = txtAdress.getText();
        String phone =  txtPhone.getText();
        librarianController.addUser(new User(name, cnp, username, pass, phone, adress));
        loadUsers();
    }

    public void updateUser(){
        String cnp = txtCnp.getText();
        String name = txtName.getText();
        String username = txtUsername.getText();
        String pass = txtPassword.getText();
        String adress = txtAdress.getText();
        String phone =  txtPhone.getText();
        librarianController.updateUser(new User(name, cnp, username, pass, phone, adress));
        loadUsers();
    }

    public void deleteUser(){
        String cnp = txtCnp.getText();
        librarianController.deleteUser(new User(cnp));
        loadUsers();
    }

    public void logout(){
        try {
            FXMLLoader messageLoader = new FXMLLoader();
            messageLoader.setLocation(getClass().getResource("/xml/login.fxml"));
            AnchorPane login = messageLoader.load();

            LoginWindow loginWindow = messageLoader.getController();
            loginWindow.setControl(stage, loginControl, userController, librarianController);
            stage.setScene(new Scene(login));
            stage.show();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    }
}
