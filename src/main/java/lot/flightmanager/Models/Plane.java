package lot.flightmanager.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "Plane")
public class Plane {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id_Plane;
    @Column(nullable = false)
    private Integer Capacity;
    @Column(nullable = false,length = 30)
    private String Model;
    @Column(nullable = false,length = 30)
    private String Producer;

    public Integer getId_Plane() {
        return Id_Plane;
    }

    public void setId_Plane(Integer id_Plane) {
        Id_Plane = id_Plane;
    }

    public Integer getCapacity() {
        return Capacity;
    }

    public void setCapacity(Integer capacity) {
        Capacity = capacity;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public String getProducer() {
        return Producer;
    }

    public void setProducer(String producer) {
        Producer = producer;
    }

    @Override
    public String toString() {
        return "Plane{" +
                "Id_Plane=" + Id_Plane +
                ", Capacity=" + Capacity +
                ", Model='" + Model + '\'' +
                ", Producer='" + Producer + '\'' +
                '}';
    }
}
