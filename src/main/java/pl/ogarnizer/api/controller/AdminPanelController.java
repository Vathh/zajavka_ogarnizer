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
//.requestMatchers("/login", "/error", "/images/oh_no.png").permitAll()
//        .requestMatchers(
//        "/",
//        "/away_work/**",
//        "/service/**",
//        "/order/**",
//        "/client",
//        "/client/add").hasAnyAuthority("admin", "serviceman")
//        .requestMatchers(
//        "/client/delete/**",
//        "/closed_away_work/**",
//        "/closed_service/**",
//        "/closed_order/**",
//        "/admin_panel").hasAnyAuthority("admin")