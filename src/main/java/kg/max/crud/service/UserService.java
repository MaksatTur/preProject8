package kg.max.crud.service;

import kg.max.crud.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserService {
    List<User> findAll();

    void delete(User user);

    void update(User user);

    User getUserById(long id);

    void insert(User user);

    String getUserPasswordById(long id);
}
