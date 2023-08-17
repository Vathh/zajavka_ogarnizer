package pl.ogarnizer.api.rest.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.ogarnizer.api.dto.mapper.ClosedServiceMapper;
import pl.ogarnizer.api.rest.dto.ClosedServicesDTO;
import pl.ogarnizer.business.ClosedServiceService;

@RestController
@AllArgsConstructor
@RequestMapping(ClosedServiceRestController.API_CLOSED_SERVICE)
public class ClosedServiceRestController {

    public static final String API_CLOSED_SERVICE = "/api/closed_service";
    public static final String API_CLOSED_SERVICE_ID = "/{closedServiceId}";

    private final ClosedServiceService closedServiceService;
    private final ClosedServiceMapper closedServiceMapper;

    @GetMapping
    public ClosedServicesDTO getClosedServices(){
        return ClosedServicesDTO.builder()
                .closedServices(closedServiceService.findClosedServices().stream()
                        .map(closedServiceMapper::map).toList())
                .build();
    }

    @DeleteMapping(API_CLOSED_SERVICE_ID)
    public ResponseEntity<?> deleteClosedService(
            @PathVariable Integer closedServiceId
    ){
        try{
            closedServiceService.deleteClosedService(closedServiceId);
            return ResponseEntity.ok().build();
        } catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}
