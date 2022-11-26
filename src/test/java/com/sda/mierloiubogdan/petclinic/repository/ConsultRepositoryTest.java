package com.sda.mierloiubogdan.petclinic.repository;

import com.sda.mierloiubogdan.petclinic.model.Consult;
import com.sda.mierloiubogdan.petclinic.model.Pet;
import com.sda.mierloiubogdan.petclinic.model.Vet;
import com.sda.mierloiubogdan.petclinic.utils.SessionManager;
import org.hibernate.Session;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;

public class ConsultRepositoryTest {

    private final Session session = SessionManager.getSessionFactory().openSession();

    private final ConsultRepository consultRepository = new ConsultRepositoryImpl();

    private final VetRepository vetRepository = new VetRepositoryImpl();

    private final PetRepository petRepository = new PetRepositoryImpl();

    @Test
    public void createConsult() {
        Vet vet = vetRepository.getAll().get(1);
        System.out.println(vet);
        Pet pet = petRepository.getAll().get(1);
        System.out.println(pet);
        Date date = Date.valueOf(LocalDate.of(2022,11,26));
        String description = "description";
        consultRepository.createConsult(vet, pet, date, description);
        Consult consult = consultRepository.getAll().get(consultRepository.getAll().size() - 1);
        System.out.println(consult.getVet());
        System.out.println(consult.getPet());
        Assertions.assertEquals(vet.getFirstName(), consult.getVet().getFirstName());
        Assertions.assertEquals(pet.getRace(), consult.getPet().getRace());
        Assertions.assertEquals(date, consult.getDate());
        Assertions.assertEquals(description, consult.getDescription());

        Assertions.assertEquals(vet.toString(), consult.getVet().toString());


    }



}
