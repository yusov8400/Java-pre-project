package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Georg", "Niordanovich", (byte) 13);
        userService.saveUser("Yan", "Haritonovichj", (byte) 27);
        userService.saveUser("Kalyan", "Abromovich", (byte) 1);
        userService.saveUser("Greg", "Helsinki", (byte) 58);
        userService.saveUser("John", "Wick", (byte) 31);
        userService.removeUserById(1);
        System.out.println(Arrays.toString(userService.getAllUsers().toArray()));
        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}
