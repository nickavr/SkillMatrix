package master.project.skillmatrix.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Data
public class SkillWrapper {
    private Integer level;
    @DocumentReference
    private Skill skill;
}
