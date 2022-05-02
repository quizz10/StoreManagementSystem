//package se.iths.storemanagementsystem.controller;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import se.iths.storemanagementsystem.entity.Employee;
//import se.iths.storemanagementsystem.service.EmployeeService;
//import se.iths.storemanagementsystem.utils.JsonFormatter;
//
//import javax.ws.rs.Path;
//import javax.ws.rs.PathParam;
//import javax.ws.rs.WebApplicationException;
//import javax.ws.rs.core.Response;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("employee")
//public class EmployeeController {
//    EmployeeService employeeService;
//
//    EmployeeController(EmployeeService employeeService) {
//        this.employeeService = employeeService;
//    }
//
//    @Path("")
//    @PostMapping
//    public ResponseEntity<Optional<Employee>> createEmployee(Employee employee) {
//        try {
//            employeeService.addEmployee(employee);
//        } catch (Exception e) {
//            throw new WebApplicationException(Response.status(Response.Status.CONFLICT)
//                    .entity(new JsonFormatter(Response.Status.CONFLICT.getStatusCode(), "Could not add new employee")).build());
//        }
//        return Response.ok(employee).build();
//    }
//
//    @GetMapping("{id}")
//    public ResponseEntity<Optional<Employee>> getEmployeeById(@PathParam("id") Long id) {
//        notFoundError(id);
//       return Response.ok(employeeService.findEmployeeById(id)).build();
//    }
//
//    @GetMapping("")
//    public ResponseEntity<Optional<Employee>> getAllEmployees() {
//        if (employeeService.getAllEmployees().isEmpty()) {
//            return Response.status(Response.Status.NO_CONTENT).entity(new JsonFormatter(Response.Status.NO_CONTENT.getStatusCode(), "There are no employees added yet")).build();
//        }
//        return Response.ok(employeeService.getAllEmployees()).build();
//    }
//
//
//    @PatchMapping("{id}")
//    public ResponseEntity<Optional<Employee>> updateEmployee(@PathParam("id")Long id, Employee employee) {
//        employeeService.updateEmployee(id, employee);
//        return Response.ok(employee).build();
//    }
//
//
//    @DeleteMapping("{id}")
//    public ResponseEntity<Optional<Employee>> deleteDepartment(@PathParam("id") Long id) {
//        notFoundError(id);
//        employeeService.deleteDepartment(id);
//        return Response.ok().entity(new JsonFormatter(200, "Successfully deleted employee with ID: " + id)).build();
//    }
//
//    private void notFoundError(Long id) {
//        if (!employeeService.findEmployeeById(id).isPresent()) {
//            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).entity(new JsonFormatter(404, "ID: " + id + " not found")).build());
//        }
//    }
//}
