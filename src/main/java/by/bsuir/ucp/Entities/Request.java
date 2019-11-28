package by.bsuir.ucp.Entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Request {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "way_id")
    private Way way;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    private Double weight;
    private Double cost;
    private Boolean complete;

    public Request(Way way, User user, Double weight, Double cost) {
        this.way = way;
        this.user = user;
        this.weight = weight;
        this.cost = cost;
    }

    public Request() {

    }
}
