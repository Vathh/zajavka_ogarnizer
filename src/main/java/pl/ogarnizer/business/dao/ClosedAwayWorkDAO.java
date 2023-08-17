package pl.ogarnizer.business.dao;

import pl.ogarnizer.domain.*;

import java.time.LocalDate;
import java.util.List;

public interface ClosedAwayWorkDAO {
    List<ClosedAwayWork> findAll();
    List<ClosedAwayWork> findByCreatingUser(User user);
    List<ClosedAwayWork> findByCreatingDate(LocalDate date);
    List<ClosedAwayWork> findByClient(Client client);
    List<ClosedAwayWork> findByClosingUser(User user);
    List<ClosedAwayWork> findByClosingDate(LocalDate date);
    void addClosedAwayWork(ClosedAwayWork closedAwayWork);
    void deleteClosedAwayWork(Integer closedAwayWorkId);
}
