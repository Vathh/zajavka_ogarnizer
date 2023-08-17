package pl.ogarnizer.business.dao;

import pl.ogarnizer.domain.Stage;

import java.util.List;
import java.util.Optional;

public interface StageDAO {

    List<Stage> findAll();
    Optional<Stage> findByName(String name);
}
