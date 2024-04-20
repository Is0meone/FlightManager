package lot.flightmanager.Service;

import lot.flightmanager.Models.Plane;
import lot.flightmanager.Models.PlaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaneService {
    @Autowired private PlaneRepository repo;

    public List<Plane> listAll(){
        return repo.findAll();
    }
    public Plane getPlaneById(Integer id) {
        Optional<Plane> result = repo.findById(id);
        return result.orElse(null);
    }
    public void save(Plane plane){
        repo.save(plane);
    }
}
