package pl.ogarnizer.business.dao;

import pl.ogarnizer.domain.Client;

import java.util.List;
import java.util.Optional;

public interface ClientDAO {

    Optional<Client> findByName(String name);

    Optional<Client> findByNip(String nip);

    List<Client> findAll();

    void addClient(Client client);

    void deleteClient(Integer clientId);
}
