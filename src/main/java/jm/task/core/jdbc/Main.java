package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();

        userService.createUsersTable();
        System.out.print("-----------");
        userService.saveUser("Гарри", "Поттер", (byte) 30);
        userService.saveUser("Рональд", "Уизли", (byte) 27);
        userService.saveUser("Гермиона", "Грейнджер", (byte) 25);
        userService.saveUser("Драко", "Малфой", (byte) 28);
        System.out.println("-----------");
        for (int i = 0; i < userService.getAllUsers().size(); i++) {
            System.out.println(userService.getAllUsers().get(i));
        }
        System.out.println("-----------");
        userService.cleanUsersTable();
        System.out.println("-----------");
        userService.dropUsersTable();
    }
}
