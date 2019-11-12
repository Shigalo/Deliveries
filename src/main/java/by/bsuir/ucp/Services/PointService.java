package by.bsuir.ucp.Services;

import by.bsuir.ucp.Entities.Point;
import by.bsuir.ucp.Repositories.PointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PointService {

    @Autowired
    PointRepository pointRepository;

    public List<Point> getSubPoints(Integer id) {
        return pointRepository.findByWay_Id(id);
    }

    public boolean addPoint(String name) {
        Point point = pointRepository.findByName(name);
        if (point == null) {
            pointRepository.save(new Point(name));
            return true;
        }
        return false;
    }

    public List<Point> getPointList() {
        return pointRepository.findByWayNull();
    }

    public Point getById(String id) {
        return pointRepository.findById(Integer.valueOf(id));
    }

    public boolean editPoint(String id, String pointName) {
        if(pointRepository.findByName(pointName) == null) {
            pointRepository.setUserInfoById(pointName, Integer.valueOf(id));
            return true;
        }
        return false;
    }

    public void pointRemoveById(int id) {
        pointRepository.deleteById(id);
    }
}
