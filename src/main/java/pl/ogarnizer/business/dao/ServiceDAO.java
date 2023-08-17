package pl.ogarnizer.business.dao;

import pl.ogarnizer.domain.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ServiceDAO {

    List<Service> findAll();
    Optional<Service> findByServiceId(Integer serviceId);
    List<Service> findByCreatingUser(User user);
    List<Service> findByCreatingDate(LocalDate date);
    List<Service> findByPriority(Priority priority);
    List<Service> findByClient(Client client);
    List<Service> findByStage(Stage stage);
    void saveService(Service service);
    void saveServices(List<Service> services);
    void addService(Service service);
    void deleteService(Integer serviceId);
}
