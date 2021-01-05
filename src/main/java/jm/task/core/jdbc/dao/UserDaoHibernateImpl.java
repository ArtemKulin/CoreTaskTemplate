package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    SessionFactory factory = Util.getSessionFactory();
    Session session = null;
    Transaction transaction = null;

    public UserDaoHibernateImpl() {
    }


    @Override
    public void createUsersTable() {
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            String query = "CREATE TABLE IF NOT EXISTS test.User (\n" +
                    "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                    "  `name` VARCHAR(45) NULL,\n" +
                    "  `lastname` VARCHAR(45) NULL,\n" +
                    "  `age` INT NULL,\n" +
                    "  PRIMARY KEY (`id`),\n" +
                    "  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE);";
            session.createSQLQuery(query).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {transaction.rollback();}
        } finally {
            if (session != null) {session.close();}
        }
    }

    @Override
    public void dropUsersTable() {
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            String query = "DROP TABLE IF EXISTS test.User;";
            session.createSQLQuery(query).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {transaction.rollback();}
        } finally {
            if (session != null) {session.close();}
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try  {
            session = factory.openSession();
            User user = new User(name, lastName, age);
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {transaction.rollback();}
        } finally {
            if (session != null) {session.close();}
        }
    }

    @Override
    public void removeUserById(long id) {
        try  {
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.createQuery("delete User where id = :id")
                    .setParameter("id", id)
                    .executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {transaction.rollback();}
        } finally {
            if (session != null) {session.close();}
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            users = session.createQuery("from User").list();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {transaction.rollback();}
        } finally {
            if (session != null) {session.close();}
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            String query = "TRUNCATE TABLE test.User;";
            session.createSQLQuery(query).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {transaction.rollback();}
        } finally {
            if (session != null) {session.close();}
        }
    }
}
