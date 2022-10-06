package com.sda.mierloiubogdan.petclinic.repository;

import com.sda.mierloiubogdan.petclinic.model.Consult;
import com.sda.mierloiubogdan.petclinic.model.Pet;
import com.sda.mierloiubogdan.petclinic.model.Vet;

import java.sql.Date;

public interface ConsultRepository extends BaseRepository<Consult> {

    void createConsult(Vet vet, Pet pet, Date date, String description);

    void updateConsultById(int id, String description);


}
