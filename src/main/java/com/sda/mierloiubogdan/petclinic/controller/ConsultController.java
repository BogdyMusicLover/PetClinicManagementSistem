package com.sda.mierloiubogdan.petclinic.controller;

import com.sda.mierloiubogdan.petclinic.model.Consult;
import com.sda.mierloiubogdan.petclinic.service.ConsultService;

import java.sql.Date;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
        System.out.println("This is all consults");
        for (Consult consult : consultService.getAllConsult()) {
            System.out.println(consult.getId()
                    + ", VET_ID: " + consult.getVet().getId()
                    + ", PET_ID: " + consult.getPet().getId()
                    + ", DATE: " + consult.getDate()
                    + ", DESCRIPTION: " + consult.getDescription());
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
}