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
import pl.ogarnizer.api.dto.TaskDTO;
import pl.ogarnizer.api.dto.mapper.ClosedAwayWorkMapper;
import pl.ogarnizer.business.ClosedAwayWorkService;
import pl.ogarnizer.business.SortOption;
import pl.ogarnizer.domain.AwayWork;
import pl.ogarnizer.domain.ClosedAwayWork;

import java.util.*;
import java.util.stream.IntStream;

@Controller
@AllArgsConstructor
public class ClosedAwayWorkController {
    static final String CLOSED_AWAY_WORK = "/closed_away_work";
    static final String DELETE_CLOSED_AWAY_WORK = "/closed_away_work/delete/{closedAwayWorkId}";

    private final ClosedAwayWorkService closedAwayWorkService;
    private final ClosedAwayWorkMapper closedAwayWorkMapper;

    @GetMapping(value = CLOSED_AWAY_WORK)
    public ModelAndView closedAwayWorkPage(
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
        int pageSize = size.orElse(5);

        Sort.Direction sortDirection = (sortDir.isEmpty() || sortDir.get().length() == 0 || Objects.equals(sortDir.get(), "DESCENDING"))
                ? Sort.Direction.DESC : Sort.Direction.ASC;

        Sort sort = Sort.by(sortDirection, (sortBy.isEmpty() || sortBy.get().length() == 0) ? "createdDate" : sortBy.get());

        Pageable pageRequest = PageRequest.of(currentPage - 1, pageSize, sort);

        Map<String, ?> data = prepareNecessaryData(pageRequest, keyword.isEmpty() ? "" : keyword.get());

        return new ModelAndView("closed_away_work", data);
    }

    private Map<String, ?> prepareNecessaryData(Pageable pageRequest, String keyword){
        Page<ClosedAwayWork> closedAwayWorksPage = closedAwayWorkService.findClosedAwayWorks(pageRequest, keyword);

        var closedAwayWorks =  closedAwayWorksPage.stream()
                .map(closedAwayWorkMapper::map)
                .toList();

        var sortByFields = List.of("createdDate", "closedDate");
        var sortDirections = List.of("DESCENDING", "ASCENDING");

        Map<String, Object> data = new HashMap<>(Map.of(
                "closedAwayWorkDTOs", closedAwayWorks,
                "sortByFields", sortByFields,
                "sortDirections", sortDirections
        ));
        int totalPages = closedAwayWorksPage.getTotalPages();
        data.put("totalPages", totalPages);
        int currentPageNumber = closedAwayWorksPage.getNumber();
        data.put("currentPageNumber", currentPageNumber);

        if(totalPages > 0){
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .toList();
            data.put("pageNumbers", pageNumbers);
        }

        return data;
    }

    @DeleteMapping(value = DELETE_CLOSED_AWAY_WORK)
    public String deleteClosedAwayWork(
            @PathVariable("closedAwayWorkId") Integer closedAwayWorkId
    ){
        closedAwayWorkService.deleteClosedAwayWork(closedAwayWorkId);
        return "redirect:/closed_away_work";
    }
}
