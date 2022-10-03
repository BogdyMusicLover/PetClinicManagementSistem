package com.sda.mierloiubogdan.petclinic.service;

import com.sda.mierloiubogdan.petclinic.model.Consult;
import com.sda.mierloiubogdan.petclinic.model.Pet;
import com.sda.mierloiubogdan.petclinic.model.Vet;
import com.sda.mierloiubogdan.petclinic.repository.ConsultRepository;
import com.sda.mierloiubogdan.petclinic.repository.PetRepository;
import com.sda.mierloiubogdan.petclinic.repository.VetRepository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public class ConsultServiceImpl implements ConsultService {

    private final VetRepository vetRepository;
    private final PetRepository petRepository;
    private final ConsultRepository consultRepository;

    public ConsultServiceImpl(VetRepository vetRepository, PetRepository petRepository, ConsultRepository consultRepository) {
        this.vetRepository = vetRepository;
        this.petRepository = petRepository;
        this.consultRepository = consultRepository;
    }

    @Override
    public void createConsult(int vetId, int petId, Date date, String description) {
        Optional<Vet> vetOptional = vetRepository.findById(vetId);
        if (vetOptional.isPresent()) {
            Optional<Pet> petOptional = petRepository.findById(petId);
            if (petOptional.isPresent()) {
                consultRepository.createConsult(vetOptional.get(), petOptional.get(), date, description);
            } else {
                throw new IllegalArgumentException("INVALID PET ID");
            }
        } else {
            throw new IllegalArgumentException("INVALID VET ID");
        }
    }

    @Override
    public List<Consult> getAllConsult() {
        List<Consult> consults = consultRepository.getAll();
        return consults;
    }

    @Override
    public void updateConsultById(int id, String description) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID IS INVALID");
        }
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("DESCRIPTION IS INVAID");
        }
        consultRepository.updateConsultById(id, description);
    }
}