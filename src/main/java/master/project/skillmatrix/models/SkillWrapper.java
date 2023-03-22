package master.project.skillmatrix.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Data
public class SkillWrapper {
    Integer level;
    @DocumentReference
    Skill skill;
}
