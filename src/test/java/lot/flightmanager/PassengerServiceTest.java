package lot.flightmanager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import lot.flightmanager.Models.FlightManifest;
import lot.flightmanager.Models.FlightManifestRepository;
import lot.flightmanager.Models.Passenger;
import lot.flightmanager.Models.FlightManifestRepository;
import lot.flightmanager.Models.PassengerRepository;
import lot.flightmanager.Service.PassengerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
@Rollback(value = true)
public class PassengerServiceTest {

    @Mock
    private PassengerRepository passengerRepository;

    @Mock
    private FlightManifestRepository flightManifestRepository;

    @InjectMocks
    private PassengerService passengerService;

    @Before
    public void setUp() {
        // Optional: Setup necessary mock behaviors here
    }

    @Test
    public void testCountPassengers() {
        when(passengerRepository.count()).thenReturn(10L);
        assertEquals(10L, passengerService.countPassengers());
    }

    @Test
    public void testListFromFlight() {
        List<FlightManifest> manifests = new ArrayList<>();
        FlightManifest manifest = new FlightManifest();
        Passenger passenger = new Passenger();
        passenger.setId_Passenger(1);
        manifest.setPassenger(passenger);
        manifests.add(manifest);

        when(flightManifestRepository.findByIdFlight(1)).thenReturn(manifests);

        List<Passenger> passengers = passengerService.listFromFlight(1);
        assertFalse(passengers.isEmpty());
        assertEquals(1, passengers.get(0).getId_Passenger().intValue());
    }

    @Test
    public void testSavePassenger() {
        Passenger passenger = new Passenger();
        passenger.setName("John Doe");
        when(passengerRepository.save(any(Passenger.class))).thenReturn(passenger);
        Passenger saved = passengerService.savePassenger(passenger);
        assertNotNull(saved);
        assertEquals("John Doe", saved.getName());
    }

    @Test
    public void testFindPassengerById() {
        Passenger passenger = new Passenger();
        passenger.setId_Passenger(1);
        when(passengerRepository.findById(1)).thenReturn(Optional.of(passenger));
        Passenger found = passengerService.findPassengerById(1);
        assertNotNull(found);
        assertEquals(1, found.getId_Passenger().intValue());
    }

    // Additional tests for removePassengersFromFlight, addPassengerToFlight, etc.
    // These would use similar mocking strategies to check correct methods are called or correct behaviors are enacted.
}
