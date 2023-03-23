package master.project.skillmatrix.services;

import master.project.skillmatrix.DTO.GenericDTO;
import org.bson.types.ObjectId;

import java.util.List;

public interface CrudService<DTO extends GenericDTO, ID extends ObjectId>{
    List<DTO> findAll();

    DTO findById(ID id);

    DTO save(DTO object);

    void delete(DTO object);

    void deleteById(ID id);
}
