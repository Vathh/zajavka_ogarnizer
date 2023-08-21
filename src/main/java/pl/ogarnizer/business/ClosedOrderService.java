package pl.ogarnizer.business;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.ogarnizer.business.dao.ClosedOrderDAO;
import pl.ogarnizer.domain.ClosedOrder;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ClosedOrderService {
    private final ClosedOrderDAO closedOrderDAO;

    @Transactional
    public List<ClosedOrder> findClosedOrders(){
        return closedOrderDAO.findAll();
    }

    @Transactional
    public void deleteClosedOrder(Integer closedOrderId){
        closedOrderDAO.deleteClosedOrder(closedOrderId);
    }
}
