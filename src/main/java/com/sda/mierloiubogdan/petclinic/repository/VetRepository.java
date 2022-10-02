package com.sda.mierloiubogdan.petclinic.repository;

import com.sda.mierloiubogdan.petclinic.model.Vet;

public interface VetRepository extends BaseRepository<Vet> {
    void createVet(
            String firstName,
            String lastName,
            String address,
            String speciality
    );

    void updateVetById(
            int id,
            String firstName,
            String lastName,
            String address,
            String speciality
    );
}
