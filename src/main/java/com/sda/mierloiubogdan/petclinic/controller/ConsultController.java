package com.sda.mierloiubogdan.petclinic.controller;

import com.sda.mierloiubogdan.petclinic.model.Consult;
import com.sda.mierloiubogdan.petclinic.repository.service.ConsultService;

import java.io.IOException;
import java.sql.Date;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ConsultController {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MM-dd-yyyy");
    private final ConsultService consultService;

    private final Scanner scanner = new Scanner(System.in);


    public ConsultController(ConsultService consultService) {
        this.consultService = consultService;
    }

    public void createConsult() {
        try {
            System.out.println("Please insert the vet ID");
            int vetId = Integer.parseInt(scanner.nextLine());
            System.out.println("Please insert the pet ID");
            int petId = Integer.parseInt(scanner.nextLine());
            System.out.println("Please insert the date of consult in this format MM-dd-yyyy");
            Date date = Date.valueOf(LocalDate.parse(scanner.nextLine(), FORMATTER));
            System.out.println("Please insert the description of consult");
            String description = scanner.nextLine();

            consultService.createConsult(vetId, petId, date, description);
        } catch (IllegalArgumentException | DateTimeException e) {
            System.out.println("INVALID DATA" + e.getMessage());
        } catch (Exception e) {
            System.out.println("INTERNAL SERVER ERROR" + e.getMessage());
        }
    }

    public void showAllConsults() {
        List<Consult> allConsults = consultService.getAllConsult();
        if (!allConsults.isEmpty()) {
            System.out.println("This is all consults");
            for (Consult consult : allConsults) {
                System.out.println(consult.getId()
                        + ", VET_ID: " + consult.getVet().getId()
                        + ", PET_ID: " + consult.getPet().getId()
                        + ", DATE: " + consult.getDate()
                        + ", DESCRIPTION: " + consult.getDescription());
            }
        } else {
            System.out.println("You don't have any consult");
        }
    }


    public void updateConsultByID() {
        try {
            System.out.println("Please insert the id of consult you want to update:");
            int id = Integer.parseInt(scanner.nextLine());
            System.out.println("Insert the new description of consult:");
            String description = scanner.nextLine();
            consultService.updateConsultById(id, description);
        } catch (NumberFormatException e) {
            System.err.println("INVALID ID FORMAT");
        } catch (Exception e) {
            System.err.println("INTERNAL SERVER ERROR" + e.getMessage());
        }
    }

    public void showConsultById() {
        try {
            System.out.println("Insert the id of consult you want to find");
            int id = Integer.parseInt(scanner.nextLine());
            Optional<Consult> consultOptional = consultService.findConsultById(id);
            if (consultOptional.isPresent()) {
                System.out.println(consultOptional.get());
            } else {
                System.out.println("Consult not found");
            }
        } catch (NumberFormatException e) {
            System.err.println("INVALID ID FORMAT " + e.getMessage());
        } catch (Exception e) {
            System.err.println("INTERNAL SERVER ERROR " + e.getMessage());
        }
    }

    public void deleteById() {
        try {
            System.out.println("Insert the id of consult you want to delete");
            int id = Integer.parseInt(scanner.nextLine());
            consultService.deleteById(id);
        } catch (NumberFormatException e) {
            System.err.println("INVALID ID FORMAT" + e.getMessage());
        } catch (Exception e) {
            System.err.println("INTERNAL SERVER ERROR" + e.getMessage());
        }
    }

    public void importConsults() {
        try {
            System.out.println("Import consults started");
            consultService.importConsults();
            System.out.println("Import finished");
        } catch (IOException e) {
            System.err.println("IMPORT CONSULTS FAILED" + e.getMessage());
        }
    }

    public void deleteAllConsults() {
        System.out.println("Are you sure you want to delete ALL CONSULTS? ( It cannot be recovered ) " +
                "Press Y -> for YES or press N -> for NO");
        String userOption = scanner.nextLine();
        try {
            if (userOption.equalsIgnoreCase("y")) {
                if (consultService.getAllConsult().isEmpty()) {
                    System.out.println("The list of consults is empty");
                } else {
                    consultService.deleteAllConsults();
                    System.out.println("All consults deleted");
                }
            }
            if (userOption.equalsIgnoreCase("n")) {
                System.out.println("Ok ! Go back to menu !");
            }
        } catch (IllegalArgumentException e) {
            System.err.println("INVALID ANSWER" + e.getMessage());

        } catch (Exception e) {
            System.err.println("INTERNAL SERVER ERROR" + e.getMessage());
        }
    }
}