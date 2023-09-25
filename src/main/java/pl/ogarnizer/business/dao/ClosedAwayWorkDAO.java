package pl.ogarnizer.business.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.ogarnizer.domain.AwayWork;
import pl.ogarnizer.domain.ClosedAwayWork;

import java.time.LocalDate;
import java.util.List;

public interface ClosedAwayWorkDAO {
    List<ClosedAwayWork> findAll();
    Page<ClosedAwayWork> findAll(Pageable pageRequest, String keyword);
    void addClosedAwayWork(ClosedAwayWork closedAwayWork);
    void deleteClosedAwayWork(Integer closedAwayWorkId);
}
