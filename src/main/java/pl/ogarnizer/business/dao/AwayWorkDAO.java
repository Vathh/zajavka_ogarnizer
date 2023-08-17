package pl.ogarnizer.business.dao;

import pl.ogarnizer.domain.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AwayWorkDAO {
    List<AwayWork> findAll();
    Optional<AwayWork> findByAwayWorkId(Integer awayWorkId);
    List<AwayWork> findByCreatingUser(User user);
    List<AwayWork> findByCreatingDate(LocalDate date);
    List<AwayWork> findByPriority(Priority priority);
    List<AwayWork> findByClient(Client client);
    List<AwayWork> findByStage(Stage stage);
    void saveAwayWork(AwayWork awayWork);
    void saveAwayWorks(List<AwayWork> awayWorks);
    void addAwayWork(AwayWork awayWork);
    void deleteAwayWork(Integer awayWorkId);
}
