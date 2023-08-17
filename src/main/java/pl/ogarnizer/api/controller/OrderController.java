package pl.ogarnizer.api.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.ogarnizer.api.dto.TaskDTO;
import pl.ogarnizer.api.dto.UpdateTaskDTO;
import pl.ogarnizer.api.dto.mapper.OrderMapper;
import pl.ogarnizer.api.dto.mapper.TaskMapper;
import pl.ogarnizer.api.ogarnizerAPI.dao.OgarnizerAPIDAO;
import pl.ogarnizer.business.*;
import pl.ogarnizer.domain.Order;
import pl.ogarnizer.domain.Task;

import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
public class OrderController {
    static final String ORDER = "/order";
    static final String ADD_ORDER = "/order/add";
    static final String CLOSE_ORDER = "/order/close/{orderId}/{success}";
    static final String ORDER_DETAILS = "/order/show/{orderId}";
    static final String UPDATE_ORDER = "/order/update/{orderId}";
    static final String LOAD_RANDOM_ORDERS = "/order/load";

    private final OrderService orderService;
    private final PriorityService priorityService;
    private final ClientService clientService;
    private final StageService stageService;
    private final ClosingTaskService closingTaskService;
    private final OgarnizerAPIDAO ogarnizerAPIDAO;
    private final OrderMapper orderMapper;
    private final TaskMapper taskMapper;

    @GetMapping(value = ORDER)
    public ModelAndView orderPage(){
        Map<String, ?> data = prepareNecessaryData();
        return new ModelAndView("order", data);
    }

    private Map<String, ?> prepareNecessaryData(){
        var orders =  orderService.findOrders().stream()
                .map(orderMapper::map)
                .toList();

        var taskDTO = new TaskDTO();
        var clients = clientService.findClients();
        var priorities = priorityService.findPriorities();

        return Map.of(
                "orderDTOs", orders,
                "taskDTO", taskDTO,
                "clients", clients,
                "priorities", priorities
        );
    }

    @PostMapping(value = ADD_ORDER)
    public String addOrder(
            @Valid @ModelAttribute("taskDTO") TaskDTO taskDTO,
            BindingResult bindingResult
    ) {
        if(bindingResult.hasErrors()){
            return "service";
        }

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        taskDTO.setCreatedByUserName(user.getUsername());

        Task task = taskMapper.map(taskDTO);

        orderService.addOrder(task);

        return "redirect:/order";
    }

    @GetMapping(value = LOAD_RANDOM_ORDERS)
    public String loadRandomOrders() {

        List<Order> orders = ogarnizerAPIDAO.getOrders();
        orders.forEach(order -> clientService.addClient(order.getClient()));
        List<Order> ordersToAdd = orders.stream().map(order -> order.withClient(clientService.findByName(order.getClient().getName()))).toList();

        orderService.addOrders(ordersToAdd);

        return "redirect:/order";
    }

    @DeleteMapping(value = CLOSE_ORDER)
    public String deleteAwayWork(
            @PathVariable("orderId") Integer orderId,
            @PathVariable("success") Boolean success
    ){
        Order orderById = orderService.findOrderById(orderId);
        User securityUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String closingUserName = securityUser.getUsername();

        closingTaskService.closeOrder(orderById, closingUserName, success);

        orderService.deleteOrder(orderId);
        return "redirect:/order";
    }

    @GetMapping(value = ORDER_DETAILS)
    public String orderDetailsPage(@PathVariable Integer orderId, Model model){
        var order = orderService.findOrderById(orderId);
        var task = taskMapper.map(order);
        var taskDTO = taskMapper.map(task);
        var priorities = priorityService.findPriorities();
        var stages = stageService.findStages();
        UpdateTaskDTO updateTaskDTO = new UpdateTaskDTO();

        String taskType = "ORDER";

        model.addAttribute("taskDTO", taskDTO);
        model.addAttribute("updateTaskDTO", updateTaskDTO);
        model.addAttribute("priorities", priorities);
        model.addAttribute("stages", stages);
        model.addAttribute("taskType", taskType);

        return "task_details";
    }

    @PutMapping(value = UPDATE_ORDER)
    public String updateAwayWork(
            @PathVariable Integer orderId,
            @ModelAttribute("updateTaskDTO") UpdateTaskDTO updateTaskDTO,
            BindingResult bindingResult
    ){
        var order = orderService.findOrderById(orderId);

        Order orderToSave = order
                .withUpdateInfo(updateTaskDTO.getUpdateInfo())
                .withPriority(priorityService.findPriority(updateTaskDTO.getPriorityName()))
                .withStage(stageService.findStage(updateTaskDTO.getStageName()));

        orderService.saveOrder(orderToSave);

        return "redirect:/order";
    }
}
