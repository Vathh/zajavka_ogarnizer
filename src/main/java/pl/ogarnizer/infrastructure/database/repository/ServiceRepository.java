package pl.ogarnizer.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.ogarnizer.business.dao.ServiceDAO;
import pl.ogarnizer.domain.*;
import pl.ogarnizer.infrastructure.database.entity.ServiceEntity;
import pl.ogarnizer.infrastructure.database.repository.jpa.ServiceJpaRepository;
import pl.ogarnizer.infrastructure.database.repository.mapper.ServiceEntityMapper;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class ServiceRepository implements ServiceDAO {

    private final ServiceJpaRepository serviceJpaRepository;
    private final ServiceEntityMapper serviceEntityMapper;

    @Override
    public List<Service> findAll() {
        return serviceJpaRepository.findAll().stream()
                .map(serviceEntityMapper::mapFromEntity)
                .toList();
    }

    @Override
    public Optional<Service> findByServiceId(Integer serviceId) {
        return serviceJpaRepository.findById(serviceId).map(serviceEntityMapper::mapFromEntity);
    }

    @Override
    public List<Service> findByCreatingUser(User user) {
        return serviceJpaRepository.findByCreatingUser(user).stream()
                .map(serviceEntityMapper::mapFromEntity)
                .toList();
    }

    @Override
    public List<Service> findByCreatingDate(LocalDate date) {
        return serviceJpaRepository.findByCreatedDate(date.toString()).stream()
                .map(serviceEntityMapper::mapFromEntity)
                .toList();
    }

    @Override
    public List<Service> findByPriority(Priority priority) {
        return serviceJpaRepository.findByPriority(priority).stream()
                .map(serviceEntityMapper::mapFromEntity)
                .toList();
    }

    @Override
    public List<Service> findByClient(Client client) {
        return serviceJpaRepository.findByClient(client).stream()
                .map(serviceEntityMapper::mapFromEntity)
                .toList();
    }

    @Override
    public List<Service> findByStage(Stage stage) {
        return serviceJpaRepository.findByStage(stage).stream()
                .map(serviceEntityMapper::mapFromEntity)
                .toList();
    }

    @Override
    public void saveService(Service service) {
        ServiceEntity toSave = serviceEntityMapper.mapToEntity(service);
        serviceJpaRepository.save(toSave);
    }

    @Override
    public void saveServices(List<Service> services) {
        List<ServiceEntity> serviceEntities = services.stream().map(serviceEntityMapper::mapToEntity).toList();
        serviceJpaRepository.saveAllAndFlush(serviceEntities);
    }

    @Override
    public void addService(Service service) {
        ServiceEntity serviceToSave = serviceEntityMapper.mapToEntity(service);
        serviceJpaRepository.saveAndFlush(serviceToSave);
    }

    @Override
    public void deleteService(Integer serviceId) {
        serviceJpaRepository.deleteById(serviceId);
    }
}
