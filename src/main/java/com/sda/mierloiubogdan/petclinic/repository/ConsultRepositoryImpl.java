package com.sda.mierloiubogdan.petclinic.repository;

import com.sda.mierloiubogdan.petclinic.model.Consult;
import com.sda.mierloiubogdan.petclinic.model.Pet;
import com.sda.mierloiubogdan.petclinic.model.Vet;

import java.sql.Date;

public class ConsultRepositoryImpl extends BaseRepositoryImpl<Consult> implements ConsultRepository {


    public ConsultRepositoryImpl() {
        super(Consult.class);
    }

    @Override
    public void createConsult(Vet vet, Pet pet, Date date, String description) {
        Consult consult = new Consult();
        consult.setVet(vet);
        consult.setPet(pet);
        consult.setDate(date);
        consult.setDescription(description);

        create(consult);
    }
}
