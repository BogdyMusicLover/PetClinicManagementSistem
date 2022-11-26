package com.sda.mierloiubogdan.petclinic.controller;

import com.sda.mierloiubogdan.petclinic.model.Pet;
import com.sda.mierloiubogdan.petclinic.repository.service.PetService;

import java.io.IOException;
import java.sql.Date;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Scanner;

public class PetController {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MM-dd-yyyy");
    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    private final Scanner scanner = new Scanner(System.in);


    public void createPet() {
        try {
            System.out.println("Please insert the race: ");
            String race = scanner.nextLine();
            System.out.println("Please insert the birth date of this pet in this format : MM-dd-yyyy: ");
            String dateString = scanner.nextLine();
            Date date = Date.valueOf(LocalDate.parse(dateString, FORMATTER));
            System.out.println("Please insert TRUE if the pet is vaccinated or FALSE if the pet is not vaccinated");
            boolean isVaccinated = Boolean.parseBoolean(scanner.nextLine());
            System.out.println("Please insert the owner name for this pet");
            String ownerName = scanner.nextLine();

            petService.createPet(race, date, isVaccinated, ownerName);
        } catch (IllegalArgumentException | DateTimeException e) {
            System.err.println(" INVALID DATA " + e.getMessage());
        } catch (Exception e) {
            System.err.println(" INTERNAL SERVER ERROR" + e.getMessage());
        }
    }

    public void showAllPets() {
        System.out.println("Pet List");
        for (Pet pet : petService.getAllPets()) {
            System.out.println(pet.getId() + ", " + pet.getRace() + ", " + pet.getOwnerName());
        }
    }

    public void updatePetById() {
        try {
            System.out.println("Please insert the id");
            int id = Integer.parseInt(scanner.nextLine());
            System.out.println("Please insert the race: ");
            String race = scanner.nextLine();
            System.out.println("Please insert the birth date of this pet in this format : MM-dd-yyyy: ");
            String dateString = scanner.nextLine();
            Date date = Date.valueOf(LocalDate.parse(dateString, FORMATTER));
            System.out.println("Please insert TRUE if the pet is vaccinated or FALSE if the pet is not vaccinated");
            boolean isVaccinated = Boolean.parseBoolean(scanner.nextLine());
            System.out.println("Please insert the owner name for this pet");
            String ownerName = scanner.nextLine();

            petService.updatePetById(id, race, date, isVaccinated, ownerName);
        } catch (IllegalArgumentException | DateTimeException e) {
            System.err.println(" INVALID DATA " + e.getMessage());
        } catch (Exception e) {
            System.err.println(" INTERNAL SERVER ERROR " + e.getMessage());
        }
    }

    public void deletePetById() {
        try {
            System.out.println("Please insert the id of pet you want to delete:");
            int id = Integer.parseInt(scanner.nextLine());
            petService.deletePetById(id);
        } catch (NumberFormatException e) {
            System.err.println("INVALID ID FORMAT");
        } catch (Exception e) {
            System.err.println("INTERNAL SERVER ERROR");
        }

    }

    public void importPets() {
        try {
            System.out.println("Import pets started");
            petService.importPets();
            System.out.println("Import vets finished");
        } catch (IOException e) {
            System.out.println("IMPORT PETS FAILED!" + e.getMessage());
        }
    }

    public void showPetById() {
        try {
            System.out.println("Insert the id for the pet you want to find");
            int id = Integer.parseInt(scanner.nextLine());
            Optional<Pet> optionalPet = petService.findById(id);
            if (optionalPet.isPresent()) {
                System.out.println(optionalPet.get());
            } else {
                System.out.println("Pet not found");
            }
        } catch (NumberFormatException e) {
            System.err.println("INVALID ID FORMAT");
        } catch (Exception e) {
            System.err.println("INTERNAL SERVER ERROR");
        }
    }
}
