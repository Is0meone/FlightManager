package lot.flightmanager;

import lot.flightmanager.Service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class PassengerController {
    @Autowired
    private PassengerService passengerService;

    @ModelAttribute("passengerCount")
    public long populatePassengerCount() {
        System.out.println(passengerService.countPassengers());
        return passengerService.countPassengers();
    }

}