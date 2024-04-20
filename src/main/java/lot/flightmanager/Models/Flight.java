package lot.flightmanager.Models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Flight")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id_Flight;

    @ManyToOne
    @JoinColumn(name = "Id_Plane", referencedColumnName = "Id_Plane")
    private Plane plane;

    @Column(nullable = false)
    private Integer Free_Seats;

    @Column(nullable = false, length = 50)
    private String Destination;

    @Column(nullable = false, length = 50)
    private String Origin;

    @Column
    private java.time.LocalDate Date;
    @Column(nullable = true)
    private java.time.LocalTime Time;





    // Getters and setters for all fields
    public Integer getId_Flight() {
        return Id_Flight;
    }

    public void setId_Flight(Integer id_Flight) {
        Id_Flight = id_Flight;
    }

    public Plane getPlane() {
        return plane;
    }

    public void setPlane(Plane plane) {
        this.plane = plane;
    }

    public Integer getFree_Seats() {
        return Free_Seats;
    }

    public void setFree_Seats(Integer free_Seats) {
        Free_Seats = free_Seats;
    }

    public String getDestination() {
        return Destination;
    }

    public void setDestination(String destination) {
        Destination = destination;
    }

    public String getOrigin() {
        return Origin;
    }

    public void setOrigin(String origin) {
        Origin = origin;
    }
    public LocalDate getDate() {
        return Date;
    }

    public void setDate(LocalDate date) {
        Date = date;
    }

    public java.time.LocalTime getTime() {
        return Time;
    }

    public void setTime(java.time.LocalTime time) {
        Time = time;
    }
    @Override
    public String toString() {
        return "Flight{" +
                "Id_Flight=" + Id_Flight +
                ", plane=" + plane +
                ", Free_Seats=" + Free_Seats +
                ", Destination='" + Destination + '\'' +
                ", Origin='" + Origin + '\'' +
                ", Time=" + Time +
                '}';
    }
}
