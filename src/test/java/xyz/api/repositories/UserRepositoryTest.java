package xyz.api.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class UserRepositoryTest {
    
    @Autowired
    private UserRepository repository;

    @Test
    @DisplayName("Find admin username by email")
    void testFindByEmail() {

        // given/arrange
        String email = "admin@xyz.io";

        // when/act
        var entity = this.repository.findByEmail(email);

        // then/assert
        assertEquals(email, entity.getEmail());
    }
}