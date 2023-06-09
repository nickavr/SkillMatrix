package master.project.skillmatrix.repositories;

import master.project.skillmatrix.models.Skill;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SkillRepository extends MongoRepository<Skill, ObjectId> {
}
