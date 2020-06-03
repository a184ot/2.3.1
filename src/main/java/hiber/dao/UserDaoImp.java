package hiber.dao;

import hiber.model.User;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
    }


    @Override
    public void editUser(User user) {
        entityManager.merge(user);
    }


    @Override
    @SuppressWarnings("unchecked")
    public List<User> listAllUsers() {
        return entityManager.createQuery("from User").getResultList();
    }


    @Override
    public User getUserByLogin(String login) {
        return entityManager.find(User.class,login);
    }


    @Override
    public User getUserById(Long id) {
        return entityManager.find(User.class,id);
    }

}
