package lot.flightmanager.Module;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PlaneRepository extends CrudRepository<Plane, Integer> {
    List<Plane> findAll();
}
