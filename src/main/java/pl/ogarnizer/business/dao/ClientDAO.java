package pl.ogarnizer.business.dao;

import pl.ogarnizer.domain.Client;

import java.util.List;
import java.util.Optional;

public interface ClientDAO {
    Optional<Client> findByName(String name);
    List<Client> findAll();
    void addClient(Client client);
    void addClients(List<Client> clients);
    void deleteClient(Integer clientId);
    Optional<Client> findById(Integer clientId);
}
