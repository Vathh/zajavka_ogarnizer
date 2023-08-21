package pl.ogarnizer.infrastructure.ogarnizerAPI;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.ogarnizer.api.ogarnizerAPI.dao.OgarnizerAPIDAO;
import pl.ogarnizer.domain.AwayWork;
import pl.ogarnizer.domain.Client;
import pl.ogarnizer.domain.Order;
import pl.ogarnizer.domain.Service;
import pl.ogarnizer.infrastructure.ogarnizerAPI.api.AwayWorkControllerApi;
import pl.ogarnizer.infrastructure.ogarnizerAPI.api.ClientControllerApi;
import pl.ogarnizer.infrastructure.ogarnizerAPI.api.OrderControllerApi;
import pl.ogarnizer.infrastructure.ogarnizerAPI.api.ServiceControllerApi;

import java.util.List;
import java.util.Objects;

@Component
@AllArgsConstructor
public class OgarnizerAPIClientImpl implements OgarnizerAPIDAO {

    private final AwayWorkControllerApi awayWorkApi;
    private final ClientControllerApi clientApi;
    private final OrderControllerApi orderApi;
    private final ServiceControllerApi serviceApi;
    private final AwayWorkAPIMapper awayWorkMapper;
    private final ClientAPIMapper clientMapper;
    private final OrderAPIMapper orderMapper;
    private final ServiceAPIMapper serviceMapper;


    @Override
    public List<AwayWork> getAwayWorks() {
        try{
            final var body = Objects.requireNonNull(awayWorkApi.awayWorksListWithHttpInfo().block())
                    .getBody();
            return Objects.requireNonNull(body).stream().map(awayWorkMapper::map).toList();
        }catch (Exception e) {
            return List.of();
        }
    }

    @Override
    public List<Client> getClients() {
        try{
            final var body = Objects.requireNonNull(clientApi.clientListWithHttpInfo().block()).getBody();
            return Objects.requireNonNull(body).stream().map(clientMapper::map).toList();
        }catch (Exception e) {
            return List.of();
        }
    }

    @Override
    public List<Order> getOrders() {
        try{
            final var body = Objects.requireNonNull(orderApi.orderListWithHttpInfo().block()).getBody();
            return Objects.requireNonNull(body).stream().map(orderMapper::map).toList();
        }catch (Exception e) {
            return List.of();
        }
    }

    @Override
    public List<Service> getServices() {
        try{
            final var body = Objects.requireNonNull(serviceApi.serviceListWithHttpInfo().block()).getBody();
            return Objects.requireNonNull(body).stream().map(serviceMapper::map).toList();
        }catch (Exception e) {
            return List.of();
        }
    }
}
