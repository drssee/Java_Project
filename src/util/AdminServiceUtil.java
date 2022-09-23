package util;

import service.AdminService;
import service.UserService;

public enum AdminServiceUtil {
    INSTANCE;
    public AdminService adminService;
    AdminServiceUtil(){
        adminService = new AdminService();
    }
}
