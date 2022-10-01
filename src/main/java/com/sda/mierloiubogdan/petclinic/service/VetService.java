package com.sda.mierloiubogdan.petclinic.service;

import com.sda.mierloiubogdan.petclinic.model.Vet;

import java.util.List;

public interface VetService {
    void createVet(
            String firstName,
            String lastName,
            String address,
            String speciality
    );
    List<Vet> getAllVets();
}
