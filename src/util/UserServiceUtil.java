package util;

import service.UserService;

public enum UserServiceUtil {
    INSTANCE;
    UserService userService;
    UserServiceUtil(){
        userService = new UserService();
    }
}
