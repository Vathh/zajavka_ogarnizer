package pl.ogarnizer.api.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.ogarnizer.api.dto.ClientDTO;
import pl.ogarnizer.api.dto.mapper.ClientMapper;
import pl.ogarnizer.business.*;
import pl.ogarnizer.domain.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Controller
@AllArgsConstructor
public class ClientController {
    static final String CLIENT = "/client";
    static final String ADD_CLIENT = "/client/add";
    static final String DELETE_CLIENT = "/client/delete/{clientId}";
    private final ClientService clientService;
    private final AwayWorkService awayWorkService;
    private final ClosedAwayWorkService closedAwayWorkService;
    private final OrderService orderService;
    private final ClosedOrderService closedOrderService;
    private final ServiceService serviceService;
    private final ClosedServiceService closedServiceService;
    private final ClientMapper clientMapper;

    @GetMapping(value = CLIENT)
    public ModelAndView clientPage(){
        Map<String, ?> data = prepareNecessaryData();
        return new ModelAndView("client", data);
    }

    private Map<String, ?> prepareNecessaryData(){
        var clients = clientService.findClients().stream()
                .map(clientMapper::map)
                .toList();

        var clientDTO = new ClientDTO();

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userRole;
        if (user.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("admin"))){
            userRole = "admin";
        } else{
            userRole = "serviceman";
        }

        return Map.of(
                "clientDTOs", clients,
                "clientDTO", clientDTO,
                "userRole", userRole
        );
    }

    @PostMapping(value = ADD_CLIENT)
    public String addClient(
            @Valid @ModelAttribute("clientDTO") ClientDTO clientDTO,
            BindingResult bindingResult
    ) {
        if(bindingResult.hasErrors()){
            return "service";
        }

        Client client = clientMapper.map(clientDTO);

        clientService.addClient(client);

        return "redirect:/client";
    }

    @DeleteMapping(value = DELETE_CLIENT)
    public String deleteAwayWork(
            @PathVariable("clientId") Integer clientId
    ){
        List<AwayWork> awayWorks = awayWorkService.findAwayWorks().stream().filter(awayWork -> awayWork.getClient().getClientId().equals(clientId)).toList();
        awayWorks.forEach(awayWork -> awayWorkService.deleteAwayWork(awayWork.getAwayWorkId()));

        List<ClosedAwayWork> closedAwayWorks = closedAwayWorkService.findClosedAwayWorks().stream().filter(closedAwayWork -> closedAwayWork.getClient().getClientId().equals(clientId)).toList();
        closedAwayWorks.forEach(closedAwayWork -> closedAwayWorkService.deleteClosedAwayWork(closedAwayWork.getClosedAwayWorkId()));

        List<Order> orders = orderService.findOrders().stream().filter(order -> order.getClient().getClientId().equals(clientId)).toList();
        orders.forEach(order -> orderService.deleteOrder(order.getOrderId()));

        List<ClosedOrder> closedOrders = closedOrderService.findClosedOrders().stream().filter(closedOrder -> closedOrder.getClient().getClientId().equals(clientId)).toList();
        closedOrders.forEach(closedOrder -> closedOrderService.deleteClosedOrder(closedOrder.getClosedOrderId()));

        List<Service> services = serviceService.findServices().stream().filter(service -> service.getClient().getClientId().equals(clientId)).toList();
        services.forEach(service -> serviceService.deleteService(service.getServiceId()));

        List<ClosedService> closedServices = closedServiceService.findClosedServices().stream().filter(closedService -> closedService.getClient().getClientId().equals(clientId)).toList();
        closedServices.forEach(closedService -> closedServiceService.deleteClosedService(closedService.getClosedServiceId()));
        clientService.deleteClient(clientId);
        return "redirect:/client";
    }
}
