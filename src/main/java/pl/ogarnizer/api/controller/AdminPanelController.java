package pl.ogarnizer.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@AllArgsConstructor
public class AdminPanelController {
    static final String ADMIN_PANEL = "/admin_panel";

    @RequestMapping(value = ADMIN_PANEL, method = RequestMethod.GET)
    public String adminPanelPage() {
        return "admin_panel";
    }
}