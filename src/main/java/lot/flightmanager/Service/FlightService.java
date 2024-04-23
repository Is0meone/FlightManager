package lot.flightmanager.Service;

import lot.flightmanager.Models.Flight;
import lot.flightmanager.Models.FlightManifestRepository;
import lot.flightmanager.Models.FlightRepository;
import lot.flightmanager.Models.Passenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class FlightService {
    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private FlightManifestRepository flightManifestRepository;
    @Autowired PassengerService passengerService;

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
        flightManifestRepository.deleteByIdFlight(id);
        flightRepository.deleteById(id);
    }
    public List<Flight> findFlightsByCriteria(String origin, String destination, LocalDate date) {
        if (origin != null || destination != null || date != null) {
            return flightRepository.findByOriginAndDestinationAndDate(origin, destination, date);
        } else {
            return flightRepository.findAll();
        }
    }
    public Integer findMinimumId(){
        return flightRepository.findMinimumId();
    }

    public void updateFlight(Flight updatedFlight) {
        if (updatedFlight.getId_Flight() != null) {
                Integer freeseats = flightRepository.findById(updatedFlight.getId_Flight()).get().getFree_Seats(); //If we want to change amount of free seats, we have to remove passengers
                updatedFlight.setFree_Seats(freeseats);
                flightRepository.save(updatedFlight);
        } else {
            throw new IllegalArgumentException("Flight ID must not be null for updates.");
        }
    }
    public Integer soldTickets(Integer idFlight){ //That is very bad solution but no time :c
        return passengerService.listFromFlight(idFlight).size();
    }
}