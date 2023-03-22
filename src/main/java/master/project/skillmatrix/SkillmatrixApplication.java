package master.project.skillmatrix;

import master.project.skillmatrix.models.Employee;
import master.project.skillmatrix.models.Skill;
import master.project.skillmatrix.models.SkillWrapper;
import master.project.skillmatrix.repositories.EmployeeRepository;
import master.project.skillmatrix.repositories.SkillRepository;
import master.project.skillmatrix.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class SkillmatrixApplication {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private SkillRepository skillRepository;

    public static void main(String[] args) {
        SpringApplication.run(SkillmatrixApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(){
        return args -> {
            List<Employee> employees = employeeService.findAll();
            List<Skill> skills = skillRepository.findAll();
            System.out.println(employees);
        };
    }

}
