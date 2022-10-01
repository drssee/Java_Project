package util;

import service.MainService;
import service.MainServiceImpl;

public enum MainServiceUtil {
    INSTANCE;
    public MainService mainService;
    MainServiceUtil(){
        mainService = new MainServiceImpl();
    }
}
