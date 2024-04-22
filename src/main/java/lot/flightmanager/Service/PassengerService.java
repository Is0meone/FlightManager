package lot.flightmanager.Service;

import lot.flightmanager.Models.FlightManifest;
import lot.flightmanager.Models.FlightManifestRepository;
import lot.flightmanager.Models.Passenger;
import lot.flightmanager.Models.PassengerRepository;
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
}