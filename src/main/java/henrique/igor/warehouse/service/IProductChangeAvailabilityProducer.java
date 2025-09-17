package henrique.igor.warehouse.service;

import henrique.igor.warehouse.dto.StockStatusMessage;

public interface IProductChangeAvailabilityProducer {

    void notifyStatusChange(final StockStatusMessage message);
}
