package by.bsuir.ucp.Repositories;

import by.bsuir.ucp.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public User findByNameAndPassword(String name, String password);

    public User findByName(String username);
}
