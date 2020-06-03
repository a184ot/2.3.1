package hiber.dao;

import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(User user) {
        entityManager.persist(user);
    }

    @Override
    public void deleteUser(Long id) {
        User user = getUserById(id);
        entityManager.remove(user);
//        sessionFactory.getCurrentSession().delete(user);
    }


    @Override
    public void editUser(User user) {
        entityManager.merge(user);
//        sessionFactory.getCurrentSession().update(user);
    }


    @Override
    @SuppressWarnings("unchecked")
    public List<User> listAllUsers() {
//        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
//        return query.getResultList();
        return entityManager.createQuery("from User").getResultList();
    }


    @Override
    public User getUserByLogin(String login) {
        return entityManager.find(User.class,login);
//        Session session = sessionFactory.openSession();
//        try {
//            Query query = session.createQuery("from User where login= :login");
//            query.setParameter("login", login);
//            User user = (User) query.uniqueResult();
//            return user;
//        } catch (Exception r) {
//            return null;
//        } finally {
//            session.close();
//        }
    }


    @Override
    public User getUserById(Long id) {
        return entityManager.find(User.class,id);
//        Session session = sessionFactory.openSession();
//        try {
//            Query query = session.createQuery("from User where id= :id");
//            query.setParameter("id", id);
//            User user = (User) query.uniqueResult();
//            return user;
//        } catch (Exception r) {
//            return null;
//        } finally {
//            session.close();
//        }
    }

}
