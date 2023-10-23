package pl.ogarnizer.business.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.ogarnizer.domain.AwayWork;
import pl.ogarnizer.domain.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ServiceDAO {

    List<Service> findAll();
    Page<Service> findAll(Pageable pageRequest, String keyword);
    Optional<Service> findByServiceId(Integer serviceId);
    void saveService(Service service);
    void saveServices(List<Service> services);
    void addService(Service service);
    void deleteService(Integer serviceId);
    long countByPriorityName(String priorityName);
    long countByStageName(String stageName);
    long countAll();
}
