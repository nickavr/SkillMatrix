package master.project.skillmatrix.services;

import master.project.skillmatrix.DTO.EmployeeDTO;
import master.project.skillmatrix.mapping.GeneralMapping;
import master.project.skillmatrix.models.Employee;
import master.project.skillmatrix.repositories.EmployeeRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService implements CrudService<EmployeeDTO, ObjectId>{
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private GeneralMapping<EmployeeDTO, Employee> generalMappingEmployee;

    @Override
    public List<EmployeeDTO> findAll() {
        return employeeRepository.findAll().stream()
                .map(employee -> generalMappingEmployee.convertEntityToDTO(employee, EmployeeDTO.class))
                .toList();
    }

    @Override
    public EmployeeDTO findById(ObjectId id) {
        return generalMappingEmployee.convertEntityToDTO(employeeRepository.findById(id).orElseThrow(), EmployeeDTO.class);
    }

    @Override
    public EmployeeDTO save(EmployeeDTO object) {
        Optional.ofNullable(object).ifPresent(dto -> employeeRepository.save(generalMappingEmployee.convertDTOToEntity(dto, Employee.class)));
        return object;
    }

    @Override
    public void delete(EmployeeDTO object) {
        employeeRepository.delete(generalMappingEmployee.convertDTOToEntity(object, Employee.class));
    }

    @Override
    public void deleteById(ObjectId id) {
        employeeRepository.deleteById(id);
    }
}
