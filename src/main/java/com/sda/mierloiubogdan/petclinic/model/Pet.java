package com.sda.mierloiubogdan.petclinic.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "pet")
public class Pet {
    @Id
    @Column(name = "id")
    private int id;
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

    public Pet(int id, String race, Date date, boolean isVaccinated, String ownerName) {
        this.id = id;
        this.race = race;
        this.date = date;
        this.isVaccinated = isVaccinated;
        this.ownerName = ownerName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
}

