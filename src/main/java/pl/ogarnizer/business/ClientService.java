package pl.ogarnizer.business;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.ogarnizer.business.dao.ClientDAO;
import pl.ogarnizer.domain.Client;
import pl.ogarnizer.domain.exception.NotFoundException;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class ClientService {

    private final ClientDAO clientDAO;

    @Transactional
    public List<Client> findClients(){
        return clientDAO.findAll();
    }

    @Transactional
    public Client findByName(String name){
        Optional<Client> client = clientDAO.findByName(name);
        if(client.isEmpty()){
            throw new NotFoundException("Could not find client by client name: [%s]".formatted(client));
        }
        return client.get();
    }

    @Transactional
    public void addClient(Client client){
        clientDAO.addClient(client);
    }

    @Transactional
    public void addClients(List<Client> clients){
        clientDAO.addClients(clients);
    }

    @Transactional
    public void deleteClient(Integer clientId) {
        clientDAO.deleteClient(clientId);
    }

    public Client findByClientId(Integer clientId) {
        Optional<Client> client = clientDAO.findById(clientId);
        if(client.isEmpty()){
            throw new NotFoundException("Could not find client by client id: [%s]".formatted(clientId));
        }
        return client.get();
    }
}
