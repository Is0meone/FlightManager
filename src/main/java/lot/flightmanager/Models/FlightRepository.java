package lot.flightmanager.Models;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface FlightRepository extends CrudRepository<Flight, Integer> {
    List<Flight> findAll();

    @Query("SELECT f FROM Flight f JOIN FETCH f.plane")
    List<Flight> findAllWithPlanes();
    @Query(value = "SELECT * FROM flight WHERE origin = IFNULL(:origin, origin) AND destination = IFNULL(:destination, destination) AND date = IFNULL(:date, date)", nativeQuery = true)
    List<Flight> findByOriginAndDestinationAndDate(@Param("origin") String origin, @Param("destination") String destination, @Param("date") LocalDate date);

}
