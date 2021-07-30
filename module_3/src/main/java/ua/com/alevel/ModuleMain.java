package ua.com.alevel;

import ua.com.alevel.controller.Controller;

public class ModuleMain {
    private static String username;
    private static String password;
    private static Long userId;

    public static void main(String[] args) {
       Controller controller = new Controller();
       controller.run(userId, username, password);
    }

    private static void init(String[] args) {
        username = args[0];
        password = args[1];
        userId = Long.parseLong(args[2]);
    }
}
