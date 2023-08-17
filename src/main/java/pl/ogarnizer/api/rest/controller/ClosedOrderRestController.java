package pl.ogarnizer.api.rest.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.ogarnizer.api.dto.mapper.ClosedOrderMapper;
import pl.ogarnizer.api.rest.dto.ClosedOrdersDTO;
import pl.ogarnizer.business.ClosedOrderService;

@RestController
@AllArgsConstructor
@RequestMapping(ClosedOrderRestController.API_CLOSED_ORDER)
public class ClosedOrderRestController {

    public static final String API_CLOSED_ORDER = "/api/closed_order";
    public static final String API_CLOSED_ORDER_ID = "/{closedOrderId}";

    private final ClosedOrderService closedOrderService;
    private final ClosedOrderMapper closedOrderMapper;

    @GetMapping
    public ClosedOrdersDTO getClosedOrders(){
        return ClosedOrdersDTO.builder()
                .closedOrders(closedOrderService.findClosedOrders().stream()
                        .map(closedOrderMapper::map).toList())
                .build();
    }

    @DeleteMapping(API_CLOSED_ORDER_ID)
    public ResponseEntity<?> deleteClosedOrder(
            @PathVariable Integer closedOrderId
    ){
        try{
            closedOrderService.deleteClosedOrder(closedOrderId);
            return ResponseEntity.ok().build();
        } catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}
