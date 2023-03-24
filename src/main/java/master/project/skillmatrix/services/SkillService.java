package master.project.skillmatrix.services;

import master.project.skillmatrix.DTO.SkillDTO;
import master.project.skillmatrix.mapping.GeneralMapping;
import master.project.skillmatrix.models.Skill;
import master.project.skillmatrix.repositories.SkillRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SkillService implements CrudService<SkillDTO, ObjectId>{

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private GeneralMapping<SkillDTO, Skill> generalMappingSkill;

    @Override
    public List<SkillDTO> findAll() {
        return skillRepository.findAll().stream()
                .map(skill -> generalMappingSkill.convertEntityToDTO(skill, SkillDTO.class))
                .toList();
    }

    @Override
    public SkillDTO findById(ObjectId id) {
        return generalMappingSkill.convertEntityToDTO(skillRepository.findById(id).orElseThrow(), SkillDTO.class);
    }

    @Override
    public SkillDTO save(SkillDTO object) {
        Optional.ofNullable(object).ifPresent(dto -> skillRepository.save(generalMappingSkill.convertDTOToEntity(dto, Skill.class)));
        return object;
    }

    @Override
    public void delete(SkillDTO object) {
        skillRepository.delete(generalMappingSkill.convertDTOToEntity(object, Skill.class));
    }

    @Override
    public void deleteById(ObjectId id) {
        skillRepository.deleteById(id);
    }
}
