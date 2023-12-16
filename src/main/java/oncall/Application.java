package oncall;

import oncall.controller.MainController;

public class Application {
    public static void main(String[] args) {
        MainController controller = new MainController();
        controller.run();
    }
}
