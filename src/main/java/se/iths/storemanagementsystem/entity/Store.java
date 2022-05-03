package se.iths.storemanagementsystem.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String storeName;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JsonManagedReference
    private List<Department> departmentList = new ArrayList<>();

    public Store() {
    }

    public List<Department> getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(List<Department> departmentList) {
        this.departmentList = departmentList;
    }

    public void addDepartment(Department department) {
        departmentList.add(department);
        department.setStore(this);
    }

    public void removeDepartment(Department department) {
        departmentList.remove(department);
        department.setStore(null);
    }

    public Store(String storeName) {
        this.storeName = storeName;
    }

    public Long getId() {
        return id;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
}
