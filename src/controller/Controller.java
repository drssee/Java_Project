package controller;

import domain.User;
import util.ClassUtil;
import view.Errorable;

public class Controller implements Errorable {
    public final static Integer RESERVATION_SIZE = 100; // 영화당 예매 가능한 최대값
    public static boolean isInLogin = false;
    public static User loginedUser = null;
    public static Integer result=-1;
    String tmp = "";
}
