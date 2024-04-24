package lot.flightmanager.Models;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.beans.Transient;
import java.util.List;

@Repository
public interface FlightManifestRepository extends CrudRepository<FlightManifest, Integer> {
    @Query(value = "SELECT * FROM passenger_manifest WHERE id_flight = :idFlight", nativeQuery = true)
    List<FlightManifest> findByIdFlight(@Param("idFlight") Integer idFlight);
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM passenger_manifest WHERE id_flight = :idFlight AND id_passenger IN :passengerIds", nativeQuery = true)
    void removePassengersFromFlight( @Param("passengerIds") List<Integer> passengerList, @Param("idFlight") Integer flightId);
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM passenger_manifest WHERE id_flight = :idFlight", nativeQuery = true)
    void deleteByIdFlight(@Param("idFlight") Integer idFlight);
    @Query(value = "SELECT * FROM passenger_manifest WHERE id_passenger = :idPassenger", nativeQuery = true)
    List<FlightManifest> findByIdPassenger(Integer idPassenger);
}
