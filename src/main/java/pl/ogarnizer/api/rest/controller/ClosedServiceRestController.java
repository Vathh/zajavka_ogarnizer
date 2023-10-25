package pl.ogarnizer.api.rest.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.ogarnizer.api.dto.mapper.ClosedServiceMapper;
import pl.ogarnizer.api.rest.dto.AwayWorksDTO;
import pl.ogarnizer.api.rest.dto.ClosedServicesDTO;
import pl.ogarnizer.business.ClosedServiceService;

import java.util.Objects;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping(ClosedServiceRestController.API_CLOSED_SERVICE)
public class ClosedServiceRestController {

    public static final String API_CLOSED_SERVICE = "/api/closed_service";
    public static final String API_CLOSED_SERVICE_PAGES = "/pages";
    public static final String API_CLOSED_SERVICE_ID = "/{closedServiceId}";

    private final ClosedServiceService closedServiceService;
    private final ClosedServiceMapper closedServiceMapper;

    @GetMapping
    public ClosedServicesDTO getClosedServices() {
        return ClosedServicesDTO.builder()
                .closedServices(
                        closedServiceService.findClosedServices().stream()
                                .map(closedServiceMapper::map).toList())
                .build();
    }

    @GetMapping(API_CLOSED_SERVICE_PAGES)
    public ClosedServicesDTO getClosedServicesPaginated(
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

        Sort sort = Sort.by(sortDirection, (sortBy.isEmpty() || sortBy.get().length() == 0) ? "createdDate" : sortBy.get());

        Pageable pageRequest = PageRequest.of(currentPage - 1, pageSize, sort);

        return ClosedServicesDTO.builder()
                .closedServices(
                        closedServiceService.findClosedServices(pageRequest, keyword.isEmpty() ? "" : keyword.get()).stream()
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
