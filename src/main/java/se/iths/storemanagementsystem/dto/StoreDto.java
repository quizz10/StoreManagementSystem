package se.iths.storemanagementsystem.dto;

import se.iths.storemanagementsystem.entity.DepartmentEntity;

import java.util.ArrayList;
import java.util.List;

public class StoreDto {

    private Long id;
    private String storeName;
    private List<DepartmentEntity> departmentList = new ArrayList<>();

    public StoreDto() {
    }

    public StoreDto(Long id, String storeName, List<DepartmentEntity> departmentList) {
        this.id = id;
        this.storeName = storeName;
        this.departmentList = departmentList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public List<String> getDepartmentList() {

        if (!departmentList.isEmpty()) {
            List<String> nameList = new ArrayList<>();
            for (DepartmentEntity department : departmentList) {
                nameList.add(department.getDepartmentName());
            }
            return nameList;
        }
        return List.of("No Departments in store");
    }

    public void setDepartmentList(List<DepartmentEntity> departmentList) {
        this.departmentList = departmentList;
    }
}
