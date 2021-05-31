package Views;

import Controller.LibrarianController;
import Controller.LoginControl;
import Controller.UserController;
import Model.Book;

import Model.BookCategory;
import Model.Borrow;
import Model.DTOs.BorrowDTO;
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

import java.io.OutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserWindow extends Application {

    private UserController userController;
    private LoginControl loginControl;
    private LibrarianController librarianController;
    private User user;
    private List<Book> books;
    private List<BorrowDTO> borrowed;
    private String selected;
    private int selectedBorrow = -1;
    private Stage stage;

    public void setControl(LoginControl loginControl, UserController userController, LibrarianController librarianController, User user, Stage stage){
        this.loginControl = loginControl;
        this.userController = userController;
        this.librarianController = librarianController;
        this.user = user;
        this.stage = stage;
        loadBooks();
        loadBorrows();
    }

    @FXML
    private TableView<Book> table;
    @FXML
    private TableColumn<Book, String> colTitle;
    @FXML
    private TableColumn<Book, String> colAuthor;
    @FXML
    private TableColumn<Book, String> colCategory;
    @FXML
    private TableColumn<Book, String> colCode;

    @FXML
    private TableView<BorrowDTO> tableBorrow;
    @FXML
    private TableColumn<BorrowDTO, String> colBTitle;
    @FXML
    private TableColumn<BorrowDTO, String> colBAuthor;
    @FXML
    private TableColumn<BorrowDTO, Boolean> colBExtented;
    @FXML
    private TableColumn<BorrowDTO, String> colBCode;
    @FXML
    private TableColumn<BorrowDTO, Integer> colBId;
    @FXML
    private TableColumn<BorrowDTO, LocalDate> colBDate;

    @FXML
    private TextArea txtBook;
    @FXML
    private TextField txtSelected;
    @FXML
    private TextField txtBorro;

    private void loadBooks(){
        books = userController.getAvailableBooks();
        table.getItems().clear();
        for(Book book: books){
            table.getItems().add(book);
        }
    }

    public void initialize(){
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colBTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colBAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        colBExtented.setCellValueFactory(new PropertyValueFactory<>("extended"));
        colBDate.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        colBId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colBCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        table.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String code = table.getSelectionModel().getSelectedItem().getCode();
                Book book = null;
                for(Book aux: books) {
                    if (aux.getCode().equals(code)) {
                        book = aux;
                        break;
                    }
                }
                String text = "Code: "+book.getCode()+"\nTitle: "+book.getTitle();
                txtSelected.setText(text);
                text += "\nAuthor: "+book.getAuthor()+"\nDescription: "+book.getDescription()+"\nPages from book:\n"+book.getPagesFromBook();
                txtBook.setText(text);
                selected = book.getCode();
            }
        });
        tableBorrow.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String code = tableBorrow.getSelectionModel().getSelectedItem().getBookCode();
                BorrowDTO book = null;
                for(BorrowDTO aux: borrowed) {
                    if (aux.getBookCode().equals(code)) {
                        book = aux;
                        break;
                    }
                }
                String text = "Code: "+book.getBookCode()+"\nTitle: "+book.getTitle();
                txtBorro.setText(text);
                selectedBorrow=book.getId();
            }
        });
    }

    public void confirm(){
        if(selected == null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.getDialogPane().setContent(new ScrollPane(new Label(new String("Select a book first"))));
            alert.show();
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm selected books");
        alert.setContentText("Borrow this book "+txtSelected.getText()+"?");
        ButtonType okButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType noButton = new ButtonType("NO", ButtonBar.ButtonData.NO);
        alert.getButtonTypes().setAll(okButton, noButton);
        alert.showAndWait().ifPresent(type -> {
            if (type == okButton) {
                userController.borrowBook(user.getCnp(), selected);
                loadBorrows();
                loadBooks();
            } else if (type == noButton) {
            }
        });
    }

    public void xtnd(){
        if(selectedBorrow == -1){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.getDialogPane().setContent(new ScrollPane(new Label(new String("Select a book first"))));
            alert.show();
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm selected books");
        alert.setContentText("Extend this book "+txtSelected.getText()+"?");
        ButtonType okButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType noButton = new ButtonType("NO", ButtonBar.ButtonData.NO);
        alert.getButtonTypes().setAll(okButton, noButton);
        alert.showAndWait().ifPresent(type -> {
            if (type == okButton) {
                userController.extendbook(selectedBorrow);
                loadBorrows();
                loadBooks();
            } else if (type == noButton) {
            }
        });
    }

    public void loadBorrows(){
        try{
            tableBorrow.getItems().clear();
            borrowed = userController.getBorrowedBooks(user.getCnp());
            for(BorrowDTO b:borrowed)
                tableBorrow.getItems().add(b);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

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

