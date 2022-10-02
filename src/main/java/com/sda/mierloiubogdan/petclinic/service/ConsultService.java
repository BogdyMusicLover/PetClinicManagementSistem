package com.sda.mierloiubogdan.petclinic.service;

import java.sql.Date;

public interface ConsultService {

    void createConsult(int vetId, int petId, Date date, String description);
}
