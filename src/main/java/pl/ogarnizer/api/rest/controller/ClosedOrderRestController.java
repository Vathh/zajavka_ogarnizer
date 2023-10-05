package pl.ogarnizer.api.rest.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.ogarnizer.api.dto.mapper.ClosedOrderMapper;
import pl.ogarnizer.api.rest.dto.ClosedOrdersDTO;
import pl.ogarnizer.business.ClosedOrderService;

import java.util.Objects;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping(ClosedOrderRestController.API_CLOSED_ORDER)
public class ClosedOrderRestController {

    public static final String API_CLOSED_ORDER = "/api/closed_order";
    public static final String API_CLOSED_ORDER_ID = "/{closedOrderId}";

    private final ClosedOrderService closedOrderService;
    private final ClosedOrderMapper closedOrderMapper;

    @GetMapping
    public ClosedOrdersDTO getClosedOrders(
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size,
            @RequestParam("keyword") Optional<String> keyword,
            @RequestParam("sortDir") Optional<String> sortDir,
            @RequestParam("sortBy") Optional<String> sortBy
    ){
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Sort.Direction sortDirection = (sortDir.isEmpty() || sortDir.get().length() == 0 || Objects.equals(sortDir.get(), "DESCENDING"))
                ? Sort.Direction.DESC : Sort.Direction.ASC;

        Sort sort = Sort.by(sortDirection, (sortBy.isEmpty() || sortBy.get().length() == 0) ? "createdDate" : sortBy.get());

        Pageable pageRequest = PageRequest.of(currentPage - 1, pageSize, sort);

        return ClosedOrdersDTO.builder()
                .closedOrders(
                        closedOrderService.findClosedOrders(pageRequest, keyword.isEmpty() ? "" : keyword.get()).stream()
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
