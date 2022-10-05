package util;

import service.UserServiceImpl;

public enum UserServiceUtil implements Util{
    INSTANCE;
    public UserServiceImpl userService;
    UserServiceUtil(){
        userService = new UserServiceImpl();
    }
}
