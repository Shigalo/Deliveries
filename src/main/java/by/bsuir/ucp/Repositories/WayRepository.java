package by.bsuir.ucp.Repositories;

import by.bsuir.ucp.Entities.Point;
import by.bsuir.ucp.Entities.Way;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface WayRepository extends JpaRepository<Way, Long> {
//    public Way findByName(String name);

    public Way findById(Integer id);

    public List<Way> findByTransportNull();

   /* @Transactional
    @Modifying
    @Query("update Point p set p.name = ?1 where p.id = ?2")
    public void setUserInfoById(String name, Integer id);*/

    @Transactional
    public void deleteById(Integer id);

    public Way findByStartPointAndEndPoint(Point start, Point end);
}
