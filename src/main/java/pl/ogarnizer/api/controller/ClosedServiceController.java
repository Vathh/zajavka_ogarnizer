package pl.ogarnizer.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.ogarnizer.api.dto.mapper.ClosedServiceMapper;
import pl.ogarnizer.business.ClosedServiceService;
import pl.ogarnizer.business.SortOption;
import pl.ogarnizer.domain.ClosedService;

import java.util.*;
import java.util.stream.IntStream;

@Controller
@AllArgsConstructor
public class ClosedServiceController {
    static final String CLOSED_SERVICE = "/closed_service";
    static final String DELETE_CLOSED_SERVICE = "/closed_service/delete/{closedServiceId}";
    private final ClosedServiceService closedServiceService;
    private final ClosedServiceMapper closedServiceMapper;

    @GetMapping(value = CLOSED_SERVICE)
    public ModelAndView closedServicesPage(
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

        Sort sort = Sort.by(sortDirection, (sortBy.isEmpty() || sortBy.get().length() == 0) ? "createdDate" : sortBy.get());

        Pageable pageRequest = PageRequest.of(currentPage - 1, pageSize, sort);

        Map<String, ?> data = prepareNecessaryData(pageRequest, keyword.isEmpty() ? "" : keyword.get());

        return new ModelAndView("closed_service", data);
    }

    private Map<String, ?> prepareNecessaryData(Pageable pageRequest, String keyword){
        Page<ClosedService> closedServicesPage = closedServiceService.findClosedServices(pageRequest, keyword);

        var closedServices =  closedServicesPage.stream()
                .map(closedServiceMapper::map)
                .toList();

        var sortByFields = List.of("createdDate", "closedDate");
        var sortDirections = List.of("DESCENDING", "ASCENDING");
        var sizes = List.of(4, 8, 20);

        Map<String, Object> data = new HashMap<>(Map.of(
                "closedServiceDTOs", closedServices,
                "sortByFields", sortByFields,
                "sortDirections", sortDirections,
                "sizes", sizes
        ));
        int totalPages = closedServicesPage.getTotalPages();
        data.put("totalPages", totalPages);
        int currentPageNumber = closedServicesPage.getNumber();
        data.put("currentPageNumber", currentPageNumber);

        if(totalPages > 0){
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .toList();
            data.put("pageNumbers", pageNumbers);
        }

        return data;
    }

    @DeleteMapping(value = DELETE_CLOSED_SERVICE)
    public String deleteClosedService(
            @PathVariable("closedServiceId") Integer closedServiceId
    ){
        closedServiceService.deleteClosedService(closedServiceId);
        return "redirect:/closed_service";
    }
}
