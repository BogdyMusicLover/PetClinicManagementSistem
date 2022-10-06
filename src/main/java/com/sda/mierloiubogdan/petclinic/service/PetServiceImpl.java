package com.sda.mierloiubogdan.petclinic.service;

import com.sda.mierloiubogdan.petclinic.model.Pet;
import com.sda.mierloiubogdan.petclinic.repository.PetRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

public class PetServiceImpl implements PetService {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

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

    @Override
    public List<Pet> getAllPets() {
        return petRepository.getAll();
    }

    public Optional<Pet> findById(int id) {
        Optional<Pet> pet = petRepository.findById(id);
        return pet;
    }

    @Override
    public void updatePetById(int id, String race, Date birthdate, boolean isVaccinated, String ownerName) {
        if (id <= 0) {
            throw new IllegalArgumentException("Id is INVALID!");
        }
        if (race == null || race.isBlank()) {
            throw new IllegalArgumentException("Race is INVALID");
        }
        if (birthdate == null) {
            throw new IllegalArgumentException("Date is INVALID");
        }
        if (ownerName == null || ownerName.isBlank()) {
            throw new IllegalArgumentException("Owner name is INVALID");
        }
        petRepository.updatePetById(id, race, birthdate, isVaccinated, ownerName);
    }

    @Override
    public void deletePetById(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID IS INVALID");
        }
        petRepository.deleteById(id);
    }

    @Override
    public void importPets() throws IOException {
        Path filePath = Paths.get("C:\\Users\\Andreea\\Documents\\GitHub\\PetClinicManagementSistem\\src\\main\\resources\\Data\\Pets.txt");
        Files.lines(filePath)
                .skip(1)
                .map(lines -> lines.split("\\|"))
                .forEach(lineElements -> {
                    if (lineElements.length == 4) {
                        String race = lineElements[0];
                        Date birthDate = Date.valueOf(LocalDate.parse(lineElements[1], FORMATTER));
                        boolean isVaccinated = Boolean.parseBoolean(lineElements[2]);
                        String ownerName = lineElements[3];
                        createPet(race, birthDate, isVaccinated, ownerName);
                    }
                });
    }
}

