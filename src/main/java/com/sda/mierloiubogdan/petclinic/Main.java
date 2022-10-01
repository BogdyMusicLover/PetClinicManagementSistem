package com.sda.mierloiubogdan.petclinic;

import com.sda.mierloiubogdan.petclinic.controller.VetController;
import com.sda.mierloiubogdan.petclinic.repository.VetRepositoryImpl;
import com.sda.mierloiubogdan.petclinic.service.VetServiceImpl;
import com.sda.mierloiubogdan.petclinic.utils.SessionManager;
import com.sda.mierloiubogdan.petclinic.utils.UserOption;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SessionManager.getSessionFactory();
        VetController vetController = new VetController(new VetServiceImpl(new VetRepositoryImpl()));

        Scanner scanner = new Scanner(System.in);
        UserOption userOption;
        do {
            UserOption.displayAllOptions();
            System.out.println("Please select an option: ");
            int numbericOption = scanner.nextInt();
            userOption = UserOption.findByNumericOption(numbericOption);
            switch (userOption) {
                case ADD_VET:
                    vetController.createVet();
                    break;
                case VIEW_ALL_VETS:
                    vetController.showAllVets();
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