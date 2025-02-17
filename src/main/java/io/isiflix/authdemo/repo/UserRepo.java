package io.isiflix.authdemo.repo;

import io.isiflix.authdemo.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Integer> {
    public User findByLogin(String login);
}
