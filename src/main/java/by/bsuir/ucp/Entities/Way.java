package by.bsuir.ucp.Entities;

import by.bsuir.ucp.Services.PointService;
import by.bsuir.ucp.Services.WayService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Service
public class Way {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double length;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "start_point_id")
    Point startPoint;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "end_point_id")
    Point endPoint;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "transport_id")
    Transport transport;

    public Way() {
    }

    public Way(Point startPoint, Point endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    public Way(Double length, Point startPoint, Point endPoint, Transport transport) {
        this.length = length;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.transport = transport;
    }

//    @Transient
//    @Autowired
//    PointService pointService;
//    @Transient
//    @Autowired
//    WayService wayService;
//    public Double sumWay() {

//       /* List<Point> pointList =*/ pointService.getPointList();

//        List<Way> wayList = new ArrayList<>();
//        wayList.add(wayService.getSubWay(getStartPoint(), pointList.get(0)));
//        for(int i = 0; i < pointList.size()-1; i++) { wayList.add(wayService.getSubWay(pointList.get(i), pointList.get(i+1))); }
//        wayList.add(wayService.getSubWay(pointList.get(pointList.size()-1), getEndPoint()));

        /*Double sumLength = 0.0;
        for(Way buf : wayList) {
            sumLength += buf.getLength();
        }*/
//        return sumLength;
//        return 100.0;
//    }
}
