package lot.flightmanager.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "Passenger")
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id_Passenger;

    @Column(nullable = false, length = 50)
    private String Name;

    @Column(nullable = false, length = 50)
    private String Surname;

    @Column(nullable = false)
    private Integer Phone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private Status status;

    // Constructors, getters, and setters

    public Integer getId_Passenger() {
        return Id_Passenger;
    }

    public void setId_Passenger(Integer id_Passenger) {
        this.Id_Passenger = id_Passenger;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        this.Surname = surname;
    }

    public Integer getPhone() {
        return Phone;
    }

    public void setPhone(Integer phone) {
        this.Phone = phone;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}

