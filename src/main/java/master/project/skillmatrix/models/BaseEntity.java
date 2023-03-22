package master.project.skillmatrix.models;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@Data
public class BaseEntity implements Serializable {
    @Id
    private ObjectId id;
}
