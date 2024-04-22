package lot.flightmanager;

import lot.flightmanager.Models.Flight;
import lot.flightmanager.Models.Passenger;
import lot.flightmanager.Service.FlightService;
import lot.flightmanager.Service.PassengerService;
import lot.flightmanager.Service.PlaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashSet;
import java.util.List;

@Controller
public class PassengerController {
    @Autowired
    private PassengerService passengerService;
    @Autowired
    FlightService flightService;

    @ModelAttribute("passengerCount")
    public long populatePassengerCount() {
        System.out.println(passengerService.countPassengers());
        return passengerService.countPassengers();
    }
    @GetMapping("/passengers")
    public String showPassengersFormFlight(@RequestParam (required = false) Integer id, Model model){
        if(id==null){
            id = flightService.findMinimumId();
        }
        model.addAttribute("listPassengers",passengerService.listFromFlight(id));
        model.addAttribute("flight",flightService.getFlightById(id));
        model.addAttribute("allFlights",flightService.listAllFlights());
        return "passengers/listPassengers";
    }
    @PostMapping("/removePassengersFromFlight")
    public String removePassengersFromFlight(@RequestParam("selectedPassengers") List<Integer> selectedPassengers,@RequestParam("flightId") Integer flightId, RedirectAttributes redirectAttributes){
        Flight flight = flightService.getFlightById(flightId);
        flight.setFree_Seats(flight.getFree_Seats()-selectedPassengers.size());
        passengerService.removePassengersFromFlight(selectedPassengers,flightId);
        return "redirect:/passengers";
    }

}