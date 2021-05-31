package Views;

import Controller.LibrarianController;
import Controller.LoginControl;
import Controller.UserController;
import Model.Librarian;
import Model.User;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoginWindow extends Application {

    private LoginControl control;
    private UserController userController;
    private LibrarianController librarianController;
    private Stage primaryStage;
    public void setControl(Stage primaryStage, LoginControl control, UserController userController, LibrarianController librarianController) {
        this.control = control;
        this.primaryStage = primaryStage;
        this.userController = userController;
        this.librarianController = librarianController;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
    }

    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Button btLogin;

    @FXML
    public void login(){
        String username = txtUsername.getText();
        String password = txtPassword.getText();
        try{
            User user = control.checkUser(username, password);

            FXMLLoader messageLoader = new FXMLLoader();
            messageLoader.setLocation(getClass().getResource("/xml/user.fxml"));
            AnchorPane pane = messageLoader.load();

            UserWindow userWindow = messageLoader.getController();
            userWindow.setControl(control, userController, librarianController, user, primaryStage);
            primaryStage.setScene(new Scene(pane));
            primaryStage.show();
        }catch (Exception e){
            try{
                Librarian lib = control.checkLibrarian(username, password);
                FXMLLoader messageLoader = new FXMLLoader();
                messageLoader.setLocation(getClass().getResource("/xml/librarian.fxml"));
                AnchorPane pane = messageLoader.load();

                LibrarianWindow librarianWindow = messageLoader.getController();
                librarianWindow.setControl(lib, primaryStage, control, userController, librarianController);
                primaryStage.setScene(new Scene(pane));
                primaryStage.show();
            }catch (Exception e1){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.getDialogPane().setContent(new ScrollPane(new Label(new String("Wrong email or password"))));
                alert.show();
            }
        }
    }


}
