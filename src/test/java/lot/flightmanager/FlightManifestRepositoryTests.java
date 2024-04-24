package lot.flightmanager;

import lot.flightmanager.Models.Flight;
import lot.flightmanager.Models.FlightManifest;
import lot.flightmanager.Models.FlightManifestRepository;
import lot.flightmanager.Models.Passenger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = true)
public class FlightManifestRepositoryTests {
    @Autowired
    private FlightManifestRepository repo;

    @Test
    public void testAddNew() {
        Flight flight = new Flight(); // Assuming you have a flight object
        Passenger passenger = new Passenger(); // And a passenger object
        // Normally you would fetch these from their repositories

        FlightManifest manifest = new FlightManifest();
        flight.setId_Flight(2);
        passenger.setId_Passenger(1);
        manifest.setFlight(flight);
        manifest.setPassenger(passenger);

        FlightManifest savedManifest = repo.save(manifest);

        assertThat(savedManifest).isNotNull();
        assertThat(savedManifest.getId()).isGreaterThan(0);
    }

    @Test
    public void testListAll() {
        List<FlightManifest> manifests = (List<FlightManifest>) repo.findAll();
        assertThat(manifests).isNotEmpty();

        for (FlightManifest m : manifests) {
            System.out.println(m);
        }
    }

    @Test
    public void testUpdate() {
        Integer manifestId = 1; // assuming this is a valid manifest ID
        Optional<FlightManifest> optionalManifest = repo.findById(manifestId);

        if (optionalManifest.isPresent()) {
            FlightManifest manifest = optionalManifest.get();
            Passenger newPassenger = new Passenger(); // assuming you have a new passenger
            // Normally you would fetch this from PassengerRepository

            manifest.setPassenger(newPassenger);
            FlightManifest updatedManifest = repo.save(manifest);

            assertThat(updatedManifest).isNotNull();
            assertThat(updatedManifest.getPassenger()).isEqualTo(newPassenger);
        } else {
            throw new AssertionError("Manifest not found with id: " + manifestId);
        }
    }

    @Test
    public void testDelete() {
        Integer manifestId = 1; // assuming this is a valid manifest ID
        repo.deleteById(manifestId);

        Optional<FlightManifest> optionalManifest = repo.findById(manifestId);
        assertThat(optionalManifest).isNotPresent();
    }
}
