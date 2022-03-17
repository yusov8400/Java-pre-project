package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    Util util = new Util();

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Session session = util.getHibernateConnection().openSession();
        try {
            session.beginTransaction();
            session.createSQLQuery("create table if not exists users (id serial , name varchar(256), lastName varchar(256),age bigint, primary key (id))").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void dropUsersTable() {
        Session session = util.getHibernateConnection().openSession();
        try {
            session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF  EXISTS  users").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = util.getHibernateConnection().openSession();
        try {
            session.getTransaction().begin();
            User user = new User(name, lastName, age);
            user.setName(name);
            user.setLastName(lastName);
            user.setAge(age);
            session.save(user);
            session.getTransaction().commit();
            System.out.println(" User - " + name + " added to database");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

    @Override
    public void removeUserById(long id) {
        Session session = util.getHibernateConnection().openSession();
        try {
            session.beginTransaction();
            session.delete(id);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        Session session = util.getHibernateConnection().openSession();
        List<User> users = null;
        try {
            session.beginTransaction();
            users= session.createQuery("FROM User").list();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Session session = util.getHibernateConnection().openSession();
        try {
            session.beginTransaction();
            session.createSQLQuery("TRUNCATE TABLE users").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
