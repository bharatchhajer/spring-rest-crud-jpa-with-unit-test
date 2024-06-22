package controller_test;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@SpringBootTest
class SpringRestCrudJpaApplicationTests {

    @Configuration
    static class TestConfig {
        // Define test-specific beans here
    }
	@Test
	void contextLoads() {
	}

}
