package com.paypal.bfs.test.employeeserv.impl;

import com.paypal.bfs.test.employeeserv.api.EmployeeResource;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.dao.EmployeeDetails;
import com.paypal.bfs.test.employeeserv.exception.EmployeeError;
import com.paypal.bfs.test.employeeserv.exception.EmployeeVerify;
import com.paypal.bfs.test.employeeserv.repo.EmployeeDetailsRepository;
import com.paypal.bfs.test.employeeserv.util.MapperUtil;
import com.paypal.bfs.test.employeeserv.validation.EmployeeValidator;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation class for employee resource.
 */
@RequestMapping("/v1")
@RestController
@AllArgsConstructor
public class EmployeeResourceImpl implements EmployeeResource {

    private EmployeeDetailsRepository employeeDetailsRepository;
    private EmployeeValidator employeeValidator;


    @Override
    @PostMapping("/bfs/employees/{id}")
    public ResponseEntity<Employee> employeeGetById(@PathVariable String id) {
        final EmployeeDetails employeeDetails = employeeDetailsRepository.findById(Integer.valueOf(id)).orElse(null);
        EmployeeVerify.verify(ObjectUtils.isEmpty(employeeDetails), EmployeeError.EMPLOYEE_NOT_FOUND);
        Employee employee = MapperUtil.prepareEmployeeObject(employeeDetails);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @Override
    @PostMapping("/bfs/employees/createEmployee")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        employeeValidator.validate(employee);
        EmployeeDetails employeeDetails = employeeDetailsRepository.save(MapperUtil.prepareEmployeeDetailsObjectFromEmployeeEntity(employee));
        return new ResponseEntity<>(MapperUtil.prepareEmployeeObject(employeeDetails), HttpStatus.CREATED);
    }

    @Override
    @GetMapping("/bfs/employees/getEmployees")
    public ResponseEntity<List<Employee>> getEmployees() {
        List<EmployeeDetails> val = employeeDetailsRepository.findAll();
        List<Employee> employees = val.stream().map(MapperUtil::prepareEmployeeObject).collect(Collectors.toList());
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
}
