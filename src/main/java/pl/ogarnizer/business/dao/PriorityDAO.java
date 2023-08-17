package pl.ogarnizer.business.dao;

import pl.ogarnizer.domain.Priority;

import java.util.List;
import java.util.Optional;

public interface PriorityDAO {
    List<Priority> findAll();
    Optional<Priority> findByName(String name);
}
