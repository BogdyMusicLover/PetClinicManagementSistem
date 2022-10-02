package com.sda.mierloiubogdan.petclinic.service;

import java.sql.Date;

public interface PetService {

    void createPet(
            String race,
            Date date,
            boolean isVaccinated,
            String ownerName
    );
}
