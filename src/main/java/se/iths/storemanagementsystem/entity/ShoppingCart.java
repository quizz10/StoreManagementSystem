package se.iths.storemanagementsystem.entity;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Entity
public class ShoppingCart {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;

    private double totalPrice;

    @ManyToMany
    Set<Item> items = new HashSet<>();

    @Transient
    @OneToOne(cascade = CascadeType.ALL)
    private UserEntity user;


    public ShoppingCart(){

    }

    public void addItem(Optional<Item> item){
        items.add(item.get());
    }

    public void removeItem(Item item){
        items.remove(item);
    }
    
    public void setUser(UserEntity user) {
        this.user = user;
    }

    public void removeUser(UserEntity user) { this.user = null; }

    public Long getId() {
        return id;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Set<Item> getItems() {
        return items;
    }

}
