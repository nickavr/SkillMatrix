package master.project.skillmatrix.services;

import master.project.skillmatrix.DTO.ProjectDTO;
import master.project.skillmatrix.mapping.GeneralMapping;
import master.project.skillmatrix.models.Project;
import master.project.skillmatrix.repositories.ProjectRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService implements CrudService<ProjectDTO, ObjectId>{

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private GeneralMapping<ProjectDTO, Project> generalMappingProject;

    @Override
    public List<ProjectDTO> findAll() {
        return projectRepository.findAll().stream()
                .map(project -> generalMappingProject.convertEntityToDTO(project, ProjectDTO.class))
                .toList();
    }

    @Override
    public ProjectDTO findById(ObjectId id) {
        return generalMappingProject.convertEntityToDTO(projectRepository.findById(id).orElseThrow(), ProjectDTO.class);
    }

    @Override
    public ProjectDTO save(ProjectDTO object) {
        Optional.ofNullable(object).ifPresent(dto -> projectRepository.save(generalMappingProject.convertDTOToEntity(dto, Project.class)));
        return object;
    }

    @Override
    public void delete(ProjectDTO object) {
        projectRepository.delete(generalMappingProject.convertDTOToEntity(object, Project.class));
    }

    @Override
    public void deleteById(ObjectId id) {
        projectRepository.deleteById(id);
    }
}
