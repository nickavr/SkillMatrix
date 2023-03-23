package master.project.skillmatrix.mapping;

import master.project.skillmatrix.DTO.GenericDTO;
import master.project.skillmatrix.models.BaseEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public record GeneralMapping<DTO extends GenericDTO, E extends BaseEntity>(@Autowired ModelMapper modelMapper) {
    public DTO convertEntityToDTO(E entity, Class<DTO> dtoClass) {
        return modelMapper.map(entity, dtoClass);
    }

    public E convertDTOToEntity(DTO dto, Class<E> entityClass) {
        return modelMapper.map(dto, entityClass);
    }

}
