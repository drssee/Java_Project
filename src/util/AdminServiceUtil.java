package util;

import service.AdminService;
import service.AdminServiceImpl;

public enum AdminServiceUtil {
    INSTANCE;
    public AdminService adminService;
    AdminServiceUtil(){
        adminService = new AdminServiceImpl();
    }
}
