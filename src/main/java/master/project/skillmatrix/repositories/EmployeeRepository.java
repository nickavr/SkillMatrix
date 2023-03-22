package master.project.skillmatrix.repositories;

import master.project.skillmatrix.models.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmployeeRepository extends MongoRepository<Employee, String> {
}
