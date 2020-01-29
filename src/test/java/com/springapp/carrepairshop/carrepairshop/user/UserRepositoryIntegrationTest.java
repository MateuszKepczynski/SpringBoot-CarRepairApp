package com.springapp.carrepairshop.carrepairshop.user;

import com.springapp.carrepairshop.carrepairshop.dao.UserRepository;
import com.springapp.carrepairshop.carrepairshop.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryIntegrationTest
{
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void whenFindByLastName_thenReturnEmployee()
    {
        User employee = new User("MikeBike","testPass","Mike",
                        "Wazowski","Adress Test","123123123","Gdansk");

        entityManager.persist(employee);
        entityManager.flush();

        User result = userRepository.findUserByLastName(employee.getLastName()).get(0);
        assertThat(result.getLastName()).isEqualTo(employee.getLastName());
    }
}
