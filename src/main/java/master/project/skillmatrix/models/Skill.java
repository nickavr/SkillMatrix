package master.project.skillmatrix.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Document(collection = "Skills")
public class Skill extends BaseEntity{
    private String name;
    private List<ObjectId> practitioners;
}
