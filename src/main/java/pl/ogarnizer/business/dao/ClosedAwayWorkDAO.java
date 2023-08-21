package pl.ogarnizer.business.dao;

import pl.ogarnizer.domain.Client;
import pl.ogarnizer.domain.ClosedAwayWork;
import pl.ogarnizer.domain.User;

import java.time.LocalDate;
import java.util.List;

public interface ClosedAwayWorkDAO {
    List<ClosedAwayWork> findAll();
    List<ClosedAwayWork> findByCreatingDate(LocalDate date);
    void addClosedAwayWork(ClosedAwayWork closedAwayWork);
    void deleteClosedAwayWork(Integer closedAwayWorkId);
}
