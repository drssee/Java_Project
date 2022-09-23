package controller;

import view.Admin;
import view.Errorable;

public class AdminController implements Errorable {

    public Integer admin() {
        Integer result;
        result = Admin.AdminMenu();
        if(result==-1){
            return result;
        }


        return result;
    }
}
