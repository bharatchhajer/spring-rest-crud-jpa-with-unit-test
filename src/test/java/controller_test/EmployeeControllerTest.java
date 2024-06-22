package controller_test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import payroll.Employee;

import payroll.EmployeeController;
import payroll.EmployeeRepository;

import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@WebMvcTest( controllers= EmployeeController.class )
@Import(EmployeeController.class)
@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTest {

    @Configuration
    static class TestConfig {
        // Define test-specific beans here
    }
	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@MockBean
	EmployeeRepository repository;
	
	private Employee employee;
	
	@BeforeEach
	public void init() {
		employee = new Employee("Johnny","HR");
	}

	@Test
	public void EmployeeController_CreateEmployee_returnCreated() throws Exception {
		//mocking a repository behavior
		//given(repository.save(ArgumentMatchers.any())).willAnswer( invocation -> invocation.getArgument(0));
		when(repository.save(ArgumentMatchers.any())).thenReturn(employee);
		
		MockHttpServletRequestBuilder requestBuilder = post("/employees")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(employee));

		ResultActions response = mockMvc.perform( requestBuilder );
		
		//Asserting expected response
		response.andExpect(MockMvcResultMatchers.status().isOk());
	}
}
