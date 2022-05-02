package se.iths.storemanagementsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String departmentName;

    @OneToMany(cascade = CascadeType.ALL)
    private List<UserEntity> employeeList = new ArrayList<>();

    @ManyToOne
    @Transient
    private Store store;

    @OneToMany
    private List<Item> itemList = new ArrayList<>();

    public void addItem (Item item){
        itemList.add(item);
        item.setDepartment(this);
    }

    public void removeItem(Item item){
        itemList.remove(item);
        item.setDepartment(null);
    }

    public Department() {
    }

    public Department(String departmentName) {
        this.departmentName = departmentName;
    }

    @JsonIgnore
    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
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

    public void addEmployee(UserEntity employee) {
        employeeList.add(employee);
        employee.setDepartment(this);
    }

    public void removeEmployee(UserEntity employee) {
        employeeList.remove(employee);
    }

}
