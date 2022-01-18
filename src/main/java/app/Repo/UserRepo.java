package app.Repo;

import app.Model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Long> {
    public User findByUserName(String userName);
}
