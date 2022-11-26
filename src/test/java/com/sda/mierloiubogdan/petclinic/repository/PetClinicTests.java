package com.sda.mierloiubogdan.petclinic.repository;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.mockito.junit.jupiter.MockitoExtension;

@RunWith(Suite.class)
@Suite.SuiteClasses({VetRepositoryTests.class, ConsultRepositoryTest.class})
@ExtendWith(MockitoExtension.class)
public class PetClinicTests {
}
