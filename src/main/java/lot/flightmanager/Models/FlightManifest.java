package lot.flightmanager.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "Passenger_Manifest")
public class FlightManifest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "Id_Flight", referencedColumnName = "Id_Flight")
    private Flight flight;

    @ManyToOne
    @JoinColumn(name = "Id_Passenger", referencedColumnName = "Id_Passenger")
    private Passenger passenger;

    // Getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    // Override toString if needed for debugging
    @Override
    public String toString() {
        return "FlightManifest{" +
                "id=" + id +
                ", flight=" + flight +
                ", passenger=" + passenger +
                '}';
    }
}
