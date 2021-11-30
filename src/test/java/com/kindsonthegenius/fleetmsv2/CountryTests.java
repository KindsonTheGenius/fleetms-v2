package com.kindsonthegenius.fleetmsv2;

import com.kindsonthegenius.fleetmsv2.parameters.models.Country;
import com.kindsonthegenius.fleetmsv2.parameters.repositories.CountryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CountryTests {

    @Autowired
    private CountryRepository repository;

    //FindByID
    @Test
    public void testFindById(){
        Country country = repository.findById(2).orElse(null);
        assertNotNull(country);
    }

    @Test
    public void testFindByIdEmpty(){
        Country country = repository.findById(100).orElse(null);
        assertNotNull(country);
    }


    @Test
    public void testFindByIdNull(){
        Country country = repository.findById(100).orElse(null);
        assertNull(country);
    }

    //FindByIdEmp


}
