package master.project.skillmatrix.controllers;

import master.project.skillmatrix.DTO.EmployeeDTO;
import master.project.skillmatrix.services.EmployeeService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable ObjectId id){
        return Optional.ofNullable(employeeService.findById(id))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO employeeDTO) throws URISyntaxException{
        employeeDTO.setId(new ObjectId(new Date()));
        EmployeeDTO createdEmployeeDTO = employeeService.save(employeeDTO);
        return Optional.ofNullable(createdEmployeeDTO)
                .map(value -> {
                    URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                            .path("/{id}")
                            .buildAndExpand(value.getId())
                            .toUri();
                    return ResponseEntity.created(uri)
                            .body(value);
                })
                .orElse(ResponseEntity.notFound().build());
    }

}
