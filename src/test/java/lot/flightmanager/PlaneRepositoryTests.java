package lot.flightmanager;

import lot.flightmanager.Module.Plane;
import lot.flightmanager.Module.PlaneRepository;
import org.apache.catalina.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = true)
public class PlaneRepositoryTests {
    @Autowired private PlaneRepository repo;

    @Test
    public void testAddNew(){
        Plane plane = new Plane();
        plane.setCapacity(270);
        plane.setModel("911");
        plane.setProducer("Boeing");

        // Save the plane object
        Plane savedPlane = repo.save(plane);

        // Assertions to check if the plane is saved successfully
        assertThat(savedPlane).isNotNull();
        assertThat(savedPlane.getId_Plane()).isGreaterThan(0);
     //   assertThat(savedPlane.getCapacity()).isEqualTo(270);
      //  assertThat(savedPlane.getModel()).isEqualTo("Boeing 737");
      //  assertThat(savedPlane.getProducer()).isEqualTo("Boeing");
    }
    @Test
    public void testListAll() {
        List<Plane> planes = repo.findAll();
        assertThat(planes.size()).isGreaterThan(0);

        for (Plane p : planes) {
            System.out.println(p);
        }
    }
    @Test
    public void testUpdate() {
        Integer idPlane = 2;
        Optional<Plane> optionalPlane = repo.findById(idPlane);

        if (optionalPlane.isPresent()) {
            Plane plane = optionalPlane.get();
            plane.setModel("Updated Model");
            Plane updatedPlane = repo.save(plane);

            assertThat(updatedPlane).isNotNull();
            assertThat(updatedPlane.getModel()).isEqualTo("Updated Model");
        } else {
            throw new AssertionError("Plane not found with id: " + idPlane);
        }
    }
    @Test
    public void testDelete(){
        Integer Id_Plane = 2;
        repo.deleteById(Id_Plane);

        Optional<Plane> optionalPlane = repo.findById(Id_Plane);
        assertThat(optionalPlane).isNotPresent();
    }

}
