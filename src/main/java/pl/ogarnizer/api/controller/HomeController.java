package pl.ogarnizer.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.ogarnizer.business.AwayWorkService;
import pl.ogarnizer.business.OrderService;
import pl.ogarnizer.business.ServiceService;
import pl.ogarnizer.business.SortOption;
import pl.ogarnizer.domain.Statistics;

import java.util.List;

@Controller
@AllArgsConstructor
public class HomeController {

    static final String HOME = "/";

    private final AwayWorkService awayWorkService;
    private final OrderService orderService;
    private final ServiceService serviceService;

    @GetMapping(value = HOME)
    public String homePage(Model model) {
        Statistics awayWorksStatistics = awayWorkService.getAwayWorksStatistics();
        Statistics ordersStatistics = orderService.getOrdersStatistics();
        Statistics servicesStatistics = serviceService.getServicesStatistics();

        List<Statistics> stats = List.of(awayWorksStatistics, ordersStatistics, servicesStatistics);

        model.addAttribute("stats", stats);

        return "home";
    }
}
