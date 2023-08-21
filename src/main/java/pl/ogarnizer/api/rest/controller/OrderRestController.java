package pl.ogarnizer.api.rest.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.ogarnizer.api.dto.OrderDTO;
import pl.ogarnizer.api.dto.TaskDTO;
import pl.ogarnizer.api.dto.UpdateTaskDTO;
import pl.ogarnizer.api.dto.mapper.OrderMapper;
import pl.ogarnizer.api.dto.mapper.TaskMapper;
import pl.ogarnizer.api.rest.dto.OrdersDTO;
import pl.ogarnizer.business.ClosingTaskService;
import pl.ogarnizer.business.OrderService;
import pl.ogarnizer.business.PriorityService;
import pl.ogarnizer.business.StageService;
import pl.ogarnizer.domain.Order;
import pl.ogarnizer.domain.Task;

@RestController
@AllArgsConstructor
@RequestMapping(OrderRestController.API_ORDER)
public class OrderRestController {

    public static final String API_ORDER = "/api/order";
    public static final String API_ORDER_ID = "/{orderId}";
    public static final String API_DELETE_ORDER = "/{orderId}/{success}/{closingUserName}";

    private final OrderService orderService;
    private final PriorityService priorityService;
    private final StageService stageService;
    private final ClosingTaskService closingTaskService;
    private final OrderMapper orderMapper;
    private final TaskMapper taskMapper;

    @GetMapping
    public OrdersDTO getOrders(){
        return OrdersDTO.builder()
                .orders(orderService.findOrders().stream()
                        .map(orderMapper::map).toList())
                .build();
    }
    @GetMapping(API_ORDER_ID)
    public OrderDTO orderDetails(
            @PathVariable Integer orderId
    ){
        return orderMapper.map(orderService.findOrderById(orderId));
    }

    @PostMapping
    public OrdersDTO addOrder(
            @Valid @RequestBody TaskDTO taskDTO
    ){
        Task task = taskMapper.map(taskDTO);
        orderService.addOrder(task);
        return OrdersDTO.builder()
                .orders(orderService.findOrders().stream()
                        .map(orderMapper::map).toList())
                .build();
    }

    @PutMapping(API_ORDER_ID)
    public ResponseEntity<?> updateOrder(
            @PathVariable Integer orderId,
            @Valid @RequestBody UpdateTaskDTO updateTaskDTO
    ){
        Order order = orderService.findOrderById(orderId);

        Order orderToSave = order
                .withUpdateInfo(updateTaskDTO.getUpdateInfo())
                .withPriority(priorityService.findPriority(updateTaskDTO.getPriorityName()))
                .withStage(stageService.findStage(updateTaskDTO.getStageName()));

        orderService.saveOrder(orderToSave);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping(API_DELETE_ORDER)
    public ResponseEntity<?> deleteOrder(
            @PathVariable Integer orderId,
            @PathVariable Boolean success,
            @PathVariable String closingUserName
    ){
        try{
            Order orderById = orderService.findOrderById(orderId);
            closingTaskService.closeOrder(orderById, closingUserName, success);
            orderService.deleteOrder(orderId);
            return ResponseEntity.ok().build();
        } catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}
