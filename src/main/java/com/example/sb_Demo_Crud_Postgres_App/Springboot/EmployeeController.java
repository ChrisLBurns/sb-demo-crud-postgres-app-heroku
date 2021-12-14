package com.example.sb_Demo_Crud_Postgres_App.Springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/crud_demo")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    //get all employess
    @GetMapping("/getAllEmployees")
    public List<Employee> getAllEmployees(){
        return this.employeeRepository.findAll();
    }

    //get a single employee
    @GetMapping("/getEmployee/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long employeeId)
        throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() ->
                new ResourceNotFoundException(employeeId));
        return ResponseEntity.ok().body(employee);
    }

    //add an employee
    @PostMapping("/addEmployee")
    public Employee createEmployee(@RequestBody Employee employee){
        return this.employeeRepository.save(employee);
    }

    //update an employee
//    @PutMapping("/updateEmployee/{id}")
//    public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long employeeId,
//        @Valid @RequestBody Employee employeeDetails) throws ResourceNotFoundException {
//        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() ->
//                new ResourceNotFoundException(employeeId));
//
//        employee.setEmail(employeeDetails.getEmail());
//        employee.setFirstname(employeeDetails.getFirstname());
//        employee.setLastname(employeeDetails.getLastname());
//
//        return ResponseEntity.ok(this.employeeRepository.save(employee));
//    }

    //delete an employee
    @DeleteMapping("/deleteEmployee/{id}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId) throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() ->
                new ResourceNotFoundException(employeeId));

        this.employeeRepository.delete(employee);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }
}
