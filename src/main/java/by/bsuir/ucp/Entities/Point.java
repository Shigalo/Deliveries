package by.bsuir.ucp.Entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Point {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "way_id")
    private Way way;

    public Point() {
    }

    public Point(String name) {
        this.name = name;
    }

    public Point(String name, Way way) {
        this.name = name;
        this.way = way;
    }
}
