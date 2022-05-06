package se.iths.storemanagementsystem.dto;

import se.iths.storemanagementsystem.entity.DepartmentEntity;

public class ItemDto {
    private Long id;

    private String name;

    private double price;

    private DepartmentEntity department;

    public ItemDto() {
    }

    public ItemDto(Long id, String name, double price, DepartmentEntity department) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.department = department;
    }

    public String getDepartment() {
        return department.getDepartmentName();
    }

    public void setDepartment(DepartmentEntity department) {
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



}
