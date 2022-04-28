//package se.iths.storemanagementsystem.controller;
//
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import se.iths.storemanagementsystem.entity.Customer;
//import se.iths.storemanagementsystem.entity.Department;
//import se.iths.storemanagementsystem.entity.Employee;
//import se.iths.storemanagementsystem.service.DepartmentService;
//import se.iths.storemanagementsystem.utils.JsonFormatter;
//
//import javax.inject.Inject;
//import javax.ws.rs.*;
//import javax.ws.rs.core.Response;
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("department")
//public class DepartmentController {
//    DepartmentService departmentService;
//
//    public DepartmentController(DepartmentService departmentService) {
//        this.departmentService = departmentService;
//    }
//
//    @Path("")
//    @POST
//    public ResponseEntity<Optional<Department>> createDepartment(Department department) {
//        try {
//            departmentService.addDepartment(department);
//        } catch (Exception e) {
//            throw new WebApplicationException(Response.status(Response.Status.CONFLICT).entity(new JsonFormatter
//                    (Response.Status.CONFLICT.getStatusCode(), "Could not add new department")).build());
//        }
//        return Response.ok(department).build();
//    }
//
//    @Path("{id}")
//    @GET
//    public ResponseEntity<Optional<Department>> getDepartmentById(@PathParam("id") Long id) {
//        notFoundError(id);
//        Optional<Department> department = departmentService.findDepartmentById(id);
//        return Response.ok(department).build();
//    }
//
//    @Path("")
//    @GET
//    public ResponseEntity<Optional<Department>> getAllDepartments() {
//        List<Department> departments = departmentService.getAllDepartments();
//        if(departments.isEmpty()) {
//            return Response.status(Response.Status.NO_CONTENT).entity(new JsonFormatter(204, "There are no departments added yet.")).build();
//        }
//        return Response.ok(departments).build();
//    }
//
//    @Path("{id}")
//    @PATCH
//    public ResponseEntity<Optional<Department>> updateDepartmentName(@PathParam("id") Long id, Department department ) {
//        notFoundError(id);
//        Department updateDepartment = departmentService.updateDepartment(id, department.getDepartmentName());
//        return Response.ok(updateDepartment).build();
//    }
//
//    @Path("{id}")
//    @DELETE
//    public ResponseEntity<Optional<Department>> deleteDepartment(@PathParam("id") Long id) {
//        notFoundError(id);
//        departmentService.deleteDepartment(id);
//        return Response.ok().entity(new JsonFormatter(200, "Successfully deleted department with ID: " + id)).build();
//    }
//
//    @Path("linkemployee/{id}")
//    @PATCH
//    public ResponseEntity<Optional<Department>> linkEmployeeToDepartment(@PathParam("id")Long id, @QueryParam("email")String email) {
//        notFoundError(id);
//        Employee connectedEmployee = departmentService.linkEmployeeToDepartment(id, email);
//        if(connectedEmployee.getEmail() == null) {
//            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).entity(new JsonFormatter
//                    (Response.Status.NOT_FOUND.getStatusCode(), "Could not find an employee with the email: " + email)).build());
//        }
//        return Response.ok(connectedEmployee).build();
//    }
//
//    @Path("unlinkemployee/{id}")
//    @PATCH
//    public ResponseEntity<Optional<Department>> unlinkEmployeeToDepartment(@PathParam("id")Long id, @QueryParam("email")String email) {
//        notFoundError(id);
//        Employee connectedEmployee = departmentService.unlinkEmployeeToDepartment(id, email);
//        if(connectedEmployee.getEmail() == null) {
//            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).entity(new JsonFormatter
//                    (Response.Status.NOT_FOUND.getStatusCode(), "Could not find an employee with the email: " + email)).build());
//        }
//        return Response.ok(connectedEmployee).build();
//    }
//
//
//    private void notFoundError(Long id) {
//        if (!departmentService.findDepartmentById(id).isPresent()) {
//            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).entity(new JsonFormatter(404, "ID: " + id + " not found")).build());
//        }
//    }
//}
