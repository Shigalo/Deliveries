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
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private Double max_capacity;
    private Double unit_cost;
    private Double speed;

    private Double dangerous;
    private Double fragile;
    private Double perishable;

    public Transport() {
    }

    public Transport(String name, Double max_capacity, Double unit_cost, Double speed, Double dangerous, Double fragile, Double perishable) {
        this.name = name;
        this.max_capacity = max_capacity;
        this.unit_cost = unit_cost;
        this.speed = speed;
        this.dangerous = dangerous;
        this.fragile = fragile;
        this.perishable = perishable;
    }
}
