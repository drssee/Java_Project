package util;

import service.UserService;

public enum UserServiceUtil {
    INSTANCE;
    public UserService userService;
    UserServiceUtil(){
        userService = new UserService();
    }
}
