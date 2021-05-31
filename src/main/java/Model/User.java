package Model;

import java.util.*;

/**
 * 
 */
public class User {

    public User() {
    }

    public User(String name, String cnp, String username, String password, String phone, String address) {
        this.name = name;
        this.cnp = cnp;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.address = address;
    }

    /**
     * Default constructor


    /**
     * 
     */
    public String name;

    /**
     *
     */
    public String cnp;

    /**
     * 
     */
    public String username;

    public User(String cnp) {
        this.cnp = cnp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 
     */
    public String password;

    /**
     * 
     */
    public String phone;

    /**
     * 
     */
    public String address;



}