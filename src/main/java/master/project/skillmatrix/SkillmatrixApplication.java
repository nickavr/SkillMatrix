package master.project.skillmatrix;

import master.project.skillmatrix.DTO.EmployeeDTO;
import master.project.skillmatrix.DTO.ProjectDTO;
import master.project.skillmatrix.DTO.SkillDTO;
import master.project.skillmatrix.mapping.GeneralMapping;
import master.project.skillmatrix.models.Employee;
import master.project.skillmatrix.models.Project;
import master.project.skillmatrix.models.Skill;
import master.project.skillmatrix.repositories.EmployeeRepository;
import master.project.skillmatrix.repositories.ProjectRepository;
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
    //todo: cleanup

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private GeneralMapping<EmployeeDTO, Employee> generalMappingEmployee;

    @Autowired
    private GeneralMapping<SkillDTO, Skill> generalMappingSkill;

    @Autowired
    private GeneralMapping<ProjectDTO, Project> generalMappingProject;

    public static void main(String[] args) {
        SpringApplication.run(SkillmatrixApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(){
        return args -> {
            List<EmployeeDTO> employees = employeeService.findAll();
            EmployeeDTO employeeDTO = employees.get(0);

            List<Skill> skills = skillRepository.findAll();
            Skill skillEntity = skills.get(0);

            List<Project>projects = projectRepository.findAll();
            Project projectEntity = projects.get(0);

            Employee employeeEntity = generalMappingEmployee.convertDTOToEntity(employeeDTO, Employee.class);
            SkillDTO skillDTO = generalMappingSkill.convertEntityToDTO(skillEntity, SkillDTO.class);
            ProjectDTO projectDTO = generalMappingProject.convertEntityToDTO(projectEntity, ProjectDTO.class);

            System.out.println(employees);
        };
    }

}
