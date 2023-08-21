package pl.ogarnizer.business;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.ogarnizer.business.dao.OrderDAO;
import pl.ogarnizer.domain.Order;
import pl.ogarnizer.domain.Task;
import pl.ogarnizer.domain.exception.NotFoundException;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class OrderService {
    private final OrderDAO orderDAO;
    private final UserService userService;
    private final PriorityService priorityService;
    private final ClientService clientService;
    private final StageService stageService;

    @Transactional
    public List<Order> findOrders(){
        return orderDAO.findAll();
    }

    @Transactional
    public Order findOrderById(Integer orderId){
        Optional<Order> orderById = orderDAO.findByOrderId(orderId);
        if(orderById.isEmpty()){
            throw new NotFoundException("Could not find Order by id: [%s]".formatted(orderId));
        }
        return orderById.get();
    }

    @Transactional
    public void addOrder(Task task){
        Order order = prepareOrder(task);
        orderDAO.addOrder(order);
    }

    @Transactional
    public void addOrders(List<Order> orders){
        orderDAO.saveOrders(orders);
    }

    @Transactional
    public void saveOrder(Order order){
        orderDAO.saveOrder(order);
    }

    @Transactional
    public void deleteOrder(Integer orderId){
        orderDAO.deleteOrder(orderId);
    }

    private Order prepareOrder(Task task){
        return Order.builder()
                .creatingUser(userService.findUserByName(task.getCreatedByUserName()))
                .createdDate(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES))
                .priority(priorityService.findPriority(task.getPriorityName()))
                .client(clientService.findByName(task.getClientName()))
                .description(task.getDescription())
                .device(task.getDevice())
                .additionalInfo(task.getAdditionalInfo())
                .stage(stageService.findStages().stream().filter(stage -> stage.getName().equals("just_added")).findFirst().get())
                .build();
    }
}
