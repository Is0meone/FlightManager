package lot.flightmanager;

import lot.flightmanager.Module.Flight;
import lot.flightmanager.Module.FlightRepository;
import lot.flightmanager.Module.Plane;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.annotation.Rollback;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class FlightRepositoryTests {
    @Autowired
    private FlightRepository repo;

    @Test
    public void testAddNew() {
        Flight flight = new Flight();
        flight.setFree_Seats(150);
        flight.setDestination("New York");
        flight.setOrigin("Los Angeles");
        LocalTime time = LocalTime.of(15,46, 12);
        LocalDate date = LocalDate.of(2024,4,28);
        flight.setTime(time);
        flight.setDate(date);
        // Assume we have a plane already saved with Id_Plane = 1
        Plane plane = new Plane();
        plane.setId_Plane(3);
        flight.setPlane(plane);

        // Save the flight object
        Flight savedFlight = repo.save(flight);

        // Assertions to check if the flight is saved successfully
        assertThat(savedFlight).isNotNull();
        assertThat(savedFlight.getId_Flight()).isGreaterThan(0);
    }

    @Test
    public void testListAll() {
        List<Flight> flights = repo.findAll();
        assertThat(flights).isNotEmpty();

        for (Flight f : flights) {
            System.out.println(f);
        }
    }

    @Test
    public void testUpdate() {
        Integer idFlight = 2; // assuming this is a valid flight ID
        Optional<Flight> optionalFlight = repo.findById(idFlight);

        if (optionalFlight.isPresent()) {
            Flight flight = optionalFlight.get();
            flight.setDestination("Chicago");
            Flight updatedFlight = repo.save(flight);

            assertThat(updatedFlight).isNotNull();
            assertThat(updatedFlight.getDestination()).isEqualTo("Chicago");
        } else {
            throw new AssertionError("Flight not found with id: " + idFlight);
        }
    }

    @Test
    public void testDelete() {
        Integer idFlight = 3; // assuming this is a valid flight ID
        repo.deleteById(idFlight);

        Optional<Flight> optionalFlight = repo.findById(idFlight);
        assertThat(optionalFlight).isNotPresent();
    }
}
