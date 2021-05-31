package Controller;

import Model.Librarian;
import Model.User;
import Repo.LibrarianRepository;
import Repo.UserRepository;

public class LoginControl {
    private UserRepository userRepository;
    private LibrarianRepository librarianRepository;

    public LoginControl(UserRepository userRepository, LibrarianRepository librarianRepository) {
        this.userRepository = userRepository;
        this.librarianRepository = librarianRepository;
    }

    public User checkUser(String username, String password) throws Exception {
        User user = new User(null, null, username, password, null, null);
        return userRepository.logIn(user);
    }

    public Librarian checkLibrarian(String username, String password) throws Exception {
        Librarian lib = new Librarian(username, password);
        return librarianRepository.logIn(lib);
    }

}
