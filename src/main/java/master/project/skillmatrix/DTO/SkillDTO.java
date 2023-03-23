package master.project.skillmatrix.DTO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bson.types.ObjectId;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class SkillDTO extends GenericDTO{
    private String name;
    private List<ObjectId> practitioners;
}
