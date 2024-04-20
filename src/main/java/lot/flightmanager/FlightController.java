package lot.flightmanager;

import lot.flightmanager.Models.Flight;
import lot.flightmanager.Models.Plane;
import lot.flightmanager.Service.FlightService;
import lot.flightmanager.Service.PlaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class FlightController {
    @Autowired
    private FlightService service;
    @Autowired
    private PlaneService planeService;

    @GetMapping("/flights")
    public String showFlightList(Model model) {
        List<Flight> listFlights = service.listAllFlights();
        model.addAttribute("listFlights", listFlights);
        return "flights/list";
    }

    @GetMapping("/flights/{id}")
    public String showFlightDetails(@PathVariable("id") Integer id, Model model) {
        Flight flight = service.getFlightById(id);
        if (flight != null) {
            model.addAttribute("flight", flight);
            return "flights/details";
        }
        return "redirect:/flights"; // Redirect to the list if flight with given ID does not exist
    }

    @GetMapping("/flights/new")
    public String showNewForm(Model model) {
        model.addAttribute("flight", new Flight());
        List<Plane> planeList = planeService.listAll();
        model.addAttribute("planes",planeList);
        return "flights/form";
    }

    @PostMapping("/flights/add")
    public String addFlight(@ModelAttribute("flight") Flight flight) {
        service.saveFlight(flight);
        return "index";
    }
}