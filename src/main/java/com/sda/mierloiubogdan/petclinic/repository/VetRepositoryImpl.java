package com.sda.mierloiubogdan.petclinic.repository;

import com.sda.mierloiubogdan.petclinic.model.Vet;
import com.sda.mierloiubogdan.petclinic.utils.SessionManager;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class VetRepositoryImpl implements VetRepository {

    @Override
    public void createVet(String firstName, String lastName, String address, String speciality) {
        Transaction transaction = null;
        try (Session session = SessionManager.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Vet vet = new Vet();
            vet.setFirstName(firstName);
            vet.setLastName(lastName);
            vet.setAddress(address);
            vet.setSpeciality(speciality);

            session.save(vet);
            transaction.commit();

        } catch (Exception e) {
            System.err.println(e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public List<Vet> getAllVets() {
        try (Session session = SessionManager.getSessionFactory().openSession()) {
            List<Vet> allVets = session.createQuery("FROM Vet", Vet.class).getResultList();
            return allVets;
        }
    }

    @Override
    public Optional<Vet> findById(int id) {
        try (Session session = SessionManager.getSessionFactory().openSession()) {
            Vet vet = session.find(Vet.class, id);
            return Optional.ofNullable(vet);
        }
    }

    @Override
    public void updateVetById(int id, String firstName, String lastName, String address, String speciality) {
        try (Session session = SessionManager.getSessionFactory().openSession()) {

            Vet vet = session.find(Vet.class, id);
            if (vet != null) {
                Transaction transaction = session.beginTransaction();
                try {
                    vet.setFirstName(firstName);
                    vet.setLastName(lastName);
                    vet.setAddress(address);
                    vet.setSpeciality(speciality);

                    session.saveOrUpdate(vet);
                    transaction.commit();
                } catch (Exception e) {
                    transaction.rollback();
                    throw new IllegalStateException(e);
                }
            } else {
                throw new IllegalArgumentException("VET ID NOT FOUND IN DATABASE");
            }
        }
    }


}
