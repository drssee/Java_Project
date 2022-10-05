package util;

import service.MainService;
import service.MainServiceImpl;

public enum MainServiceUtil implements Util {
    INSTANCE;
    public MainService mainService;
    MainServiceUtil(){
        mainService = new MainServiceImpl();
    }
}
