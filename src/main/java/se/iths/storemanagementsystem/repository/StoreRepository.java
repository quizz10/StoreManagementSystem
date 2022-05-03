package se.iths.storemanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.iths.storemanagementsystem.entity.StoreEntity;
@Repository
public interface StoreRepository extends JpaRepository <StoreEntity, Long>{
}
