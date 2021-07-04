package com.paypal.bfs.test.employeeserv.api;

import com.paypal.bfs.test.employeeserv.api.model.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * Interface for employee resource operations.
 */
public interface EmployeeResource {

    /**
     * Retrieves the {@link Employee} resource by id.
     *
     * @param id employee id.
     * @return {@link Employee} resource.
     */
    ResponseEntity<Employee> employeeGetById(@PathVariable("id") String id);

    /**
     * Creates an employee resource into the system
     *
     * @param employee
     * @return
     */
    ResponseEntity<Employee> createEmployee(@RequestBody Employee employee);


    /**
     * @return List of Employees
     */
    ResponseEntity<List<Employee>> getEmployees();
}
