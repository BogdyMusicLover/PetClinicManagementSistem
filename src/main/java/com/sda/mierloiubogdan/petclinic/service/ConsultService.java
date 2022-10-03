package com.sda.mierloiubogdan.petclinic.service;

import com.sda.mierloiubogdan.petclinic.model.Consult;

import java.sql.Date;
import java.util.List;

public interface ConsultService {

    void createConsult(int vetId, int petId, Date date, String description);

    List<Consult> getAllConsult();

    void updateConsultById(int id, String description);
}
