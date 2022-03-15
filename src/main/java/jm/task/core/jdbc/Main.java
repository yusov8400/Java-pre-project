package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Nick","Garchevskiy", (byte) 1);
        userService.saveUser("Jorj","White", (byte) 37);
        userService.saveUser("Kelvin","Black", (byte) 21);
        userService.saveUser("Ivan","Ivanov", (byte) 28);
        userService.removeUserById(1);
        System.out.println(Arrays.toString(userService.getAllUsers().toArray()));
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
