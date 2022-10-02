package com.sda.mierloiubogdan.petclinic.service;

import com.sda.mierloiubogdan.petclinic.repository.PetRepository;

import java.sql.Date;

public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;

    public PetServiceImpl(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public void createPet(String race, Date birthdate, boolean isVaccinated, String ownerName) {
        if (race == null || race.isBlank()) {
            throw new IllegalArgumentException("Race is INVALID");
        }
        if (birthdate == null) {
            throw new IllegalArgumentException("Date is INVALID");
        }
        if (ownerName == null || ownerName.isBlank()) {
            throw new IllegalArgumentException("Owner name is INVALID");
        }

        petRepository.createPet(race, birthdate, isVaccinated, ownerName);
    }
}

