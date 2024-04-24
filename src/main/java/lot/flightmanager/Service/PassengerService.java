package lot.flightmanager.Service;

import lot.flightmanager.Models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PassengerService {
    @Autowired
    private PassengerRepository passengerRepository;
    @Autowired
    private FlightManifestRepository flightManifestRepository;
    public long countPassengers() {
        return passengerRepository.count();
    }
    public List<Passenger> listFromFlight(Integer id){
        List<FlightManifest> flightManifestlist = flightManifestRepository.findByIdFlight(id);
        List<Passenger> passengerList = new ArrayList<>();
        for (FlightManifest f :flightManifestlist){
            Passenger passenger = f.getPassenger();
            passengerList.add(passenger);
        }
        return passengerList;
    }
    public void removePassengersFromFlight(List<Integer> passengerList, Integer flightId){
        flightManifestRepository.removePassengersFromFlight(passengerList,flightId);
    }
    public Passenger savePassenger(Passenger passenger){
        Passenger savedPassenger = passengerRepository.save(passenger);
        return savedPassenger; // Zakładając, że Passenger ma metodę getId()
    }
    public void addPassengerToFlight(Passenger passenger, Flight flight){
        FlightManifest flightManifest = new FlightManifest();
        flightManifest.setPassenger(passenger);
        flightManifest.setFlight(flight);

        flightManifestRepository.save(flightManifest);

    }
    public List<Passenger> findAll() {
        return passengerRepository.findAll();
    }
    public Passenger findPassengerById(Integer id) {
        return passengerRepository.findById(id).orElse(null);
    }

    public void updatePassenger(Passenger passenger) {
        passengerRepository.save(passenger);
    }
    public List<FlightManifest> findPassengersFlights(Integer idPassenger){
       return flightManifestRepository.findByIdPassenger(idPassenger);
    }
}