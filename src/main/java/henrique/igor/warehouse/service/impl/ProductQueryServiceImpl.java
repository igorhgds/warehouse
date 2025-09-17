package henrique.igor.warehouse.service.impl;

import henrique.igor.warehouse.dto.ProductStorefrontSaveDTO;
import henrique.igor.warehouse.dto.ProductStorefrontSavedDTO;
import henrique.igor.warehouse.entity.ProductEntity;
import henrique.igor.warehouse.mapper.IProductMapper;
import henrique.igor.warehouse.repository.ProductRepository;
import henrique.igor.warehouse.service.IProductQueryService;
import henrique.igor.warehouse.service.IProductService;
import henrique.igor.warehouse.service.IStockService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ProductQueryServiceImpl implements IProductQueryService {

    private final ProductRepository repository;

    @Override
    public ProductEntity findById(UUID id) {
        return repository.findById(id).orElseThrow();
    }

}
