package se.iths.storemanagementsystem.entity;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String email;
    private String password;
    @OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Item> items = new ArrayList<>();
    @ManyToOne(cascade = CascadeType.ALL)
    private RoleEntity role;



    public void addItem(Item item) {
        items.add(item);
    }

    public UserEntity(String username, String email, String password, RoleEntity role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public UserEntity() {}

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public RoleEntity getRole() {
        return role;
    }

    public void setRole(RoleEntity role) {
        this.role = role;
    }
}
