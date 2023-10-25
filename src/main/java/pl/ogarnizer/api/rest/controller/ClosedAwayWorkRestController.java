package pl.ogarnizer.api.rest.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.ogarnizer.api.dto.mapper.ClosedAwayWorkMapper;
import pl.ogarnizer.api.rest.dto.AwayWorksDTO;
import pl.ogarnizer.api.rest.dto.ClosedAwayWorksDTO;
import pl.ogarnizer.business.ClosedAwayWorkService;

import java.util.Objects;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping(ClosedAwayWorkRestController.API_CLOSED_AWAY_WORK)
public class ClosedAwayWorkRestController {

    public static final String API_CLOSED_AWAY_WORK = "/api/closed_away_work";
    public static final String API_CLOSED_AWAY_WORK_PAGES = "/pages";
    public static final String API_CLOSED_AWAY_WORK_ID = "/{closedAwayWorkId}";

    private final ClosedAwayWorkService closedAwayWorkService;
    private final ClosedAwayWorkMapper closedAwayWorkMapper;

    @GetMapping
    public ClosedAwayWorksDTO getClosedAwayWorks() {
        return ClosedAwayWorksDTO.builder()
                .closedAwayWorks(
                        closedAwayWorkService.findClosedAwayWorks().stream()
                                .map(closedAwayWorkMapper::map).toList())
                .build();
    }

    @GetMapping(API_CLOSED_AWAY_WORK_PAGES)
    public ClosedAwayWorksDTO getClosedAwayWorksPaginated(
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

        return ClosedAwayWorksDTO.builder()
                .closedAwayWorks(closedAwayWorkService.findClosedAwayWorks(pageRequest, keyword.isEmpty() ? "" : keyword.get())
                    .stream()
                    .map(closedAwayWorkMapper::map).toList())
                .build();
    }

    @DeleteMapping(API_CLOSED_AWAY_WORK_ID)
    public ResponseEntity<?> deleteClosedAwayWork(
            @PathVariable Integer closedAwayWorkId
    ){
        try{
            closedAwayWorkService.deleteClosedAwayWork(closedAwayWorkId);
            return ResponseEntity.ok().build();
        } catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}
