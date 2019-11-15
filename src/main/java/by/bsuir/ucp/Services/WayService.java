package by.bsuir.ucp.Services;

import by.bsuir.ucp.Entities.Point;
import by.bsuir.ucp.Entities.Transport;
import by.bsuir.ucp.Entities.Way;
import by.bsuir.ucp.Repositories.PointRepository;
import by.bsuir.ucp.Repositories.TransportRepository;
import by.bsuir.ucp.Repositories.WayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WayService {

    @Autowired
    WayRepository wayRepository;

    @Autowired
    PointRepository pointRepository;

    @Autowired
    TransportRepository transportRepository;

    @Autowired
    PointService pointService;
    @Autowired
    WayService wayService;

    public Way getSubWay(Point startPoint, Point endPoint) {
        return wayRepository.findByStartPointAndEndPoint(startPoint, endPoint);
    }

    public List<Way> getWayList() {
        return wayRepository.findByTransportNull();
    }

    public Way getById(String id) {
        return wayRepository.findById(Integer.valueOf(id));
    }

    public void wayRemoveById(int id) {
        wayRepository.deleteById(id);
    }


    public void addWay(String wayPoints, String[] length, String transportListName) {

        String[] points = wayPoints.split(",");
        String[] transports = transportListName.split(",");
        String startPointName = points[0];
        String endPointName = points[points.length-1];
        Point startPoint = pointRepository.findByName(startPointName);
        Point endPoint = pointRepository.findByName(endPointName);

        Way way = new Way(startPoint, endPoint);
        wayRepository.save(way);

        List<Point> subPointList = new ArrayList<>();
        for (int i = 1; i < points.length-1; i++) {
            Point subPoint = new Point(points[i], way);
            subPointList.add(subPoint);
            pointRepository.save(subPoint);
        }

        List<Transport> transportList = new ArrayList<>();
        for (String transportName : transports) {
            Transport transport = transportRepository.findByName(transportName);
            transportList.add(transport);
        }

        wayRepository.save(new Way(Double.valueOf(length[0]), startPoint, subPointList.get(0), transportList.get(0)));
        for (int i = 1; i < length.length-1; i++) {
            Way subWay = new Way(Double.valueOf(length[i]), subPointList.get(i-1), subPointList.get(i), transportList.get(i));
            wayRepository.save(subWay);
        }
        wayRepository.save(new Way(Double.valueOf(length[length.length-1]), subPointList.get(subPointList.size()-1), endPoint, transportList.get(transportList.size()-1)));
    }

    public Double getLength(Way way) {
        List<Point> pointList = pointService.getSubPoints(way.getId());

        List<Way> wayList = new ArrayList<>();
        wayList.add(wayService.getSubWay(way.getStartPoint(), pointList.get(0)));
        for(int i = 0; i < pointList.size()-1; i++) { wayList.add(wayService.getSubWay(pointList.get(i), pointList.get(i+1))); }
        wayList.add(wayService.getSubWay(pointList.get(pointList.size()-1), way.getEndPoint()));

        Double sumLength = 0.0;
        for(Way buf : wayList) {
            if(buf != null)
            sumLength += buf.getLength();
        }
        return sumLength;
    }
    public Double getTime(Way way) {
        List<Point> pointList = pointService.getSubPoints(way.getId());

        List<Way> wayList = new ArrayList<>();
        wayList.add(wayService.getSubWay(way.getStartPoint(), pointList.get(0)));
        for(int i = 0; i < pointList.size()-1; i++) { wayList.add(wayService.getSubWay(pointList.get(i), pointList.get(i+1))); }
        wayList.add(wayService.getSubWay(pointList.get(pointList.size()-1), way.getEndPoint()));

        Double sumTime = 0.0;
        for(Way buf : wayList) {
            if(buf != null)
                sumTime += buf.getLength()/buf.getTransport().getSpeed();
        }
        return sumTime;
    }

    public Double getCost(Way way) {
        /*List<Point> pointList = pointService.getSubPoints(way.getId());

        List<Way> wayList = new ArrayList<>();
        wayList.add(wayService.getSubWay(way.getStartPoint(), pointList.get(0)));
        for(int i = 0; i < pointList.size()-1; i++) { wayList.add(wayService.getSubWay(pointList.get(i), pointList.get(i+1))); }
        wayList.add(wayService.getSubWay(pointList.get(pointList.size()-1), way.getEndPoint()));

        Double sumTime = 0.0;
        for(Way buf : wayList) {
            if(buf != null)
                sumTime += buf.getLength()/buf.getTransport().getSpeed();
        }
        return sumTime;*/
        return 2.0;
    }
}
