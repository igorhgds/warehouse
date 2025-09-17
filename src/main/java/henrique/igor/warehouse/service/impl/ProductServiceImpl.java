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
public class ProductServiceImpl implements IProductService {

    private final ProductRepository repository;
    private final IProductQueryService  queryService;
    private final IStockService stockService;
    private final RestClient storefrontClient;
    private final IProductMapper mapper;

    @Override
    public ProductEntity save(ProductEntity entity) {
        repository.save(entity);
        var dto = mapper.toDTO(entity);
        saveStorefront(dto);
        return entity;
    }


    @Override
    public void purchase(UUID id) {
        var entity = queryService.findById(id);
        var stock = entity.decStock();
        repository.save(entity);
        if (stock.isUnavailable()){
            stockService.changeStatus(entity.getId(), stock.getStatus());
        }
    }

    private void saveStorefront(ProductStorefrontSaveDTO dto) {
        storefrontClient.post()
                .uri("/products")
                .body(dto)
                .retrieve()
                .body(ProductStorefrontSavedDTO.class);
    }
}
