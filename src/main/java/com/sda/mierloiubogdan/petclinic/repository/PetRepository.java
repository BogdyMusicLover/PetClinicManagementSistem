package com.sda.mierloiubogdan.petclinic.repository;

import com.sda.mierloiubogdan.petclinic.model.Pet;

import java.sql.Date;

public interface PetRepository extends BaseRepository<Pet> {

    void createPet(
            String race,
            Date birthdate,
            boolean isVaccinated,
            String ownerName
    );

    void updatePetById(
            int id,
            String race,
            Date birthdate,
            boolean isVaccinated,
            String ownerName
    );
}
