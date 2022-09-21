package util;

import Service.UserService;

public enum UserServiceUtil {
    INSTANCE;
    UserService userService;
    UserServiceUtil(){
        userService = new UserService();
    }
}
