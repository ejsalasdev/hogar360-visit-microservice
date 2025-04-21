package com.powerup.visitmicroservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {
        "jwt.secret=secret_key",
        "jwt.user=test_generator",
        "jwt.expiration=3600000"
})
class VisitMicroserviceApplicationTests {

    @Test
    void contextLoads() {
    }

}
