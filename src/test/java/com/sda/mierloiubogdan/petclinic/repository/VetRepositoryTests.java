package com.sda.mierloiubogdan.petclinic.repository;

import com.sda.mierloiubogdan.petclinic.model.Vet;
import com.sda.mierloiubogdan.petclinic.utils.SessionManager;
import org.hibernate.Session;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class VetRepositoryTests {
    private final Session session = SessionManager.getSessionFactory().openSession();
    private final VetRepository vetRepository = new VetRepositoryImpl();
    @Test
    @Order(1)
    public void createVet() {
        String firstName = "Bogdan";
        String lastName = "Mierloiu";
        String address = "address";
        String speciality = "speciality";
        vetRepository.createVet( firstName, lastName, address, speciality);
        Vet vetAgain = session.createNativeQuery("select * from vet order by id desc limit 1", Vet.class)
                .getSingleResult();
        System.out.println(vetAgain);
        Assertions.assertEquals(firstName, vetAgain.getFirstName());
        Assertions.assertEquals(lastName, vetAgain.getLastName());
        Assertions.assertEquals(address, vetAgain.getAddress());
        Assertions.assertEquals(speciality, vetAgain.getSpeciality());

    }
    @Test
    @Order(3)
    public void deleteVet() {
        Vet vetLastEntry = session.createNativeQuery("select * from vet order by id desc limit 1", Vet.class)
                .getSingleResult();
        vetRepository.deleteById(vetLastEntry.getId());
        Vet findVetAgain = session.createNativeQuery("select * from vet order by id desc limit 1", Vet.class)
                .getSingleResult();
        Assertions.assertNotEquals(vetLastEntry.getId(), findVetAgain.getId());
    }
    @Test
    @Order(2)
    public void updateVet() {
        String firstName = "updateTestFirstName";
        String lastName = "updateTestLastName";
        String address = "updateTestAddress";
        String speciality = "updateTestSpeciality";
        Vet vetToUpdate = session.createNativeQuery("select * from vet order by id desc limit 1", Vet.class)
                .getSingleResult();
        System.out.println(vetToUpdate);
        vetRepository.updateVetById(vetToUpdate.getId(),
                firstName, lastName, address, speciality);
        System.out.println(vetToUpdate);
        Vet vetAgain = vetRepository.findById(vetToUpdate.getId()).orElse(new Vet());
        Assertions.assertEquals(firstName, vetAgain.getFirstName());
        Assertions.assertEquals(lastName, vetAgain.getLastName());
        Assertions.assertEquals(address, vetAgain.getAddress());
        Assertions.assertEquals(speciality, vetAgain.getSpeciality());
    }


}
