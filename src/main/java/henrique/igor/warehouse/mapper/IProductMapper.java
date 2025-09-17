package henrique.igor.warehouse.mapper;

import henrique.igor.warehouse.dto.ProductStorefrontSaveDTO;
import henrique.igor.warehouse.entity.ProductEntity;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;


@Mapper(componentModel = SPRING)
public interface IProductMapper {

    ProductStorefrontSaveDTO toDTO(final ProductEntity entity);

}
