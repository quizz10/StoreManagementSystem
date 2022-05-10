package se.iths.storemanagementsystem.controller;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.iths.storemanagementsystem.dto.DepartmentDto;
import se.iths.storemanagementsystem.dto.UserDto;
import se.iths.storemanagementsystem.entity.DepartmentEntity;
import se.iths.storemanagementsystem.entity.UserEntity;
import se.iths.storemanagementsystem.service.DepartmentService;

import java.util.List;
import java.util.Optional;


@RestController
public class DepartmentController {

    @Autowired
    private ModelMapper modelMapper;

    DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping("admin/department")
    public ResponseEntity<DepartmentDto> createDepartment(@RequestBody Optional<DepartmentEntity> department) {
        DepartmentEntity addedDepartment = departmentService.addDepartment(department.get());

        return new ResponseEntity<>(modelMapper.map(addedDepartment, DepartmentDto.class), HttpStatus.CREATED);
    }

    @GetMapping("general/department/{id}")
    public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable("id") Long id) {
        Optional<DepartmentEntity> department = departmentService.findDepartmentById(id);
        return new ResponseEntity<>(modelMapper.map(department, DepartmentDto.class), HttpStatus.OK);
    }

    @GetMapping("general/department")
    public ResponseEntity<List<DepartmentDto>> getAllDepartments() {
        List<DepartmentDto> departments = departmentService.getAllDepartments()
                .stream().map(department -> modelMapper.map(department, DepartmentDto.class)).toList();

        if (departments == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }


    @PatchMapping("admin/department/{id}")
    public ResponseEntity<DepartmentDto> updateDepartmentName(@PathVariable("id") Long id, @RequestBody Optional<DepartmentEntity> department) {
        Optional<DepartmentEntity> updateDepartment = departmentService.updateDepartment(id, department.get().getDepartmentName());
        return new ResponseEntity<>(modelMapper.map(updateDepartment, DepartmentDto.class), HttpStatus.OK);
    }

    @PatchMapping("admin/department/linkemployee/{departmentid}/{userid}")
    public ResponseEntity<String> linkEmployeeToDepartment(@PathVariable("departmentid") Long departmentId, @PathVariable("userid") Long userId) {
        Optional<UserEntity> connectedEmployee = departmentService.linkEmployeeToDepartment(departmentId, userId);
        UserDto userDto = modelMapper.map(connectedEmployee, UserDto.class);
        return new ResponseEntity<>("linked employee with id " + userId + " to " + userDto.getDepartment(), HttpStatus.OK);
    }

    @PatchMapping("admin/department/unlinkemployee/{departmentid}/{userid}")
    public ResponseEntity<String> unlinkEmployeeFromDepartment(@PathVariable("departmentid") Long departmentId, @PathVariable("userid") Long userId) {
        Optional<UserEntity> connectedEmployee = departmentService.unlinkEmployeeToDepartment(departmentId, userId);
        UserDto userDto = modelMapper.map(connectedEmployee, UserDto.class);

        return new ResponseEntity<>("Unlinked employee with id " + userId + " from " + userDto.getDepartment(), HttpStatus.OK);
    }

    @PatchMapping("employee/department/linkitem/{departmentid}/{itemid}")
    public ResponseEntity linkItemToDepartment(@PathVariable("departmentid") Long departmentId, @PathVariable("itemid") Long itemId) {
        Optional<DepartmentEntity> department = departmentService.linkItemToDepartment(departmentId, itemId);
        DepartmentDto departmentDto = modelMapper.map(department, DepartmentDto.class);
        return new ResponseEntity<>(("Successfully linked item with id " + itemId + " to department " + departmentDto.getDepartmentName()), HttpStatus.OK);
    }

    @PatchMapping("employee/department/unlinkitem/{departmentid}/{itemid}")
    public ResponseEntity unLinkItemFromDepartment(@PathVariable("departmentid") Long departmentId, @PathVariable("itemid") Long itemId) {
        Optional<DepartmentEntity> department = departmentService.unLinkItemFromDepartment(departmentId, itemId);
        DepartmentDto departmentDto = modelMapper.map(department, DepartmentDto.class);
        return new ResponseEntity<>(("Successfully unlinked item with id " + itemId + " from department " + departmentDto.getDepartmentName()), HttpStatus.OK);
    }

    @DeleteMapping("admin/department/{id}")
    public ResponseEntity<Optional<Void>> deleteDepartment(@PathVariable("id") Long id) {
        departmentService.deleteDepartment(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
