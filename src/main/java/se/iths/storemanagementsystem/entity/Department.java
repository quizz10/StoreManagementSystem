package se.iths.storemanagementsystem.entity;

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


    public Department() {
    }

    public Department(String departmentName) {
        this.departmentName = departmentName;
    }


    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
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

    // link to items, employee
}
