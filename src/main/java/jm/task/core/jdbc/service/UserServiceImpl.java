package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {

    public void createUsersTable() {
        UserDao userDaoJDBC = new UserDaoHibernateImpl();
        userDaoJDBC.createUsersTable();
    }

    public void dropUsersTable() {
        UserDao userDao = new UserDaoHibernateImpl();
        userDao.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        UserDao userDao = new UserDaoHibernateImpl();
        userDao.saveUser(name,lastName,age);
    }

    public void removeUserById(long id) {
        UserDao userDao = new UserDaoHibernateImpl();
        userDao.removeUserById(id);
    }

    public List<User> getAllUsers() {
        UserDao userDao = new UserDaoHibernateImpl();
        return userDao.getAllUsers();
    }

    public void cleanUsersTable() {
        UserDao userDao = new UserDaoHibernateImpl();
        userDao.cleanUsersTable();
    }
}
