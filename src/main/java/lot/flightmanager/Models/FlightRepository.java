package lot.flightmanager.Models;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FlightRepository extends CrudRepository<Flight, Integer> {
    List<Flight> findAll();
}
