package se.iths.storemanagementsystem.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class StoreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String storeName;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JsonManagedReference
    private List<DepartmentEntity> departmentList = new ArrayList<>();

    public StoreEntity() {
    }

    public List<DepartmentEntity> getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(List<DepartmentEntity> departmentList) {
        this.departmentList = departmentList;
    }

    public void addDepartment(DepartmentEntity department) {
        departmentList.add(department);
        department.setStore(this);
    }

    public void removeDepartment(DepartmentEntity department) {
        departmentList.remove(department);
        department.setStore(null);
    }

    public StoreEntity(String storeName) {
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
