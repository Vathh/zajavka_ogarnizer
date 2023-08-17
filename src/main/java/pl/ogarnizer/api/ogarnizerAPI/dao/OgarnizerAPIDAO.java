package pl.ogarnizer.api.ogarnizerAPI.dao;

import pl.ogarnizer.domain.AwayWork;
import pl.ogarnizer.domain.Client;
import pl.ogarnizer.domain.Order;
import pl.ogarnizer.domain.Service;

import java.util.List;

public interface OgarnizerAPIDAO {

    List<AwayWork> getAwayWorks();
    List<Client> getClients();
    List<Order> getOrders();
    List<Service> getServices();
}
