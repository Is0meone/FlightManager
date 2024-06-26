package lot.flightmanager.Models;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PassengerRepository extends CrudRepository<Passenger, Integer> {
    List<Passenger> findAll();
}