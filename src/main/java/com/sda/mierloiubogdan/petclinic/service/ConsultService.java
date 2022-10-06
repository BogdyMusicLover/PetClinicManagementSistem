package com.sda.mierloiubogdan.petclinic.service;

import com.sda.mierloiubogdan.petclinic.model.Consult;

import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface ConsultService {

    void createConsult(int vetId, int petId, Date date, String description);

    List<Consult> getAllConsult();

    void updateConsultById(int id, String description);

    Optional<Consult> findConsultById(int id);

    void deleteById(int id);

    void importConsults() throws IOException;

    void deleteAllConsults();
}
