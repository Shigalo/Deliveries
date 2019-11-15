package by.bsuir.ucp.Entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
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
}
