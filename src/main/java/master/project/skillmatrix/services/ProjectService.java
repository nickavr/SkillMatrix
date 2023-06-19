/**
 *  Copyright Murex S.A.S., 2003-2023. All Rights Reserved.
 * 
 *  This software program is proprietary and confidential to Murex S.A.S and its affiliates ("Murex") and, without limiting the generality of the foregoing reservation of rights, shall not be accessed, used, reproduced or distributed without the
 *  express prior written consent of Murex and subject to the applicable Murex licensing terms. Any modification or removal of this copyright notice is expressly prohibited.
 */
package master.project.skillmatrix.services;

import java.util.List;
import java.util.Optional;

import master.project.skillmatrix.DTO.ProjectDTO;
import master.project.skillmatrix.mapping.GeneralMapping;
import master.project.skillmatrix.models.Project;
import master.project.skillmatrix.repositories.EmployeeRepository;
import master.project.skillmatrix.repositories.ProjectRepository;

import org.bson.types.ObjectId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProjectService implements CrudService<ProjectDTO, ObjectId> {

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Instance fields 
    //~ ----------------------------------------------------------------------------------------------------------------

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private GeneralMapping<ProjectDTO, Project> generalMappingProject;

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Methods 
    //~ ----------------------------------------------------------------------------------------------------------------

    @Override
    public List<ProjectDTO> findAll() {
        return projectRepository.findAll().stream().map(project -> generalMappingProject.convertEntityToDTO(project, ProjectDTO.class)).toList();
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

    public ProjectDTO addTeamMemberForProject(String employeeEmail, String projectName) {
        return projectRepository.findFirstByName(projectName).map(project -> {
            employeeRepository.findFirstByEmail(employeeEmail).ifPresent(employee -> {
                project.getTeamMembers().add(employee);
                projectRepository.save(project);
            });
            return generalMappingProject.convertEntityToDTO(project, ProjectDTO.class);
        }).orElse(null);
    }

    public void deleteTeamMemberForProject(String employeeEmail, String projectName) {
        projectRepository.findFirstByName(projectName).ifPresent(project -> {
            employeeRepository.findFirstByEmail(employeeEmail).ifPresent(employee -> {
                project.getTeamMembers().remove(employee);
                projectRepository.save(project);
            });
        });
    }

}
