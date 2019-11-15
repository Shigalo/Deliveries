package by.bsuir.ucp.Repositories;

import by.bsuir.ucp.Entities.Point;
import by.bsuir.ucp.Entities.Request;
import by.bsuir.ucp.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Long> {
//    public Request findByName(String name);

    public Request findById(Integer id);

    @Transactional
    public void deleteById(Integer id);

    public List<Request> findByUser(User user);

}
