package master.project.skillmatrix.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
@Document(collection = "Employees")
public class Employee extends BaseEntity{
    private String name;
    private String surname;
    private String email;
    private String jobTitle;
    private Boolean isManager;
    private List<SkillWrapper> skills;


}
