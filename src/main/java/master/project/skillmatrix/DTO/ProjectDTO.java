package master.project.skillmatrix.DTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProjectDTO extends GenericDTO{
    private List<EmployeeDTO> teamMembers;
    private Double budget;
    private Integer durationWeeks;
    private String client;
    private String name;
    private EmployeeDTO manager;
}
