package master.project.skillmatrix.DTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class EmployeeDTO extends GenericDTO {
    private String name;
    private String surname;
    private String email;
    private String jobTitle;
    private Boolean isManager;
    private Double salary;
    private List<SkillWrapperDTO> skills;
}
