package com.sda.mierloiubogdan.petclinic.model;
import jakarta.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "pet")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "race")
    private String race;
    @Column(name = "birthdate")
    private Date date;
    @Column(name = "is_vaccinated")
    private boolean isVaccinated;
    @Column(name = "owner_name")
    private String ownerName;

    @OneToMany(mappedBy = "pet")
    private List<Consult> consults;

    public Pet() {
    }

    public Pet(Integer id, String race, Date date, boolean isVaccinated, String ownerName) {
        this.id = id;
        this.race = race;
        this.date = date;
        this.isVaccinated = isVaccinated;
        this.ownerName = ownerName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isVaccinated() {
        return isVaccinated;
    }

    public void setVaccinated(boolean vaccinated) {
        isVaccinated = vaccinated;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public List<Consult> getConsults() {
        return consults;
    }

    public void setConsults(List<Consult> consults) {
        this.consults = consults;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", race='" + race + '\'' +
                ", date=" + date +
                ", isVaccinated=" + isVaccinated +
                ", ownerName='" + ownerName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pet pet)) return false;
        return isVaccinated == pet.isVaccinated && Objects.equals(id, pet.id) && Objects.equals(race, pet.race) && Objects.equals(date, pet.date) && Objects.equals(ownerName, pet.ownerName) && Objects.equals(consults, pet.consults);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, race, date, isVaccinated, ownerName, consults);
    }
}

