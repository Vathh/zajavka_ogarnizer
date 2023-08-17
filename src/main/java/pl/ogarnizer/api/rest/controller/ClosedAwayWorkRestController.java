package pl.ogarnizer.api.rest.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.ogarnizer.api.dto.mapper.ClosedAwayWorkMapper;
import pl.ogarnizer.api.rest.dto.ClosedAwayWorksDTO;
import pl.ogarnizer.business.ClosedAwayWorkService;

@RestController
@AllArgsConstructor
@RequestMapping(ClosedAwayWorkRestController.API_CLOSED_AWAY_WORK)
public class ClosedAwayWorkRestController {

    public static final String API_CLOSED_AWAY_WORK = "/api/closed_away_work";
    public static final String API_CLOSED_AWAY_WORK_ID = "/{closedAwayWorkId}";

    private final ClosedAwayWorkService closedAwayWorkService;
    private final ClosedAwayWorkMapper closedAwayWorkMapper;

    @GetMapping
    public ClosedAwayWorksDTO getClosedAwayWorks(){
        return ClosedAwayWorksDTO.builder()
                .closedAwayWorks(closedAwayWorkService.findClosedAwayWorks().stream()
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
