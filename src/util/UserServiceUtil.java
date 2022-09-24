package util;

import service.UserServiceImpl;

public enum UserServiceUtil {
    INSTANCE;
    public UserServiceImpl userService;
    UserServiceUtil(){
        userService = new UserServiceImpl();
    }
}
