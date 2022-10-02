package com.sda.mierloiubogdan.petclinic.repository;

import com.sda.mierloiubogdan.petclinic.model.Vet;

public class VetRepositoryImpl extends BaseRepositoryImpl<Vet> implements VetRepository {

    public VetRepositoryImpl() {
        super(Vet.class);
    }

    @Override
    public void createVet(String firstName, String lastName, String address, String speciality) {
        Vet vet = new Vet();
        vet.setFirstName(firstName);
        vet.setLastName(lastName);
        vet.setAddress(address);
        vet.setSpeciality(speciality);
        create(vet);
    }

    @Override
    public void updateVetById(int id, String firstName, String lastName, String address, String speciality) {
        Vet vet = new Vet();
        vet.setId(id);
        vet.setFirstName(firstName);
        vet.setLastName(lastName);
        vet.setAddress(address);
        vet.setSpeciality(speciality);

        updateById(id, vet);
    }
}
