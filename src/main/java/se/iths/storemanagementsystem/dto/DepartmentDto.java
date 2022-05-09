package se.iths.storemanagementsystem.dto;

import se.iths.storemanagementsystem.entity.ItemEntity;
import se.iths.storemanagementsystem.entity.StoreEntity;
import se.iths.storemanagementsystem.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;

public class DepartmentDto {

    private Long id;
    private String departmentName;
    private List<UserEntity> employeeList = new ArrayList<>();
    private StoreEntity store;
    private List<ItemEntity> itemList = new ArrayList<>();

    public DepartmentDto() {
    }

    public DepartmentDto(Long id, String departmentName, List<UserEntity> employeeList, StoreEntity store, List<ItemEntity> itemList) {
        this.id = id;
        this.departmentName = departmentName;
        this.employeeList = employeeList;
        this.store = store;
        this.itemList = itemList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public List<String> getEmployeeList() {
        List<String> employees = new ArrayList<>();
        for (UserEntity user : employeeList) {
            employees.add(user.getEmail());
        }
        return employees;
    }

    public void setEmployeeList(List<UserEntity> employeeList) {
        this.employeeList = employeeList;
    }

    public String getStore() {
        if (store != null) {
            return store.getStoreName();
        } else
            return "";
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
}
