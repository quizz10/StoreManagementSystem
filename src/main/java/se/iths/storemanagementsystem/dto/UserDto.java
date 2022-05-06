package se.iths.storemanagementsystem.dto;


import se.iths.storemanagementsystem.entity.DepartmentEntity;
import se.iths.storemanagementsystem.entity.RoleEntity;
import se.iths.storemanagementsystem.entity.ShoppingCartEntity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserDto {

    private Long id;
    private String username;
    private String email;
    private DepartmentEntity department;

    private ShoppingCartEntity shoppingCart;
    Set<RoleEntity> roles = new HashSet<>();


    public UserDto() {
    }

    public UserDto(Long id, String username, String email, DepartmentEntity department, ShoppingCartEntity shoppingCart) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.department = department;
        this.shoppingCart = shoppingCart;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getShoppingCart() {
        if(shoppingCart != null) {
            return "ID: " + shoppingCart.getId().toString();
        }
        return "No shoppingcart found.";
    }

    public void setShoppingCart(ShoppingCartEntity shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public List<String> getRoles() {

        List<String> roleNames = new ArrayList<>();
        for(RoleEntity role : roles) {
            roleNames.add(role.getName());
        }
        return roleNames;
    }

    public void setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
    }

    public String getDepartment() {

        RoleEntity foundRole = roles.stream()
                .filter(role -> role.getName().equals("ROLE_EMPLOYEE")).findAny()
                .orElse(null);

        if(foundRole != null) {
            return department.getDepartmentName();
        }
        return "";
    }

    public void setDepartment(DepartmentEntity department) {
        this.department = department;
    }
}
