package pl.ogarnizer.api.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.ogarnizer.api.dto.TaskDTO;
import pl.ogarnizer.api.dto.UpdateTaskDTO;
import pl.ogarnizer.api.dto.mapper.OrderMapper;
import pl.ogarnizer.api.dto.mapper.TaskMapper;
import pl.ogarnizer.api.ogarnizerAPI.dao.OgarnizerAPIDAO;
import pl.ogarnizer.business.*;
import pl.ogarnizer.domain.AwayWork;
import pl.ogarnizer.domain.Order;
import pl.ogarnizer.domain.Task;

import java.util.*;
import java.util.stream.IntStream;

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
    public ModelAndView orderPage(
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size,
            @RequestParam("keyword") Optional<String> keyword,
            @RequestParam("sortDir") Optional<String> sortDir,
            @RequestParam("sortBy") Optional<String> sortBy,
            @ModelAttribute("sortOption") SortOption sortOption,
            BindingResult bindingResult
    ) throws BindException {
        if(bindingResult.hasErrors()){
            throw new BindException(bindingResult);
        }

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(4);

        Sort.Direction sortDirection = (sortDir.isEmpty() || sortDir.get().length() == 0 || Objects.equals(sortDir.get(), "DESCENDING"))
                ? Sort.Direction.DESC : Sort.Direction.ASC;

        Sort sort = Sort.by(sortDirection, (sortBy.isEmpty() || sortBy.get().length() == 0) ? "priority" : sortBy.get());

        Pageable pageRequest = PageRequest.of(currentPage - 1, pageSize, sort);

        Map<String, ?> data = prepareNecessaryData(pageRequest, keyword.isEmpty() ? "" : keyword.get());

        return new ModelAndView("order", data);
    }

    private Map<String, ?> prepareNecessaryData(Pageable pageRequest, String keyword){
        Page<Order> ordersPage = orderService.findOrders(pageRequest, keyword);

        var orders =  ordersPage.stream()
                .map(orderMapper::map)
                .toList();

        var taskDTO = new TaskDTO();
        var clients = clientService.findClients();
        var priorities = priorityService.findPriorities();
        var sortByFields = List.of("priority", "createdDate", "stage");
        var sortDirections = List.of("DESCENDING", "ASCENDING");
        var sizes = List.of(4, 8, 20);

        Map<String, Object> data = new HashMap<>(Map.of(
                "orderDTOs", orders,
                "taskDTO", taskDTO,
                "clients", clients,
                "priorities", priorities,
                "sortByFields", sortByFields,
                "sortDirections", sortDirections,
                "sizes", sizes
        ));
        int totalPages = ordersPage.getTotalPages();
        data.put("totalPages", totalPages);
        int currentPageNumber = ordersPage.getNumber();
        data.put("currentPageNumber", currentPageNumber);

        if(totalPages > 0){
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .toList();
            data.put("pageNumbers", pageNumbers);
        }

        return data;
    }

    @PostMapping(value = ADD_ORDER)
    public String addOrder(
            @Valid @ModelAttribute("taskDTO") TaskDTO taskDTO,
            BindingResult bindingResult
    ) throws BindException {
        if(bindingResult.hasErrors()){
            throw new BindException(bindingResult);
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
