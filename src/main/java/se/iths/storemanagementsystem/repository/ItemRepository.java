package se.iths.storemanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.iths.storemanagementsystem.entity.Item;
@Repository
public interface ItemRepository extends JpaRepository <Item, Long>{
}
