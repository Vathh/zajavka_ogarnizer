package pl.ogarnizer.business;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.ogarnizer.api.dto.mapper.ClosedAwayWorkMapper;
import pl.ogarnizer.api.dto.mapper.ClosedOrderMapper;
import pl.ogarnizer.api.dto.mapper.ClosedServiceMapper;
import pl.ogarnizer.business.dao.ClosedAwayWorkDAO;
import pl.ogarnizer.business.dao.ClosedOrderDAO;
import pl.ogarnizer.business.dao.ClosedServiceDAO;
import pl.ogarnizer.domain.*;

import java.time.LocalDateTime;

@Slf4j
@Service
@AllArgsConstructor
public class ClosingTaskService {

    private final ClosedAwayWorkDAO closedAwayWorkDAO;
    private final ClosedOrderDAO closedOrderDAO;
    private final ClosedServiceDAO closedServiceDAO;
    private final UserService userService;
    private final ClosedAwayWorkMapper closedAwayWorkMapper;
    private final ClosedOrderMapper closedOrderMapper;
    private final ClosedServiceMapper closedServiceMapper;

    @Transactional
    public void closeAwayWork(AwayWork awayWork, String closingUserName, Boolean success){
        User closingUser = userService.findUserByName(closingUserName);
        ClosedAwayWork closedAwayWork = closedAwayWorkMapper.map(awayWork);
        ClosedAwayWork closedAwayWorkToAdd = closedAwayWork.withClosingUser(closingUser)
                .withClosedDate(LocalDateTime.now())
                .withSuccess(success);

        closedAwayWorkDAO.addClosedAwayWork(closedAwayWorkToAdd);
    };

    @Transactional
    public void closeOrder(Order order, String closingUserName, Boolean success){
        User closingUser = userService.findUserByName(closingUserName);
        ClosedOrder closedOrder = closedOrderMapper.map(order);
        ClosedOrder closedOrderToAdd = closedOrder.withClosingUser(closingUser)
                .withClosedDate(LocalDateTime.now())
                .withSuccess(success);

        closedOrderDAO.addClosedOrder(closedOrderToAdd);
    };

    @Transactional
    public void closeService(pl.ogarnizer.domain.Service service, String closingUserName, Boolean success){
        User closingUser = userService.findUserByName(closingUserName);
        ClosedService closedService = closedServiceMapper.map(service);
        ClosedService closedServiceToAdd = closedService.withClosingUser(closingUser)
                .withClosedDate(LocalDateTime.now())
                .withSuccess(success);

        closedServiceDAO.addClosedService(closedServiceToAdd);
    };
}
