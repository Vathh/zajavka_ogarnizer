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
import pl.ogarnizer.api.dto.mapper.ClosedOrderMapper;
import pl.ogarnizer.business.ClosedOrderService;
import pl.ogarnizer.business.SortOption;
import pl.ogarnizer.domain.ClosedOrder;

import java.util.*;
import java.util.stream.IntStream;

@Controller
@AllArgsConstructor
public class ClosedOrderController {
    static final String CLOSED_ORDER = "/closed_order";
    static final String DELETE_CLOSED_ORDER = "/closed_order/delete/{closedOrderId}";
    private final ClosedOrderService closedOrderService;
    private final ClosedOrderMapper closedOrderMapper;

    @GetMapping(value = CLOSED_ORDER)
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

        return new ModelAndView("closed_order", data);
    }

    private Map<String, ?> prepareNecessaryData(Pageable pageRequest, String keyword){
        Page<ClosedOrder> closedOrdersPage = closedOrderService.findClosedOrders(pageRequest, keyword);

        var closedOrders =  closedOrdersPage.stream()
                .map(closedOrderMapper::map)
                .toList();

        var sortByFields = List.of("createdDate", "closedDate");
        var sortDirections = List.of("DESCENDING", "ASCENDING");
        var sizes = List.of(4, 8, 20);

        Map<String, Object> data = new HashMap<>(Map.of(
                "closedOrderDTOs", closedOrders,
                "sortByFields", sortByFields,
                "sortDirections", sortDirections,
                "sizes", sizes
        ));
        int totalPages = closedOrdersPage.getTotalPages();
        data.put("totalPages", totalPages);
        int currentPageNumber = closedOrdersPage.getNumber();
        data.put("currentPageNumber", currentPageNumber);

        if(totalPages > 0){
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .toList();
            data.put("pageNumbers", pageNumbers);
        }

        return data;
    }

    @DeleteMapping(value = DELETE_CLOSED_ORDER)
    public String deleteClosedOrder(
            @PathVariable("closedOrderId") Integer closedOrderId
    ){
        closedOrderService.deleteClosedOrder(closedOrderId);
        return "redirect:/closed_order";
    }
}
