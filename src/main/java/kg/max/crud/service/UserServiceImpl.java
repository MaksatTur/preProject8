package kg.max.crud.service;

import kg.max.crud.dao.UserDAO;
import kg.max.crud.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    @Transactional
    public List<User> findAll() {
        return userDAO.findAll();
    }

    @Override
    @Transactional
    public void delete(User user) {
        userDAO.delete(user);
    }

    @Override
    @Transactional
    public void update(User user) {
        userDAO.update(user);
    }

    @Override
    @Transactional
    public User getUserById(long id) {
        return userDAO.getUserById(id);
    }

    @Override
    @Transactional
    public void insert(User user) {
        userDAO.insert(user);
    }

    @Override
    @Transactional
    public String getUserPasswordById(long id) {
        return userDAO.getUserPasswordById(id);
    }
}
