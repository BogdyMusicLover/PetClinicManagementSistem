package com.sda.mierloiubogdan.petclinic.repository;

import com.sda.mierloiubogdan.petclinic.model.Vet;
import com.sda.mierloiubogdan.petclinic.utils.SessionManager;
import org.hibernate.Session;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class VetRepositoryTests {
    private final Session session = SessionManager.getSessionFactory().openSession();
    private final VetRepository vetRepository = new VetRepositoryImpl();

    @Test
    public void createVet() {
        Vet vet = new Vet(
                null,
                "Bogdan",
                "Mierloiu",
                "Bucuresti",
                "dogs",
                new ArrayList<>());
        session.persist(vet);
        Vet vetAgain = session.createNativeQuery("select * from vet order by id desc limit 1", Vet.class)
                .getSingleResult();
        System.out.println(vetAgain);
        Assertions.assertEquals(vet.getFirstName(), vetAgain.getFirstName());
        Assertions.assertEquals(vet.getLastName(), vetAgain.getLastName());
        Assertions.assertEquals(vet.getAddress(), vetAgain.getAddress());
        Assertions.assertEquals(vet.getSpeciality(), vetAgain.getSpeciality());
        Assertions.assertArrayEquals(vet.getConsults().toArray(), vetAgain.getConsults().toArray());
    }

    @Test
    public void deleteVet() {
        Vet vetLastEntry = session.createNativeQuery("select * from vet order by id desc limit 1", Vet.class)
                .getSingleResult();
        vetRepository.deleteById(vetLastEntry.getId());
        Vet findVetAgain = session.createNativeQuery("select * from vet order by id desc limit 1", Vet.class)
                .getSingleResult();
        Assertions.assertNotEquals(vetLastEntry.getId(), findVetAgain.getId());
    }

    @Test
    public void updateVet() {
        Vet vetToUpdate = session.createNativeQuery("select * from vet order by id desc limit 1", Vet.class)
                .getSingleResult();
        System.out.println(vetToUpdate);
        vetRepository.updateVetById(vetToUpdate.getId(),
                "updateTestFirstName",
                "updateTestLastName",
                "updateTestAddress",
                "updateTestSpeciality");
        System.out.println(vetToUpdate);
        Vet vetAgain = vetRepository.findById(vetToUpdate.getId()).orElse(new Vet());
        Assertions.assertEquals(vetToUpdate.getFirstName(), vetAgain.getFirstName());
        Assertions.assertEquals(vetToUpdate.getLastName(), vetAgain.getLastName());
        Assertions.assertEquals(vetToUpdate.getAddress(), vetAgain.getAddress());
        Assertions.assertEquals(vetToUpdate.getSpeciality(), vetAgain.getSpeciality());
    }


}
