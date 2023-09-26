package pl.ogarnizer.business.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.ogarnizer.domain.AwayWork;
import pl.ogarnizer.domain.Client;

import java.util.List;
import java.util.Optional;

public interface ClientDAO {
    Optional<Client> findByName(String name);
    List<Client> findAll();
    Page<Client> findAll(Pageable pageRequest, String keyword);
    void addClient(Client client);
    void addClients(List<Client> clients);
    void deleteClient(Integer clientId);
    Optional<Client> findById(Integer clientId);
}
