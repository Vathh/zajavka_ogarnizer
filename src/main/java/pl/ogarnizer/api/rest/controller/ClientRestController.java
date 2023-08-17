package pl.ogarnizer.api.rest.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.ogarnizer.api.dto.ClientDTO;
import pl.ogarnizer.api.dto.mapper.ClientMapper;
import pl.ogarnizer.api.rest.dto.ClientsDTO;
import pl.ogarnizer.business.ClientService;
import pl.ogarnizer.domain.Client;

@RestController
@AllArgsConstructor
@RequestMapping(ClientRestController.API_CLIENT)
public class ClientRestController {
    public static final String API_CLIENT = "/api/client";
    public static final String API_CLIENT_ID = "/{clientId}";

    private final ClientService clientService;
    private final ClientMapper clientMapper;

    @GetMapping
    public ClientsDTO getClients(){
        return ClientsDTO.builder()
                .clients(clientService.findClients().stream()
                        .map(clientMapper::map).toList())
                .build();
    }

    @GetMapping(API_CLIENT_ID)
    public ClientDTO clientDetails(
            @PathVariable Integer clientId
    ){
        return clientMapper.map(clientService.findByClientId(clientId));
    }

    @PostMapping
    public ClientsDTO addClient(
            @Valid @RequestBody ClientDTO clientDTO
    ){
        Client client = clientMapper.map(clientDTO);
        clientService.addClient(client);
        return ClientsDTO.builder()
                .clients(clientService.findClients().stream()
                        .map(clientMapper::map).toList())
                .build();
    }

    @DeleteMapping(API_CLIENT_ID)
    public ResponseEntity<?> deleteClient(
            @PathVariable Integer clientId
    ){
        try{
            clientService.deleteClient(clientId);
            return ResponseEntity.ok().build();
        } catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}
