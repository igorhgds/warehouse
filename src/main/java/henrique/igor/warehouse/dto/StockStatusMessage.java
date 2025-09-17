package henrique.igor.warehouse.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import henrique.igor.warehouse.entity.StockStatus;

import java.util.UUID;

public record StockStatusMessage(
        @JsonProperty("id")
        UUID id,
        @JsonProperty("status")
        StockStatus status
) {
}
