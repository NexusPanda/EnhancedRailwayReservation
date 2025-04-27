package controller;

import model.User;
import db.UserDB;

public class UserController {
    public static boolean register(String username, String password) {
        return UserDB.registerUser(username, password);
    }

    public static User login(String username, String password) {
        return UserDB.loginUser(username, password);
    }
}
