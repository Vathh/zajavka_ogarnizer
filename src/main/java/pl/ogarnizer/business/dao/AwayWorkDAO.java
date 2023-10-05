package pl.ogarnizer.business.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.ogarnizer.domain.AwayWork;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AwayWorkDAO {
    List<AwayWork> findAll();
    Page<AwayWork> findAll(Pageable pageRequest, String keyword);
    Optional<AwayWork> findByAwayWorkId(Integer awayWorkId);
//    List<AwayWork> findByCreatingDate(LocalDate date);
    void saveAwayWork(AwayWork awayWork);
    void saveAwayWorks(List<AwayWork> awayWorks);
    void addAwayWork(AwayWork awayWork);
    void deleteAwayWork(Integer awayWorkId);
    long countByPriorityName(String priorityName);
    long countByStageName(String stageName);
    long countAll();
}
