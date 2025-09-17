package henrique.igor.warehouse.mapper;

import henrique.igor.warehouse.controller.request.ProductSaveRequest;
import henrique.igor.warehouse.controller.response.ProductDetailResponse;
import henrique.igor.warehouse.controller.response.ProductSavedResponse;
import henrique.igor.warehouse.dto.ProductStorefrontSaveDTO;
import henrique.igor.warehouse.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;


@Mapper(componentModel = SPRING)
public interface IProductMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "stocks" , ignore = true)
    @Mapping(target = "price" , ignore = true)
    ProductEntity toEntity(final ProductSaveRequest request);

    ProductSavedResponse toSavedResponse(final ProductEntity entity);

    ProductStorefrontSaveDTO toDTO(final ProductEntity entity);

    ProductDetailResponse toDetailResponse(final ProductEntity entity);
}
