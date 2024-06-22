package payroll;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
	
	private final EmployeeRepository repository;
	
	public EmployeeController(EmployeeRepository repository) {
		this.repository = repository;
	}

	@GetMapping("/employees")
	public List<Employee> all(){
		return repository.findAll();
	}
	
	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee newEmployee) {
		return repository.save(newEmployee);
	}
	
	@GetMapping("/employees/{id}")
	public Employee getEmployee(@PathVariable Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new EmployeeNotFoundException() );
	}
	
	@PutMapping("/employees/{id}")
	public Employee putEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
		return repository.findById(id)
				.map( employee -> {
					employee.setName(newEmployee.getName());
					employee.setRole(newEmployee.getRole());
					return repository.save(employee);
				})
				.orElseGet( ()->{
					newEmployee.setId(id);
					return repository.save(newEmployee);
				});
		
	}
	
	@DeleteMapping("/employees/{id}")
	public void deleteEmployee(@PathVariable Long id) {
		repository.deleteById(id);
	}
}
