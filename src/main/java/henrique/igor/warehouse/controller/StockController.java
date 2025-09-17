package henrique.igor.warehouse.controller;

import henrique.igor.warehouse.controller.request.StockSaveRequest;
import henrique.igor.warehouse.controller.response.StockSavedResponse;
import henrique.igor.warehouse.mapper.IStockMapper;
import henrique.igor.warehouse.service.IStockService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("stocks")
@RequiredArgsConstructor
public class StockController {

    private final IStockService service;
    private final IStockMapper  mapper;

    @PostMapping
    @ResponseStatus(CREATED)
    StockSavedResponse save(@RequestBody final StockSaveRequest request) {
        var entity = mapper.toEntity(request);
        entity = service.save(entity);
        return mapper.toResponse(entity);
    }

    @PutMapping("{id}/active")
    @ResponseStatus(NO_CONTENT)
    void release(@PathVariable final UUID id){
        service.release(id);
    }

    @DeleteMapping("{id}/active")
    @ResponseStatus(NO_CONTENT)
    void inactive(@PathVariable final UUID id){
        service.inactive(id);
    }
}
