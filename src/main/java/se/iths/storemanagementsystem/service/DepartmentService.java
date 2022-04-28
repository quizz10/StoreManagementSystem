//package se.iths.storemanagementsystem.service;
//
//import org.springframework.stereotype.Service;
//import se.iths.storemanagementsystem.entity.Department;
//import se.iths.storemanagementsystem.entity.Employee;
//import se.iths.storemanagementsystem.repository.DepartmentRepository;
//
//import javax.persistence.EntityNotFoundException;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class DepartmentService {
//
//    private final DepartmentRepository departmentRepository;
//
//    public DepartmentService(DepartmentRepository departmentRepository) {
//        this.departmentRepository = departmentRepository;
//    }
//
//    public void addDepartment(Department department) {
//        departmentRepository.save(department);
//    }
//
//    public Optional<Department> findDepartmentById(Long id) {
//        return Optional.ofNullable(departmentRepository.findById(id).orElseThrow(EntityNotFoundException::new));
//    }
//
//    public List<Department> getAllDepartments() {
//        return departmentRepository.createQuery("SELECT d from Department d", Department.class).getResultList();
//    }
//
//    public Department updateDepartment(Long id, String departmentName) {
//        Department department = getDepartmentById(id);
//        department.setDepartmentName(departmentName);
//        return department;
//    }
//
//    public void deleteDepartment(Long id) {
//        departmentRepository.remove(.find(Department.class, id));
//    }
//
//    public Employee linkEmployeeToDepartment(Long id, String email) {
//
//        Department department = getDepartmentById(id);
//        Employee employee = (Employee) .createQuery("Select e from Employee e where e.email = :email")
//                .setParameter("email", email).getSingleResult();
//        department.addEmployee(employee);
//        return employee;
//    }
//
//    public Employee unlinkEmployeeToDepartment(Long id, String email) {
//        Department department = getDepartmentById(id);
//        Employee employee = (Employee) .createQuery("Select e from Employee e where e.email = :email")
//                .setParameter("email", email).getSingleResult();
//        department.removeEmployee(employee);
//        return employee;
//    }
//}