package pl.ogarnizer.api.rest.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.ogarnizer.api.dto.OrderDTO;
import pl.ogarnizer.api.dto.TaskDTO;
import pl.ogarnizer.api.dto.UpdateTaskDTO;
import pl.ogarnizer.api.dto.mapper.OrderMapper;
import pl.ogarnizer.api.dto.mapper.TaskMapper;
import pl.ogarnizer.api.ogarnizerAPI.dao.OgarnizerAPIDAO;
import pl.ogarnizer.api.rest.dto.AwayWorksDTO;
import pl.ogarnizer.api.rest.dto.OrdersDTO;
import pl.ogarnizer.business.*;
import pl.ogarnizer.domain.Order;
import pl.ogarnizer.domain.Task;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping(OrderRestController.API_ORDER)
public class OrderRestController {

    public static final String API_ORDER = "/api/order";
    public static final String API_ORDER_ID = "/{orderId}";
    public static final String API_DELETE_ORDER = "/{orderId}/{success}/{closingUserName}";
    public static final String LOAD_RANDOM_ORDERS = "/load";

    private final OrderService orderService;
    private final ClientService clientService;
    private final PriorityService priorityService;
    private final StageService stageService;
    private final ClosingTaskService closingTaskService;
    private final OgarnizerAPIDAO ogarnizerAPIDAO;
    private final OrderMapper orderMapper;
    private final TaskMapper taskMapper;

    @GetMapping
    public OrdersDTO getOrders() {
        return OrdersDTO.builder()
                .orders(
                        orderService.findOrders().stream()
                                .map(orderMapper::map).toList())
                .build();
    }

    @GetMapping
    public OrdersDTO getOrdersPaginated(
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size,
            @RequestParam("keyword") Optional<String> keyword,
            @RequestParam("sortDir") Optional<String> sortDir,
            @RequestParam("sortBy") Optional<String> sortBy
    ){
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(4);

        Sort.Direction sortDirection = (sortDir.isEmpty() || sortDir.get().length() == 0 || Objects.equals(sortDir.get(), "DESCENDING"))
                ? Sort.Direction.DESC : Sort.Direction.ASC;

        Sort sort = Sort.by(sortDirection, (sortBy.isEmpty() || sortBy.get().length() == 0) ? "priority" : sortBy.get());

        Pageable pageRequest = PageRequest.of(currentPage - 1, pageSize, sort);

        return OrdersDTO.builder()
                .orders(
                        orderService.findOrders(pageRequest, keyword.isEmpty() ? "" : keyword.get()).stream()
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

    @GetMapping(value = LOAD_RANDOM_ORDERS)
    public void loadRandomOrders() {

        List<Order> orders = ogarnizerAPIDAO.getOrders();
        orders.forEach(order -> clientService.addClient(order.getClient()));
        List<Order> ordersToAdd = orders.stream().map(
                        order -> order.withClient(
                                clientService.findByName(
                                        order.getClient().getName())))
                .toList();
        orderService.addOrders(ordersToAdd);
    }
}
