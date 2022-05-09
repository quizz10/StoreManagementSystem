package se.iths.storemanagementsystem.dto;

import se.iths.storemanagementsystem.entity.ItemEntity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ShoppingCartDto {
    private Long id;
    private double totalPrice;
    private Set<ItemEntity> items = new HashSet<>();

    public ShoppingCartDto() {
    }

    public ShoppingCartDto(Long id, double totalPrice, Set<ItemEntity> items) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<String> getItems() {

        List<String> itemList = new ArrayList<>();

        for (ItemEntity item : items) {
            itemList.add(item.getName());
            itemList.add("Price: " + item.getPrice());
            itemList.add("Department: " + item.getDepartment().getDepartmentName());
        }
        return itemList;
    }

    public void setItems(Set<ItemEntity> items) {
        this.items = items;
    }
}
