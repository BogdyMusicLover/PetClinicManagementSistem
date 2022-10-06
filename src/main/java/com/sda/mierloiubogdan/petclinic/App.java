package com.sda.mierloiubogdan.petclinic;

import com.sda.mierloiubogdan.petclinic.controller.ConsultController;
import com.sda.mierloiubogdan.petclinic.controller.PetController;
import com.sda.mierloiubogdan.petclinic.controller.VetController;
import com.sda.mierloiubogdan.petclinic.repository.ConsultRepositoryImpl;
import com.sda.mierloiubogdan.petclinic.repository.PetRepositoryImpl;
import com.sda.mierloiubogdan.petclinic.repository.VetRepositoryImpl;
import com.sda.mierloiubogdan.petclinic.service.ConsultServiceImpl;
import com.sda.mierloiubogdan.petclinic.service.PetServiceImpl;
import com.sda.mierloiubogdan.petclinic.service.VetServiceImpl;
import com.sda.mierloiubogdan.petclinic.utils.SessionManager;
import com.sda.mierloiubogdan.petclinic.utils.UserOption;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        SessionManager.getSessionFactory();
        VetController vetController = new VetController(new VetServiceImpl(new VetRepositoryImpl()));
        PetController petController = new PetController(new PetServiceImpl(new PetRepositoryImpl()));
        ConsultController consultController = new ConsultController(new ConsultServiceImpl(
                new VetRepositoryImpl(),
                new PetRepositoryImpl(),
                new ConsultRepositoryImpl()
        )
        );

        Scanner scanner = new Scanner(System.in);
        UserOption userOption;

        do {
            try {
                UserOption.displayAllOptions();
                System.out.println("Please select an option: ");
                int numericOption = Integer.parseInt(scanner.nextLine().trim());
                userOption = UserOption.findByNumericOption(numericOption);
            } catch (NumberFormatException e) {
                userOption = UserOption.UNKNOWN;
            }
            switch (userOption) {
                case ADD_VET -> vetController.createVet();
                case VIEW_ALL_VETS -> vetController.showAllVets();
                case VIEW_VET_BY_ID -> vetController.showVetById();
                case UPDATE_VET_BY_ID -> vetController.updateVetById();
                case DELETE_VET_BY_ID -> vetController.deleteVetById();
                case ADD_PET -> petController.createPet();
                case VIEW_ALL_PETS -> petController.showAllPets();
                case VIEW_PET_BY_ID -> petController.showPetById();
                case UPDATE_PET_BY_ID -> petController.updatePetById();
                case DELETE_PET_BY_ID -> petController.deletePetById();
                case IMPORT_VETS -> vetController.importVets();
                case IMPORT_PETS -> petController.importPets();
                case CREATE_CONSULT -> consultController.createConsult();
                case VIEW_ALL_CONSULTS -> consultController.showAllConsults();
                case UPDATE_CONSULT_BY_ID -> consultController.updateConsultByID();
                case SHOW_CONSULT_BY_ID -> consultController.showConsultById();
                case DELETE_CONSULT_BY_ID -> consultController.deleteById();
                case IMPORT_CONSULTS -> consultController.importConsults();
                case DELETE_ALL_CONSULTS -> consultController.deleteAllConsults();
                case UNKNOWN -> System.err.println("Invalid option selected");
                case EXIT -> {
                    System.out.println("Good bye");
                    System.out.println();
                }
            }
        } while (userOption != UserOption.EXIT);
        SessionManager.shutdown();
    }
}