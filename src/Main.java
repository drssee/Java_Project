import controller.MainController;
import util.ClassUtil;

public class Main {
    public static void main(String[] args) throws Exception {
        ClassUtil.INSTANCE.invoke("view.Home","mainLogo");
        new MainController();
    }//main
}
