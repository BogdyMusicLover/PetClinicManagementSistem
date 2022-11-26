package com.sda.mierloiubogdan.petclinic.service;

import com.sda.mierloiubogdan.petclinic.model.Consult;
import com.sda.mierloiubogdan.petclinic.model.Pet;
import com.sda.mierloiubogdan.petclinic.model.Vet;
import com.sda.mierloiubogdan.petclinic.repository.ConsultRepository;
import com.sda.mierloiubogdan.petclinic.repository.PetRepository;
import com.sda.mierloiubogdan.petclinic.repository.VetRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.sda.mierloiubogdan.petclinic.utils.Utils.FORMATTER;

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
                throw new IllegalArgumentException(" INVALID PET ID");
            }
        } else {
            throw new IllegalArgumentException(" INVALID VET ID");
        }
    }

    @Override
    public List<Consult> getAllConsult() {
        return consultRepository.getAll();
    }

    @Override
    public void updateConsultById(int id, String description) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID IS INVALID");
        }
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("DESCRIPTION IS INVALID");
        }
        consultRepository.updateConsultById(id, description);
    }

    @Override
    public Optional<Consult> findConsultById(int id) {
        return consultRepository.findById(id);
    }

    @Override
    public void deleteById(int id) {
        if (id <= 0) {
            System.err.println("ID IS INVALID ");
        }
        consultRepository.deleteById(id);
    }

    @Override
    public void importConsults() {
        try {
            Path filePath = Paths.get("C:\\Users\\Andreea\\Documents\\GitHub\\PetClinicManagementSistem\\src\\main\\resources\\Data\\Consults.txt");
            Files.lines(filePath)
                    .skip(1)
                    .map(lines -> lines.split("\\|"))
                    .forEach(lineElements -> {
                        if (lineElements.length == 4) {
                            Date dateOfConsult = Date.valueOf(LocalDate.parse(lineElements[0], FORMATTER));
                            String description = lineElements[1];
                            int vetId = Integer.parseInt(lineElements[2]);
                            int petId = Integer.parseInt(lineElements[3]);
                            createConsult(vetId, petId, dateOfConsult, description);
                        }
                    });
        } catch (IOException e) {
            System.err.println("INVALID DATA FORMAT FOR IMPORT" + e.getMessage());
        }
    }

    @Override
    public void deleteAllConsults() {
        List<Consult> allConsults = consultRepository.getAll();
        if (!allConsults.isEmpty()) {
            consultRepository.deleteAll();
        } else {
            System.err.println("The list of consults is empty");
        }
    }

}