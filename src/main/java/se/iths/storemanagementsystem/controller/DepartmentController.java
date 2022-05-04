package se.iths.storemanagementsystem.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.iths.storemanagementsystem.entity.DepartmentEntity;
import se.iths.storemanagementsystem.entity.UserEntity;
import se.iths.storemanagementsystem.service.DepartmentService;
import se.iths.storemanagementsystem.utils.JsonFormatter;

import java.util.Optional;


@RestController
public class DepartmentController {
    DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping("admin/department")
    public ResponseEntity<Optional<DepartmentEntity>> createDepartment(@RequestBody Optional<DepartmentEntity> department) {
        departmentService.addDepartment(department.get());

        return new ResponseEntity<>(department, HttpStatus.CREATED);
    }

    @GetMapping("general/department/{id}")
    public ResponseEntity<Optional<DepartmentEntity>> getDepartmentById(@PathVariable("id") Long id) {
//        notFoundError(id);
        Optional<DepartmentEntity> department = departmentService.findDepartmentById(id);
        return new ResponseEntity<>(department, HttpStatus.OK);
    }

    @GetMapping("general/department")
    public ResponseEntity<Iterable<DepartmentEntity>> getAllDepartments() {
        Iterable<DepartmentEntity> departments = departmentService.getAllDepartments();
        if (departments == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }


    @PatchMapping("admin/department/{id}")
    public ResponseEntity<Optional<DepartmentEntity>> updateDepartmentName(@PathVariable("id") Long id, @RequestBody Optional<DepartmentEntity> department) {
        //  notFoundError(id);
        Optional<DepartmentEntity> updateDepartment = departmentService.updateDepartment(id, department.get().getDepartmentName());
        return new ResponseEntity<>(updateDepartment, HttpStatus.OK);
    }

    @DeleteMapping("admin/department/{id}")
    public ResponseEntity<Optional<DepartmentEntity>> deleteDepartment(@PathVariable("id") Long id) {
//        notFoundError(id);
        departmentService.deleteDepartment(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("admin/department/linkemployee/{departmentid}/{userid}")
    public ResponseEntity<Optional<UserEntity>> linkEmployeeToDepartment(@PathVariable("departmentid") Long departmentId, @PathVariable("userid") Long userId) {
        // notFoundError(id);
        Optional<UserEntity> connectedEmployee = departmentService.linkEmployeeToDepartment(departmentId, userId);

        return new ResponseEntity<>(connectedEmployee, HttpStatus.OK);
    }

    @PatchMapping("admin/department/unlinkemployee/{departmentid}/{userid}")
    public ResponseEntity<Optional<UserEntity>> unlinkEmployeeToDepartment(@PathVariable("departmentid") Long departmentId, @PathVariable("userid") Long userId) {
        // notFoundError(id);
        Optional<UserEntity> connectedEmployee = departmentService.unlinkEmployeeToDepartment(departmentId, userId);

        return new ResponseEntity<>(connectedEmployee, HttpStatus.OK);
    }

    //TODO: Bestämma sig för om vi ska returnera vår jsonformatter eller skicka tillbaka objektet.
    @PatchMapping("employee/department/linkitem/{departmentid}/{itemid}")
    public ResponseEntity linkItemToDepartment(@PathVariable("departmentid") Long departmentId, @PathVariable("itemid") Long itemId) {
        Optional<DepartmentEntity> department = departmentService.linkItemToDepartment(departmentId, itemId);

        return new ResponseEntity<>(new JsonFormatter(HttpStatus.OK.value(), "Successfully linked item with id " + itemId + " to department with id " + departmentId), HttpStatus.OK);
    }
    //TODO: Bestämma sig för om vi ska returnera vår jsonformatter eller skicka tillbaka objektet.
    @PatchMapping("employee/department/unlinkitem/{departmentid}/{itemid}")
    public ResponseEntity<Optional<DepartmentEntity>> unLinkItemFromDepartment(@PathVariable("departmentid") Long departmentId, @PathVariable("itemid") Long itemId) {
        Optional<DepartmentEntity> department = departmentService.unLinkItemFromDepartment(departmentId, itemId);

        return new ResponseEntity<>(department, HttpStatus.OK);
    }


//    private void notFoundError(Long id) {
//        if (!departmentService.findDepartmentById(id).isPresent()) {
//            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).entity(new JsonFormatter(404, "ID: " + id + " not found")).build());
//        }
//    }


}
