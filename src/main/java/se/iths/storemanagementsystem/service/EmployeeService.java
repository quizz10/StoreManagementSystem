//package se.iths.storemanagementsystem.service;
//
//import org.springframework.stereotype.Service;
//import se.iths.storemanagementsystem.entity.Employee;
//import se.iths.storemanagementsystem.repository.EmployeeRepository;
//
//import javax.persistence.EntityNotFoundException;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class EmployeeService {
//
//    private final EmployeeRepository employeeRepository;
//
//    public EmployeeService(EmployeeRepository employeeRepository) {
//        this.employeeRepository = employeeRepository;
//    }
//
//
//    public void addEmployee(Employee employee) {
//        employeeRepository.save(employee);
//    }
//
//    public Optional<Employee> findEmployeeById(Long id) {
//        return Optional.ofNullable(employeeRepository.findById(id).orElseThrow(EntityNotFoundException::new));
//    }
//
//    public List<Employee> getAllEmployees() {
//        return employeeRepository.createQuery("Select e from Employee e").getResultList();
//    }
//
//    public Employee updateEmployee(Long id, Employee employee) {
//        Employee updateEmp = getEmployeeById(id);
//
//        if(employee.getRole() != null) {
//            updateEmp.setRole(employee.getRole());
//        }
//
//        if(employee.getSalary() != 0) {
//            updateEmp.setSalary(employee.getSalary());
//        }
//
//        if (employee.getEmail() != null) {
//            updateEmp.setEmail(employee.getEmail());
//        }
//
//        if (employee.getFirstName() != null) {
//            updateEmp.setFirstName(employee.getFirstName());
//        }
//
//        if (employee.getFirstName() != null) {
//            updateEmp.setLastName(employee.getLastName());
//        }
//        return employee;
//    }
//
//    public void deleteDepartment(Long id) {
//        employeeRepository.remove(getEmployeeById(id));
//    }
//}
