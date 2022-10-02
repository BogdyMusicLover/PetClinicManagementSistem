package com.sda.mierloiubogdan.petclinic.repository;

import com.sda.mierloiubogdan.petclinic.model.Pet;

import java.sql.Date;

public class PetRepositoryImpl extends BaseRepositoryImpl<Pet> implements PetRepository {
    public PetRepositoryImpl() {
        super(Pet.class);
    }

    @Override
    public void createPet(String race, Date birthdate, boolean isVaccinated, String ownerName) {
        Pet pet = new Pet();
        pet.setRace(race);
        pet.setDate(birthdate);
        pet.setVaccinated(isVaccinated);
        pet.setOwnerName(ownerName);
        create(pet);
    }

    @Override
    public void updatePet(int id, String race, Date birthdate, boolean isVaccinated, String ownerName) {
        Pet pet = new Pet();
        pet.setId(id);
        pet.setRace(race);
        pet.setDate(birthdate);
        pet.setVaccinated(isVaccinated);
        pet.setOwnerName(ownerName);
        updateById(id,pet);
    }
}

