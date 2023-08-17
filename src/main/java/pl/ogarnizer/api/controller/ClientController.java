package pl.ogarnizer.api.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.ogarnizer.api.dto.ClientDTO;
import pl.ogarnizer.api.dto.mapper.ClientMapper;
import pl.ogarnizer.api.ogarnizerAPI.dao.OgarnizerAPIDAO;
import pl.ogarnizer.business.ClientService;
import pl.ogarnizer.domain.AwayWork;
import pl.ogarnizer.domain.Client;

import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
public class ClientController {
    static final String CLIENT = "/client";
    static final String ADD_CLIENT = "/client/add";
    static final String DELETE_CLIENT = "/client/delete/{clientId}";
    static final String LOAD_RANDOM_CLIENTS = "/client/load";
    private final OgarnizerAPIDAO ogarnizerAPIDAO;
    private final ClientService clientService;
    private final ClientMapper clientMapper;

    @GetMapping(value = CLIENT)
    public ModelAndView clientPage(){
        Map<String, ?> data = prepareNecessaryData();
        return new ModelAndView("client", data);
    }

    private Map<String, ?> prepareNecessaryData(){
        var clients = clientService.findClients().stream()
                .map(clientMapper::map)
                .toList();

        var clientDTO = new ClientDTO();

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userRole;
        if (user.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("admin"))){
            userRole = "admin";
        } else{
            userRole = "serviceman";
        }

        return Map.of(
                "clientDTOs", clients,
                "clientDTO", clientDTO,
                "userRole", userRole
        );
    }

    @PostMapping(value = ADD_CLIENT)
    public String addClient(
            @Valid @ModelAttribute("clientDTO") ClientDTO clientDTO,
            BindingResult bindingResult
    ) {
        if(bindingResult.hasErrors()){
            return "service";
        }

        Client client = clientMapper.map(clientDTO);

        clientService.addClient(client);

        return "redirect:/client";
    }

    @GetMapping(value = LOAD_RANDOM_CLIENTS)
    public String loadRandomClients() {

        List<Client> clients = ogarnizerAPIDAO.getClients();

        clientService.addClients(clients);

        return "redirect:/client";
    }

    @DeleteMapping(value = DELETE_CLIENT)
    public String deleteAwayWork(
            @PathVariable("clientId") Integer clientId
    ){
        clientService.deleteClient(clientId);
        return "redirect:/client";
    }
}
