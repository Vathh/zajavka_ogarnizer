package pl.ogarnizer.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import pl.ogarnizer.business.dao.ServiceDAO;
import pl.ogarnizer.domain.AwayWork;
import pl.ogarnizer.domain.Service;
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
    public Page<Service> findAll(Pageable pageRequest, String keyword) {

        if(keyword == null || keyword.isEmpty()){
            return serviceJpaRepository
                    .findAll(pageRequest)
                    .map(serviceEntityMapper::mapFromEntity);
        }

        return serviceJpaRepository.findAllByKeywordAndSort(keyword,
                        pageRequest)
                .map(serviceEntityMapper::mapFromEntity);
    }

    @Override
    public Optional<Service> findByServiceId(Integer serviceId) {
        return serviceJpaRepository.findById(serviceId).map(serviceEntityMapper::mapFromEntity);
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
