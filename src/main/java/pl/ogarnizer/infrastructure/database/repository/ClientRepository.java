package pl.ogarnizer.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import pl.ogarnizer.business.dao.ClientDAO;
import pl.ogarnizer.domain.AwayWork;
import pl.ogarnizer.domain.Client;
import pl.ogarnizer.infrastructure.database.entity.ClientEntity;
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
    public List<Client> findAll() {
        return clientJpaRepository.findAll().stream()
                .map(clientEntityMapper::mapFromEntity)
                .toList();
    }

    @Override
    public Page<Client> findAll(Pageable pageRequest, String keyword) {

        if(keyword == null || keyword.isEmpty()){
            return clientJpaRepository
                    .findAll(pageRequest)
                    .map(clientEntityMapper::mapFromEntity);
        }

        return clientJpaRepository.findAllByKeywordAndSort(keyword,
                        pageRequest)
                .map(clientEntityMapper::mapFromEntity);
    }

    @Override
    public void addClient(Client client) {
        ClientEntity clientToSave = clientEntityMapper.mapToEntity(client);
        ClientEntity clientSaved = clientJpaRepository.saveAndFlush(clientToSave);
    }

    @Override
    public void addClients(List<Client> clients) {
        List<ClientEntity> clientEntities = clients.stream().map(clientEntityMapper::mapToEntity).toList();
        clientJpaRepository.saveAllAndFlush(clientEntities);
    }

    @Override
    public void deleteClient(Integer clientId) {
        clientJpaRepository.deleteById(clientId);
    }

    @Override
    public Optional<Client> findById(Integer clientId) {
        return clientJpaRepository.findById(clientId).map(clientEntityMapper::mapFromEntity);
    }
}
