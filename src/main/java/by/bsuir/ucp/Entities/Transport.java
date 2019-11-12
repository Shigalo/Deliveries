package by.bsuir.ucp.Entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Transport {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String name;
    private Double max_capacity;
    private Double unit_cost;
    private Double speed;

    public Transport() {
    }

    public Transport(String name, Double max_capacity, Double unit_cost, Double speed) {
        this.name = name;
        this.max_capacity = max_capacity;
        this.unit_cost = unit_cost;
        this.speed = speed;
    }
}
