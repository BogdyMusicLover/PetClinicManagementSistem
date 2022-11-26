package com.sda.mierloiubogdan.petclinic.model;
import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "vet")
public class Vet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "address")
    private String address;
    @Column(name = "speciality")
    private String speciality;

    @OneToMany(mappedBy = "vet")
    private List<Consult> consults;

    public Vet() {
    }

    public Vet(Integer id, String firstName, String lastName, String address, String speciality, List<Consult> consults) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.speciality = speciality;
        this.consults = consults;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public List<Consult> getConsults() {
        return consults;
    }

    public void setConsults(List<Consult> consults) {
        this.consults = consults;
    }

    @Override
    public String toString() {
        return "Vet{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", speciality='" + speciality + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vet vet)) return false;
        return Objects.equals(id, vet.id) && Objects.equals(firstName, vet.firstName) && Objects.equals(lastName, vet.lastName) && Objects.equals(address, vet.address) && Objects.equals(speciality, vet.speciality) && Objects.equals(consults, vet.consults);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, address, speciality, consults);
    }
}
