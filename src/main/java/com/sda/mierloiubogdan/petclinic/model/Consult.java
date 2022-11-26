package com.sda.mierloiubogdan.petclinic.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "consult")
public class Consult {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;
    @Column(name = "date")
    private Date date;
    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "vet_id")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Vet vet;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

    public Consult() {
    }


    public Consult(Integer id, Date date, String description, Vet vet, Pet pet) {
        this.id = id;
        this.date = date;
        this.description = description;
        this.vet = vet;
        this.pet = pet;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Vet getVet() {
        return vet;
    }

    public void setVet(Vet vet) {
        this.vet = vet;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    @Override
    public String toString() {
        return "Consult{" +
                "id=" + id +
                ", date=" + date +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Consult consult)) return false;
        return Objects.equals(id, consult.id) && Objects.equals(date, consult.date) && Objects.equals(description, consult.description) && Objects.equals(vet, consult.vet) && Objects.equals(pet, consult.pet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, description, vet, pet);
    }
}
