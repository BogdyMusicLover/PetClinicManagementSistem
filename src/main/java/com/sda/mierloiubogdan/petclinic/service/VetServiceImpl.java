package com.sda.mierloiubogdan.petclinic.service;

import com.sda.mierloiubogdan.petclinic.model.Vet;
import com.sda.mierloiubogdan.petclinic.repository.VetRepository;

import java.util.List;
import java.util.Optional;

public class VetServiceImpl implements VetService {

    private final VetRepository vetRepository;

    public VetServiceImpl(VetRepository vetRepository) {
        this.vetRepository = vetRepository;
    }

    @Override
    public void createVet(String firstName, String lastName, String address, String speciality) {
        if (firstName == null || firstName.isBlank()) {
            throw new IllegalArgumentException("First name is INVALID");
        }
        if (lastName == null || lastName.isBlank()) {
            throw new IllegalArgumentException("Last name is INVALID");
        }
        if (address == null || address.isBlank()) {
            throw new IllegalArgumentException("First name is INVALID");
        }
        if (speciality == null || speciality.isBlank()) {
            throw new IllegalArgumentException("First name is INVALID");
        }
        vetRepository.createVet(firstName, lastName, address, speciality);

    }

    @Override
    public List<Vet> getAllVets() {
        List<Vet> allVets = vetRepository.getAllVets();
        return allVets;
    }

    @Override
    public Optional<Vet> findById(int id) {
        Optional<Vet> vet = vetRepository.findById(id);
        return vet;
    }

}
