package henrique.igor.warehouse.service;

import henrique.igor.warehouse.entity.ProductEntity;

import java.util.UUID;

public interface IProductQueryService {

    ProductEntity findById(final UUID id);
}
