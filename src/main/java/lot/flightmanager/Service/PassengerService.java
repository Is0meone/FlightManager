package lot.flightmanager.Service;

import lot.flightmanager.Models.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PassengerService {
    @Autowired
    private PassengerRepository passengerRepository;

    public long countPassengers() {
        return passengerRepository.count();
    }
}