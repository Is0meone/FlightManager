package lot.flightmanager;

import lot.flightmanager.Models.Flight;
import lot.flightmanager.Models.Plane;
import lot.flightmanager.Service.FlightService;
import lot.flightmanager.Service.PlaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
public class FlightController {
    @Autowired
    private FlightService service;
    @Autowired
    private PlaneService planeService;

    @GetMapping("/flights") //TODO: add sorting by free seats
    public String showFilteredFlights(@RequestParam(required = false) String origin,
                                      @RequestParam(required = false) String destination,
                                      @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                      Model model) {
        if(origin!=null&&origin.isEmpty()){origin=null;}
        if(destination!=null&&destination.isEmpty()){destination=null;}
        List<Flight> flights = service.findFlightsByCriteria(origin, destination, date);
        for(Flight f:flights){
            System.out.println(f.toString());
        }
        model.addAttribute("listFlights", flights);
        return "flights/flights";
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

    @GetMapping("/flights/newFlight")
    public String showNewForm(Model model) {
        model.addAttribute("flight", new Flight());
        List<Plane> planeList = planeService.listAll();
        model.addAttribute("planes",planeList);
        return "flights/addFlight";
    }

    @PostMapping("/flights/add") //TODO: Zmienic mapping zeby mial rece i nogi
    public String addFlight(@ModelAttribute("flight") Flight flight) {
        int freeSeats = flight.getPlane().getCapacity();
        flight.setFree_Seats(freeSeats);
        service.saveFlight(flight);
        return "redirect:/flights";
    }
    @PostMapping("flights/delete")
    public String deleteFlight(@RequestParam("selectedFlights") List<Integer> flightsToDel){
        for(Integer flightId : flightsToDel) {
            service.deleteFlight(flightId);
        }
        return "redirect:/flights";
    }
}