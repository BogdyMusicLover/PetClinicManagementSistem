package com.sda.mierloiubogdan.petclinic.repository;

import com.sda.mierloiubogdan.petclinic.model.Consult;
import com.sda.mierloiubogdan.petclinic.model.Pet;
import com.sda.mierloiubogdan.petclinic.model.Vet;
import com.sda.mierloiubogdan.petclinic.utils.SessionManager;
import org.hibernate.Session;
import org.hibernate.Transaction;

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

    @Override
    public void updateConsultById(int id, String description) {
        try (Session session = SessionManager.getSessionFactory().openSession()) {
            Consult consult = session.find(Consult.class, id);
            if (consult != null) {
                Transaction transaction = session.beginTransaction();
                try {
                    consult.setDescription(description);
                    session.saveOrUpdate(consult);
                    session.flush();
                    transaction.commit();
                } catch (Exception e) {
                    transaction.rollback();
                    throw new IllegalStateException(e);
                }
            } else {
                throw new IllegalArgumentException(
                        Consult.class + " ID NOT FOUND IN DATABASE"
                );
            }
        }
    }


}
