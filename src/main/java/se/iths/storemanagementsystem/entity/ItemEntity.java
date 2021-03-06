package se.iths.storemanagementsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;


@Entity
public class ItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    @Size(min = 2)
    private String name;

    @DecimalMin("1")
    private double price;

    @ManyToOne
    private DepartmentEntity department;

    public ItemEntity() {
    }

    public ItemEntity(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public ItemEntity(String name, double price, DepartmentEntity department) {
        this.name = name;
        this.price = price;
        this.department = department;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @JsonIgnore
    public DepartmentEntity getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentEntity department) {
        this.department = department;
    }
}
