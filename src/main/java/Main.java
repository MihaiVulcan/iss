import Controller.LibrarianController;
import Controller.LoginControl;
import Controller.UserController;
import Model.Librarian;
import Repo.*;
import Views.LoginWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.io.IOException;


public class Main extends Application {

    private void initView(Stage primaryStage) throws IOException {
        UserRepository userRepository = new UserRepositoryDatabase(sessionFactory);
        LibrarianRepository librarianRepository = new LibrarianRepositoryDatabase(sessionFactory);
        BookRepository bookRepository = new BookRepositoryDatabase(sessionFactory);
        BorrowRepository borrowRepository = new BorrowRepositoryDatabase(sessionFactory);

        LoginControl control = new LoginControl(userRepository, librarianRepository);
        UserController userController = new UserController(bookRepository, borrowRepository);
        LibrarianController librarianController = new LibrarianController(userRepository, bookRepository, borrowRepository);

        FXMLLoader messageLoader = new FXMLLoader();
        messageLoader.setLocation(getClass().getResource("/xml/login.fxml"));
        AnchorPane login = messageLoader.load();

        LoginWindow loginWindow = messageLoader.getController();
        loginWindow.setControl(primaryStage, control, userController, librarianController);
        primaryStage.setScene(new Scene(login));
        primaryStage.show();
    }


    static SessionFactory sessionFactory;

    static void initialize() {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            System.err.println("Exception "+e);
            StandardServiceRegistryBuilder.destroy( registry );
        }
    }

    static void close(){
        if ( sessionFactory != null ) {
            sessionFactory.close();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        initialize();
        initView(primaryStage);
    }
}
