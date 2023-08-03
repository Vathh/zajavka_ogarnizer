package pl.ogarnizer.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.ogarnizer.api.dto.mapper.ClosedAwayWorkMapper;
import pl.ogarnizer.business.ClosedAwayWorkService;
import pl.ogarnizer.domain.AwayWork;

@Controller
@AllArgsConstructor
public class ClosedAwayWorkController {
    static final String CLOSED_AWAY_WORK = "/closed_away_work";
    static final String DELETE_CLOSED_AWAY_WORK = "/closed_away_work/delete/{closedAwayWorkId}";

    private final ClosedAwayWorkService closedAwayWorkService;
    private final ClosedAwayWorkMapper closedAwayWorkMapper;

    @GetMapping(value = CLOSED_AWAY_WORK)
    public String closedAwayWorkPage(
            Model model
    ) {
        var closedAwayWorks = closedAwayWorkService.findClosedAwayWorks().stream()
                .map(closedAwayWorkMapper::map)
                .toList();
        
        model.addAttribute("closedAwayWorkDTOs", closedAwayWorks);

        return "closed_away_work";
    }

    @DeleteMapping(value = DELETE_CLOSED_AWAY_WORK)
    public String deleteClosedAwayWork(
            @PathVariable("closedAwayWorkId") Integer closedAwayWorkId
    ){
        closedAwayWorkService.deleteClosedAwayWork(closedAwayWorkId);
        return "redirect:/closed_away_work";
    }
}
