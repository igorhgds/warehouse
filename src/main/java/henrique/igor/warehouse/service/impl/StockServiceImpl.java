package henrique.igor.warehouse.service.impl;

import henrique.igor.warehouse.dto.StockStatusMessage;
import henrique.igor.warehouse.entity.StockEntity;
import henrique.igor.warehouse.entity.StockStatus;
import henrique.igor.warehouse.repository.StockRepository;
import henrique.igor.warehouse.service.IProductChangeAvailabilityProducer;
import henrique.igor.warehouse.service.IProductService;
import henrique.igor.warehouse.service.IStockService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class StockServiceImpl implements IStockService {

    private final StockRepository repository;
    private final IProductService productService;
    private final IProductChangeAvailabilityProducer producer;

    @Override
    public StockEntity save(StockEntity entity) {
        entity.setProduct(productService.findById(entity.getProduct().getId()));
        return repository.save(entity);
    }

    @Override
    public void release(UUID id) {
        changeStatus(id, StockStatus.AVAILABLE);
    }

    @Override
    public void inactive(UUID id) {
        changeStatus(id, StockStatus.UNAVAILABLE);
    }

    @Override
    public void changeStatus(UUID id, StockStatus status) {
        var entity = repository.findById(id).orElseThrow();
        entity.setStatus(status);
        repository.save(entity);
        producer.notifyStatusChange(new StockStatusMessage(entity.getProduct().getId(), status));
    }
}
