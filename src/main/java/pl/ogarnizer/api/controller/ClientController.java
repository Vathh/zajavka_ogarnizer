package pl.ogarnizer.api.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.ogarnizer.api.dto.ClientDTO;
import pl.ogarnizer.api.dto.mapper.ClientMapper;
import pl.ogarnizer.api.ogarnizerAPI.dao.OgarnizerAPIDAO;
import pl.ogarnizer.business.ClientService;
import pl.ogarnizer.business.SortOption;
import pl.ogarnizer.domain.AwayWork;
import pl.ogarnizer.domain.Client;

import java.util.*;
import java.util.stream.IntStream;

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
    public ModelAndView clientPage(
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size,
            @RequestParam("keyword") Optional<String> keyword,
            @ModelAttribute("sortOption") SortOption sortOption,
            BindingResult bindingResult
    ) throws BindException {
        if(bindingResult.hasErrors()){
            throw new BindException(bindingResult);
        }

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(4);

        Pageable pageRequest = PageRequest.of(currentPage - 1, pageSize,Sort.Direction.ASC,  "name");

        Map<String, ?> data = prepareNecessaryData(pageRequest, keyword.isEmpty() ? "" : keyword.get());

        return new ModelAndView("client", data);
    }

    private Map<String, ?> prepareNecessaryData(Pageable pageRequest, String keyword){
        Page<Client> clientsPage = clientService.findClients(pageRequest, keyword);

        var clients =  clientsPage.stream()
                .map(clientMapper::map)
                .toList();

        var clientDTO = new ClientDTO();
        var sizes = List.of(4, 8, 20);

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userRole;
        if (user.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("admin"))){
            userRole = "admin";
        } else{
            userRole = "serviceman";
        }

        Map<String, Object> data = new HashMap<>(Map.of(
                "clientDTOs", clients,
                "clientDTO", clientDTO,
                "userRole", userRole,
                "sizes", sizes
        ));
        int totalPages = clientsPage.getTotalPages();
        data.put("totalPages", totalPages);
        int currentPageNumber = clientsPage.getNumber();
        data.put("currentPageNumber", currentPageNumber);

        if(totalPages > 0){
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .toList();
            data.put("pageNumbers", pageNumbers);
        }

        return data;
    }

    @PostMapping(value = ADD_CLIENT)
    public String addClient(
            @Valid @ModelAttribute("clientDTO") ClientDTO clientDTO,
            BindingResult bindingResult
    ) throws BindException {
        if(bindingResult.hasErrors()){
            throw new BindException(bindingResult);
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
    public String deleteClient(
            @PathVariable("clientId") Integer clientId
    ){
        clientService.deleteClient(clientId);
        return "redirect:/client";
    }
}
