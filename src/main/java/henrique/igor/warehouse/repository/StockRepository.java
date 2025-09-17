package henrique.igor.warehouse.repository;

import henrique.igor.warehouse.entity.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StockRepository extends JpaRepository<StockEntity, UUID> {
}
