package lot.flightmanager;

import lot.flightmanager.Module.Passenger;
import lot.flightmanager.Module.PassengerRepository;
import lot.flightmanager.Module.Status;
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
@Rollback(value = false)
public class PassengerRepositoryTests {
    @Autowired
    private PassengerRepository repo;

    @Test
    public void testAddNew() {
        Passenger passenger = new Passenger();
        passenger.setName("John");
        passenger.setSurname("Doe");
        passenger.setPhone(123456789);
        passenger.setStatus(Status.NORMAL);

        Passenger savedPassenger = repo.save(passenger);

        assertThat(savedPassenger).isNotNull();
        assertThat(savedPassenger.getId_Passenger()).isGreaterThan(0);
    }

    @Test
    public void testListAll() {
        List<Passenger> passengers = repo.findAll();
        assertThat(passengers).isNotEmpty();

        for (Passenger p : passengers) {
            System.out.println(p);
        }
    }

    @Test
    public void testUpdate() {
        Integer idPassenger = 1; // assuming this is a valid passenger ID
        Optional<Passenger> optionalPassenger = repo.findById(idPassenger);

        if (optionalPassenger.isPresent()) {
            Passenger passenger = optionalPassenger.get();
            passenger.setStatus(Status.GOLD);
            Passenger updatedPassenger = repo.save(passenger);

            assertThat(updatedPassenger).isNotNull();
            assertThat(updatedPassenger.getStatus()).isEqualTo(Status.GOLD);
        } else {
            throw new AssertionError("Passenger not found with id: " + idPassenger);
        }
    }

    @Test
    public void testDelete() {
        Integer idPassenger = 1; // assuming this is a valid passenger ID
        repo.deleteById(idPassenger);

        Optional<Passenger> optionalPassenger = repo.findById(idPassenger);
        assertThat(optionalPassenger).isNotPresent();
    }
}
