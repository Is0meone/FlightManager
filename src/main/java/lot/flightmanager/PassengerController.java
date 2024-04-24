package lot.flightmanager;

import lot.flightmanager.Models.Flight;
import lot.flightmanager.Models.FlightManifest;
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

import java.util.ArrayList;
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
    @PostMapping("passengers/removePassengersFromFlight")
    public String removePassengersFromFlight(@RequestParam("selectedPassengers") List<Integer> selectedPassengers,
                                             @RequestParam("flightId") Integer flightId){
        Flight flight = flightService.getFlightById(flightId);
        flight.setFree_Seats(flight.getFree_Seats()+selectedPassengers.size());
        passengerService.removePassengersFromFlight(selectedPassengers,flightId);
        return "redirect:/passengers?id=" + flightId;
    }
    @PostMapping("/passengers/newPassenger")
    public String showNewForm(@RequestParam(name = "addOption") String addOption,
                               @RequestParam(name = "flightId", required = false) Integer flightId,
                               Model model) {
        model.addAttribute("passenger",new Passenger());
        model.addAttribute("choosenOption",addOption);
        model.addAttribute("forwardedId",flightId);

        return "passengers/addPassenger";
    }
    @PostMapping("/passengers/newPassenger/add")
    public String addPassenger(@ModelAttribute("passenger") Passenger passenger,
                               @RequestParam("option") String option,
                               @RequestParam("flightId") Integer flightId) {
        passengerService.savePassenger(passenger);

        if(option.equals("flight")){
            Flight flight = flightService.getFlightById(flightId);
            passengerService.addPassengerToFlight(passenger,flight);
            flight.setFree_Seats(flight.getFree_Seats()-1);
            flightService.saveFlight(flight);
            return "redirect:/passengers?id=" + flightId;
        }
        System.out.println(passenger.toString());

        return "redirect:/passengers";
    }
    @GetMapping("/passengers/listAllPassengers")
    public String allPassengers(Model model) {
        List<Passenger> passengers = passengerService.findAll(); // Make sure you have a method to find all passengers
        model.addAttribute("allPassengers", passengers);
        return "passengers/listAllPassengers"; // Name of the Thymeleaf template for showing all passengers
    }

    @GetMapping("/passengers/modify")
    public String showModifyPassengerForm(@RequestParam("id") Integer id, Model model) {
        Passenger passenger = passengerService.findPassengerById(id);
        if (passenger == null) {
            return "redirect:/passengers"; // Redirect to the passenger list if no passenger is found
        }
        List<FlightManifest> list = passengerService.findPassengersFlights(id);
        List<Flight> flightList = new ArrayList<>();
        for (FlightManifest i: list){
            flightList.add(i.getFlight());
        }
        model.addAttribute("passenger", passenger);
        model.addAttribute("id_Passenger", id);
        model.addAttribute("flights",flightList);
        return "passengers/modifyPassenger"; // Thymeleaf template to modify the passenger
    }

    @PostMapping("/passengers/update")
    public String updatePassenger(@ModelAttribute("passenger") Passenger passenger, @RequestParam("idPassenger") Integer passengerId) {
        passenger.setId_Passenger(passengerId);
        passengerService.updatePassenger(passenger);

        return "redirect:/passengers";
    }

}