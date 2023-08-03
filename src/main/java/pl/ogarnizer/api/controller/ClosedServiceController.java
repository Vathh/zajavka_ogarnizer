package pl.ogarnizer.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.ogarnizer.api.dto.mapper.ClosedServiceMapper;
import pl.ogarnizer.business.ClosedServiceService;

@Controller
@AllArgsConstructor
public class ClosedServiceController {
    static final String CLOSED_SERVICE = "/closed_service";
    static final String DELETE_CLOSED_SERVICE = "/closed_service/delete/{closedServiceId}";
    private final ClosedServiceService closedServiceService;
    private final ClosedServiceMapper closedServiceMapper;

    @GetMapping(value = CLOSED_SERVICE)
    public String closedServicesPage(
            Model model
    ) {
        var closedServices = closedServiceService.findClosedServices().stream()
                .map(closedServiceMapper::map)
                .toList();

        model.addAttribute("closedServiceDTOs", closedServices);

        return "closed_service";
    }

    @DeleteMapping(value = DELETE_CLOSED_SERVICE)
    public String deleteClosedService(
            @PathVariable("closedServiceId") Integer closedServiceId
    ){
        closedServiceService.deleteClosedService(closedServiceId);
        return "redirect:/closed_service";
    }
}
