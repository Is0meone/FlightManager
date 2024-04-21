package lot.flightmanager;

import org.springframework.ui.Model;
import lot.flightmanager.Service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @Autowired
    PassengerService passengerService;
    @GetMapping("")
    public String defHomePage(Model model){
        long passengerCount = passengerService.countPassengers();
        model.addAttribute("passengerCount", passengerCount);
        return("index");
    }
}
