package se.iths.storemanagementsystem.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Entity
public class ShoppingCartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private double totalPrice;

    @ManyToMany
    Set<ItemEntity> items = new HashSet<>();

    @Transient
    @OneToOne(cascade = CascadeType.ALL)
    private UserEntity user;


    public ShoppingCartEntity() {

    }

    public void addItem(ItemEntity item) {
        items.add(item);
    }

    public void removeItem(Optional<ItemEntity> item) {
        items.remove(item.get());
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public void removeUser(UserEntity user) {
        this.user = null;
    }

    public Long getId() {
        return id;
    }

    public double getTotalPrice() {
        totalPrice = items.stream().mapToDouble(ItemEntity::getPrice).sum();
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Set<ItemEntity> getItems() {
        return items;
    }

}
