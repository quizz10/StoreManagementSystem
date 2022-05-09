package se.iths.storemanagementsystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class DepartmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(min = 2)
    @Column(unique=true)
    private String departmentName;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<UserEntity> employeeList = new ArrayList<>();

    @ManyToOne
    @JsonBackReference
    private StoreEntity store;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<ItemEntity> itemList = new ArrayList<>();

    public void addItem (ItemEntity item){
        itemList.add(item);
        item.setDepartment(this);
    }

    public void removeItem(ItemEntity item){
        itemList.remove(item);
        item.setDepartment(null);
    }

    public DepartmentEntity() {
    }

    public DepartmentEntity(String departmentName) {
        this.departmentName = departmentName;
    }

    public StoreEntity getStore() {
        return store;
    }

    public void setStore(StoreEntity store) {
        this.store = store;
    }

    public List<ItemEntity> getItemList() {
        return itemList;
    }

    public void setItemList(List<ItemEntity> itemList) {
        this.itemList = itemList;
    }

    public Long getId() {
        return id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public List<UserEntity> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<UserEntity> employeeList) {
        this.employeeList = employeeList;
    }

    public void addEmployee(UserEntity employee) {
        employeeList.add(employee);
        employee.setDepartment(this);
    }

    public void removeEmployee(UserEntity employee) {
        employeeList.remove(employee);
        employee.setDepartment(null);
    }

}
