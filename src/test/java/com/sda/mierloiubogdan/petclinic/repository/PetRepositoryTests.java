package com.sda.mierloiubogdan.petclinic.repository;

import org.junit.BeforeClass;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.sql.Date;
import java.time.LocalDate;

import static com.sda.mierloiubogdan.petclinic.utils.Utils.FORMATTER;

public class PetRepositoryTests {
    private final PetRepository petRepository = new PetRepositoryImpl();

    @BeforeClass

    @ParameterizedTest
    @CsvSource({"german shepherd, 25-11-2022, true, Bogdan",
                "chyuaua, 25-11-2022,false, Cristina"})
    public void createMultiplePets(String race, String date, String vaccinated, String ownerName) {
        Date birthdate = Date.valueOf(LocalDate.parse(date, FORMATTER));
        boolean isVaccinated = Boolean.parseBoolean(vaccinated);
        petRepository.createPet(race, birthdate, isVaccinated, ownerName);
    }
}
