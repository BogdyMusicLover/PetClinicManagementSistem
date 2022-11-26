package com.sda.mierloiubogdan.petclinic.repository.service;

import com.sda.mierloiubogdan.petclinic.model.Pet;

import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface PetService {

    void createPet(
            String race,
            Date date,
            boolean isVaccinated,
            String ownerName
    );

    Optional<Pet> findById(int id);

    void updatePetById(
            int id,
            String race,
            Date birthdate,
            boolean isVaccinated,
            String ownerName
    );

    void deletePetById(int id);

    List<Pet> getAllPets();

    void importPets() throws IOException;
}
