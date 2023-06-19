/**
 *  Copyright Murex S.A.S., 2003-2023. All Rights Reserved.
 * 
 *  This software program is proprietary and confidential to Murex S.A.S and its affiliates ("Murex") and, without limiting the generality of the foregoing reservation of rights, shall not be accessed, used, reproduced or distributed without the
 *  express prior written consent of Murex and subject to the applicable Murex licensing terms. Any modification or removal of this copyright notice is expressly prohibited.
 */
package master.project.skillmatrix.controllers;

import java.util.List;
import java.util.Optional;

import master.project.skillmatrix.DTO.ProjectDTO;
import master.project.skillmatrix.services.EmployeeService;
import master.project.skillmatrix.services.ProjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//J-

@RestController
@RequestMapping("/projects")
public class ProjectController {

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Instance fields
    //~ ----------------------------------------------------------------------------------------------------------------

    @Autowired
    private ProjectService projectService;

    @Autowired
    private EmployeeService employeeService;

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Methods
    //~ ----------------------------------------------------------------------------------------------------------------

    @GetMapping
    public ResponseEntity<List<ProjectDTO>> getAllProjects() {
        return Optional.ofNullable(projectService.findAll()).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/post")
    public ResponseEntity<ProjectDTO> addEmployeeToProject(@RequestHeader("employeeEmail") String employeeEmail,
                                                           @RequestHeader("projectName") String projectName) {
        return Optional.ofNullable(projectService.addTeamMemberForProject(employeeEmail, projectName))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/deleteEmployee")
    public ResponseEntity<Object> removeEmployeeFromProject(@RequestHeader("employeeEmail") String employeeEmail,
                                          @RequestHeader("projectName") String projectName) {
        projectService.deleteTeamMemberForProject(employeeEmail, projectName);
        return ResponseEntity.noContent().build();
    }
}
//J+
