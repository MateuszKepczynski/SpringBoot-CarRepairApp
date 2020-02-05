package com.springapp.carrepairshop.carrepairshop.system;

import com.springapp.carrepairshop.carrepairshop.dao.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CarrepairshopIntegrationTests
{
    @Autowired
    private UserRepository userRepository;

    @Test
    void testFindAll()
    {
        userRepository.findAll();
        userRepository.findAll();
    }

}
