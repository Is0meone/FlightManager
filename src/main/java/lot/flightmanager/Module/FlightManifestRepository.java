package lot.flightmanager.Module;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightManifestRepository extends CrudRepository<FlightManifest, Integer> {
    // Tutaj można dodać metody wyszukiwania, np. znajdź wszystkie manifesty dla danego lotu lub pasażera
}
