package com.sda.mierloiubogdan.petclinic.service;

import java.io.IOException;
import java.sql.Date;

public interface PetService {

    void createPet(
            String race,
            Date date,
            boolean isVaccinated,
            String ownerName
    );

    void importPets() throws IOException;
}
