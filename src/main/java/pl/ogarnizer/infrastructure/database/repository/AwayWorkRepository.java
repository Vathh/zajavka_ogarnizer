package pl.ogarnizer.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.ogarnizer.business.dao.AwayWorkDAO;
import pl.ogarnizer.domain.*;
import pl.ogarnizer.infrastructure.database.entity.AwayWorkEntity;
import pl.ogarnizer.infrastructure.database.repository.jpa.AwayWorkJpaRepository;
import pl.ogarnizer.infrastructure.database.repository.mapper.AwayWorkEntityMapper;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class AwayWorkRepository implements AwayWorkDAO {

    private final AwayWorkJpaRepository awayWorkJpaRepository;

    private final AwayWorkEntityMapper awayWorkEntityMapper;

    @Override
    public List<AwayWork> findAll() {
        return awayWorkJpaRepository.findAll().stream()
                .map(awayWorkEntityMapper::mapFromEntity)
                .toList();
    }

    @Override
    public Optional<AwayWork> findByAwayWorkId(Integer awayWorkId) {
        return awayWorkJpaRepository.findById(awayWorkId).map(awayWorkEntityMapper::mapFromEntity);
    }

    @Override
    public List<AwayWork> findByCreatingUser(User user) {
        return awayWorkJpaRepository.findByCreatingUser(user).stream()
                .map(awayWorkEntityMapper::mapFromEntity)
                .toList();
    }

    @Override
    public List<AwayWork> findByCreatingDate(LocalDate date) {
        return awayWorkJpaRepository.findByCreatedDate(date.toString()).stream()
                .map(awayWorkEntityMapper::mapFromEntity)
                .toList();
    }

    @Override
    public List<AwayWork> findByPriority(Priority priority) {
        return awayWorkJpaRepository.findByPriority(priority).stream()
                .map(awayWorkEntityMapper::mapFromEntity)
                .toList();
    }

    @Override
    public List<AwayWork> findByClient(Client client) {
        return awayWorkJpaRepository.findByClient(client).stream()
                .map(awayWorkEntityMapper::mapFromEntity)
                .toList();
    }

    @Override
    public List<AwayWork> findByStage(Stage stage) {
        return awayWorkJpaRepository.findByStage(stage).stream()
                .map(awayWorkEntityMapper::mapFromEntity)
                .toList();
    }

    @Override
    public void saveAwayWork(AwayWork awayWork) {
        AwayWorkEntity toSave = awayWorkEntityMapper.mapToEntity(awayWork);
        awayWorkJpaRepository.save(toSave);
    }

    @Override
    public void addAwayWork(AwayWork awayWork) {
        AwayWorkEntity awayWorkToSave = awayWorkEntityMapper.mapToEntity(awayWork);
        AwayWorkEntity awayWorkSaved = awayWorkJpaRepository.saveAndFlush(awayWorkToSave);
    }

    @Override
    public void deleteAwayWork(Integer awayWorkId) {
        awayWorkJpaRepository.deleteById(awayWorkId);
    }
}
