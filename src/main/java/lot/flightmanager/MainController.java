package lot.flightmanager;

import lot.flightmanager.Models.Flight;
import lot.flightmanager.Service.FlightService;
import lot.flightmanager.Service.PlaneService;
import org.springframework.ui.Model;
import lot.flightmanager.Service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @Autowired
    PassengerService passengerService;
    @Autowired
    PlaneService planeService;
    @Autowired
    FlightService flightService;
    @GetMapping("")
    public String defHomePage(Model model){
        long passengerCount = passengerService.countPassengers();
        long flightCount = flightService.countFlights();
        long planeCount = planeService.countPlanes();
        model.addAttribute("passengerCount", passengerCount);
        model.addAttribute("flightCount", flightCount);
        model.addAttribute("planeCount",planeCount);
        return("index");
    }
    @GetMapping("index")
    public String defHomePageIndex(Model model){
        long passengerCount = passengerService.countPassengers();
        long flightCount = flightService.countFlights();
        long planeCount = planeService.countPlanes();
        model.addAttribute("passengerCount", passengerCount);
        model.addAttribute("flightCount", flightCount);
        model.addAttribute("planeCount",planeCount);
        return("index");
    }
}
