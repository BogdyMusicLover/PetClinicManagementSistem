package com.sda.mierloiubogdan.petclinic.controller;

import com.sda.mierloiubogdan.petclinic.model.Vet;
import com.sda.mierloiubogdan.petclinic.service.VetService;

import java.util.Optional;
import java.util.Scanner;

public class VetController {
    private final VetService vetService;
    final Scanner scanner = new Scanner(System.in);

    public VetController(VetService vetService) {
        this.vetService = vetService;
    }

    public void createVet() {
        System.out.println("Please insert vet first name");
        String firstName = scanner.nextLine();
        System.out.println("Please insert vet last_name");
        String lastName = scanner.nextLine();
        System.out.println("Please insert vet address");
        String address = scanner.nextLine();
        System.out.println("Please insert vet speciality");
        String speciality = scanner.nextLine();
        try {
            vetService.createVet(firstName, lastName, address, speciality);
        } catch (IllegalArgumentException e) {
            System.out.println("INVALID DATA ! " + e.getMessage());
        } catch (Exception e) {
            System.out.println("INTERNAL SERVER ERROR" + e.getMessage());
        }
    }

    public void showAllVets() {
        System.out.println("Vet List: ");
        for (Vet vet : vetService.getAllVets()) {
            System.out.println(vet.getId() + " " + vet.getFirstName() + " " + vet.getLastName());
        }
    }

    public void showVetById() {
        try {
            System.out.println("Please insert an id: ");
            int id = Integer.parseInt(scanner.nextLine().trim());
            Optional<Vet> optionalVet = vetService.findById(id);
            if (optionalVet.isPresent()) {
                System.out.println(optionalVet.get());
            } else {
                System.out.println("Vet not found");
            }
        } catch (NumberFormatException e) {
            System.err.println("INVALID ID FORMAT!");
        } catch (Exception e) {
            System.err.println("INTERNAL SERVER ERROR");
        }
    }

    public void updateVetById() {
        try {
            System.out.println("Please insert the id: ");
            int id = Integer.parseInt(scanner.nextLine().trim());
            System.out.println("Please insert vet first name");
            String firstName = scanner.nextLine();
            System.out.println("Please insert vet last_name");
            String lastName = scanner.nextLine();
            System.out.println("Please insert vet address");
            String address = scanner.nextLine();
            System.out.println("Please insert vet speciality");
            String speciality = scanner.nextLine();

            vetService.updateVetById(id, firstName, lastName, address, speciality);
        } catch (NumberFormatException e) {
            System.err.println("INVALID ID FORMAT!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.err.println("INTERNAL SERVER ERROR");
        }
    }
}
