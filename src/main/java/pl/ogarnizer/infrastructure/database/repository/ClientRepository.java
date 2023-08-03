package pl.ogarnizer.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.ogarnizer.business.dao.ClientDAO;
import pl.ogarnizer.domain.Client;
import pl.ogarnizer.domain.Service;
import pl.ogarnizer.infrastructure.database.entity.ClientEntity;
import pl.ogarnizer.infrastructure.database.entity.ServiceEntity;
import pl.ogarnizer.infrastructure.database.repository.jpa.ClientJpaRepository;
import pl.ogarnizer.infrastructure.database.repository.mapper.ClientEntityMapper;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class ClientRepository implements ClientDAO {

    private final ClientJpaRepository clientJpaRepository;

    private final ClientEntityMapper clientEntityMapper;

    @Override
    public Optional<Client> findByName(String name) {
        return clientJpaRepository.findByName(name).map(clientEntityMapper::mapFromEntity);
    }

    @Override
    public Optional<Client> findByNip(String nip) {
        return clientJpaRepository.findByNip(nip).map(clientEntityMapper::mapFromEntity);
    }

    @Override
    public List<Client> findAll() {
        return clientJpaRepository.findAll().stream()
                .map(clientEntityMapper::mapFromEntity)
                .toList();
    }

    @Override
    public void addClient(Client client) {
        ClientEntity clientToSave = clientEntityMapper.mapToEntity(client);
        ClientEntity clientSaved = clientJpaRepository.saveAndFlush(clientToSave);
    }

    @Override
    public void deleteClient(Integer clientId) {
        clientJpaRepository.deleteById(clientId);
    }
}
