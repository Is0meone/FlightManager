package lot.flightmanager;

import lot.flightmanager.Models.Plane;
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
public class PlaneController {
    @Autowired private PlaneService service;
    @GetMapping("/planes") //TODO: mozna przeniesdc mapping na gore
    public String showPlaneList(Model model){
        List<Plane> listPlanes = service.listAll();
        model.addAttribute("listPlanes",listPlanes);
        return "planes/planes";
    }
    @GetMapping("planes/{id}")
    public String showPlaneDetails(@PathVariable("id") Integer id, Model model) {
        Plane plane = service.getPlaneById(id);
        System.out.println("doopa");
        if (plane != null) {
            model.addAttribute("plane", plane);
            System.out.println(plane);
            return "planes/plane_details";
        }
        return "redirect:/planes"; // Redirect to the list if plane with given ID does not exist
    }
    @GetMapping("planes/newPlane")
    public String showNewForm(Model model){
        model.addAttribute("plane",new Plane());
        return "planes/plane_form";
    }
    @PostMapping("/planes/add")
    public String addPlane(@ModelAttribute("plane") Plane plane) {
        // Zapisuje samolot za pomocą serwisu
        service.save(plane);
        return "redirect:/planes";
    }
}
