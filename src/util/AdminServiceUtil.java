package util;

import service.AdminService;
import service.AdminServiceImpl;

public enum AdminServiceUtil implements Util {
    INSTANCE;
    public AdminService adminService;
    AdminServiceUtil(){
        adminService = new AdminServiceImpl();
    }
}
