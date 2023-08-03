package pl.ogarnizer.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.ogarnizer.business.dao.ClosedAwayWorkDAO;
import pl.ogarnizer.domain.AwayWork;
import pl.ogarnizer.domain.Client;
import pl.ogarnizer.domain.ClosedAwayWork;
import pl.ogarnizer.domain.User;
import pl.ogarnizer.infrastructure.database.entity.AwayWorkEntity;
import pl.ogarnizer.infrastructure.database.entity.ClosedAwayWorkEntity;
import pl.ogarnizer.infrastructure.database.repository.jpa.ClosedAwayWorkJpaRepository;
import pl.ogarnizer.infrastructure.database.repository.mapper.ClosedAwayWorkEntityMapper;

import java.time.LocalDate;
import java.util.List;

@Repository
@AllArgsConstructor
public class ClosedAwayWorkRepository implements ClosedAwayWorkDAO {

    private final ClosedAwayWorkJpaRepository closedAwayWorkJpaRepository;

    private final ClosedAwayWorkEntityMapper closedAwayWorkEntityMapper;


    @Override
    public List<ClosedAwayWork> findAll() {
        return closedAwayWorkJpaRepository.findAll().stream()
                .map(closedAwayWorkEntityMapper::mapFromEntity)
                .toList();
    }

    @Override
    public List<ClosedAwayWork> findByCreatingUser(User user) {
        return closedAwayWorkJpaRepository.findByCreatingUser(user).stream()
                .map(closedAwayWorkEntityMapper::mapFromEntity)
                .toList();
    }

    @Override
    public List<ClosedAwayWork> findByCreatingDate(LocalDate date) {
        return closedAwayWorkJpaRepository.findByCreatedDate(date.toString()).stream()
                .map(closedAwayWorkEntityMapper::mapFromEntity)
                .toList();
    }

    @Override
    public List<ClosedAwayWork> findByClient(Client client) {
        return closedAwayWorkJpaRepository.findByClient(client).stream()
                .map(closedAwayWorkEntityMapper::mapFromEntity)
                .toList();
    }

    @Override
    public List<ClosedAwayWork> findByClosingUser(User user) {
        return closedAwayWorkJpaRepository.findByClosingUser(user).stream()
                .map(closedAwayWorkEntityMapper::mapFromEntity)
                .toList();
    }

    @Override
    public List<ClosedAwayWork> findByClosingDate(LocalDate date) {
        return closedAwayWorkJpaRepository.findByClosedDate(date.toString()).stream()
                .map(closedAwayWorkEntityMapper::mapFromEntity)
                .toList();
    }

    @Override
    public void addClosedAwayWork(ClosedAwayWork closedAwayWork) {
        ClosedAwayWorkEntity closedAwayWorkToSave = closedAwayWorkEntityMapper.mapToEntity(closedAwayWork);
        closedAwayWorkJpaRepository.saveAndFlush(closedAwayWorkToSave);
    }

    @Override
    public void deleteClosedAwayWork(Integer closedAwayWorkId) {
        closedAwayWorkJpaRepository.deleteById(closedAwayWorkId);
    }
}
