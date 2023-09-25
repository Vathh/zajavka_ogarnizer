package pl.ogarnizer.business.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.ogarnizer.domain.ClosedAwayWork;
import pl.ogarnizer.domain.ClosedOrder;

import java.time.LocalDate;
import java.util.List;

public interface ClosedOrderDAO {
    List<ClosedOrder> findAll();
    Page<ClosedOrder> findAll(Pageable pageRequest, String keyword);
    void addClosedOrder(ClosedOrder closedOrder);
    void deleteClosedOrder(Integer closedOrderId);
}
