package se.iths.storemanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.iths.storemanagementsystem.entity.ShoppingCart;
@Repository
public interface ShoppingCartRepository extends JpaRepository <ShoppingCart, Long> {

}
