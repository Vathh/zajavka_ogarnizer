package pl.ogarnizer.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.ogarnizer.api.dto.mapper.ClosedOrderMapper;
import pl.ogarnizer.business.ClosedOrderService;

@Controller
@AllArgsConstructor
public class ClosedOrderController {
    static final String CLOSED_ORDER = "/closed_order";
    static final String DELETE_CLOSED_ORDER = "/closed_order/delete/{closedOrderId}";
    private final ClosedOrderService closedOrderService;
    private final ClosedOrderMapper closedOrderMapper;

    @GetMapping(value = CLOSED_ORDER)
    public String closedServicesPage(
            Model model
    ) {
        var closedOrders = closedOrderService.findClosedOrders().stream()
                .map(closedOrderMapper::map)
                .toList();

        model.addAttribute("closedOrderDTOs", closedOrders);

        return "closed_order";
    }

    @DeleteMapping(value = DELETE_CLOSED_ORDER)
    public String deleteClosedOrder(
            @PathVariable("closedOrderId") Integer closedOrderId
    ){
        closedOrderService.deleteClosedOrder(closedOrderId);
        return "redirect:/closed_order";
    }
}
