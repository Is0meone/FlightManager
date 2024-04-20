package lot.flightmanager;

import lot.flightmanager.Module.Plane;
import lot.flightmanager.Module.PlaneRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase
@Rollback(false)
public class PlaneRepositoryTests {
    @Autowired private PlaneRepository repo;

    @Test
    public void testAddNew(){
        Plane plane = new Plane();

    }
}
