package master.project.skillmatrix.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Document(collection = "Projects")
public class Project extends BaseEntity {
    @DocumentReference
    private List<Employee> teamMembers;
    private Double budget;
    private Integer durationWeeks;
    private String client;
    private String name;
    @DocumentReference
    private Employee manager;

}
