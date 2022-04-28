//package se.iths.storemanagementsystem.controller;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import se.iths.storemanagementsystem.entity.Department;
//import se.iths.storemanagementsystem.entity.Employee;
//import se.iths.storemanagementsystem.service.EmployeeService;
//import se.iths.storemanagementsystem.utils.JsonFormatter;
//
//import javax.inject.Inject;
//import javax.ws.rs.*;
//import javax.ws.rs.core.MediaType;
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
//    @POST
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
//    @Path("{id}")
//    @GET
//    public ResponseEntity<Optional<Employee>> getEmployeeById(@PathParam("id") Long id) {
//        notFoundError(id);
//       return Response.ok(employeeService.findEmployeeById(id)).build();
//    }
//
//    @Path("")
//    @GET
//    public ResponseEntity<Optional<Employee>> getAllEmployees() {
//        if (employeeService.getAllEmployees().isEmpty()) {
//            return Response.status(Response.Status.NO_CONTENT).entity(new JsonFormatter(Response.Status.NO_CONTENT.getStatusCode(), "There are no employees added yet")).build();
//        }
//        return Response.ok(employeeService.getAllEmployees()).build();
//    }
//
//    @Path("{id}")
//    @PATCH
//    public ResponseEntity<Optional<Employee>> updateEmployee(@PathParam("id")Long id, Employee employee) {
//        employeeService.updateEmployee(id, employee);
//        return Response.ok(employee).build();
//    }
//
//    @Path("{id}")
//    @DELETE
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
