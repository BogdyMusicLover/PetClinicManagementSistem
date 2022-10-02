package com.sda.mierloiubogdan.petclinic;

import com.sda.mierloiubogdan.petclinic.controller.PetController;
import com.sda.mierloiubogdan.petclinic.controller.VetController;
import com.sda.mierloiubogdan.petclinic.repository.PetRepositoryImpl;
import com.sda.mierloiubogdan.petclinic.repository.VetRepositoryImpl;
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
                case ADD_VET:
                    vetController.createVet();
                    break;
                case VIEW_ALL_VETS:
                    vetController.showAllVets();
                    break;
                case VIEW_VET_BY_ID:
                    vetController.showVetById();
                    break;
                case UPDATE_VET_BY_ID:
                    vetController.updateVetById();
                    break;
                case DELETE_VET_BY_ID:
                    vetController.deleteVetById();
                    break;
                case ADD_PET:
                    petController.createPet();
                    break;
                case VIEW_ALL_PETS:
                    System.out.println("Soon");
                    break;
                case VIEW_PET_BY_ID:
                    System.out.println("Soon");
                    break;
                case UPDATE_PET_BY_ID:
                    System.out.println("Soon");
                    break;
                case DELETE_PET_BY_ID:
                    System.out.println("Soon");
                    break;
                case UNKNOWN:
                    System.err.println("Invalid option selected");
                    break;
                case EXIT:
                    System.out.println("Good bye");
                    break;

            }
        } while (userOption != UserOption.EXIT);
        SessionManager.shutdown();
    }
}