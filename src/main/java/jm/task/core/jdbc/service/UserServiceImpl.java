package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    private static final UserDaoJDBCImpl userDAO = new UserDaoJDBCImpl();
    private static final UserDaoHibernateImpl userHiber = new UserDaoHibernateImpl();

    public void createUsersTable() {
        //userDAO.createUsersTable();
        userHiber.createUsersTable();
    }

    public void dropUsersTable() {
        //userDAO.dropUsersTable();
        userHiber.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        //userDAO.saveUser(name, lastName, age);
        userHiber.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        //userDAO.removeUserById(id);
        userHiber.removeUserById(id);
    }

    public List<User> getAllUsers() {
        //return userDAO.getAllUsers();
        return userHiber.getAllUsers();
    }

    public void cleanUsersTable() {
        //userDAO.cleanUsersTable();
        userHiber.cleanUsersTable();
    }
}
