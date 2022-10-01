package com.sda.mierloiubogdan.petclinic;

import com.sda.mierloiubogdan.petclinic.controller.VetController;
import com.sda.mierloiubogdan.petclinic.repository.VetRepositoryImpl;
import com.sda.mierloiubogdan.petclinic.service.VetServiceImpl;

public class Main {
    public static void main(String[] args) {
        VetController vetController = new VetController(new VetServiceImpl(new VetRepositoryImpl()));
        vetController.createVet();


    }
}