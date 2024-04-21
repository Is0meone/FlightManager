package lot.flightmanager.Service;

import lot.flightmanager.Models.Flight;
import lot.flightmanager.Models.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class FlightService {
    @Autowired
    private FlightRepository flightRepository;

    public List<Flight> listAllFlights() {
        return flightRepository.findAllWithPlanes();
    }

    public void saveFlight(Flight flight) {
        flightRepository.save(flight);
    }

    public Flight getFlightById(Integer id) {
        Optional<Flight> flight = flightRepository.findById(id);
        return flight.orElse(null);
    }
    public void deleteFlight(Integer id) {
        flightRepository.deleteById(id);
    }
    public List<Flight> findFlightsByCriteria(String origin, String destination, LocalDate date) {
        if (origin != null || destination != null || date != null) {
            return flightRepository.findByOriginAndDestinationAndDate(origin, destination, date);
        } else {
            return flightRepository.findAll();
        }
    }
}