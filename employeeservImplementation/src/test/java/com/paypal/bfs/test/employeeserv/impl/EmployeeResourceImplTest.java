package com.paypal.bfs.test.employeeserv.impl;

import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.dao.AddressDetails;
import com.paypal.bfs.test.employeeserv.dao.EmployeeDetails;
import com.paypal.bfs.test.employeeserv.exception.EmployeeError;
import com.paypal.bfs.test.employeeserv.repo.EmployeeDetailsRepository;
import com.paypal.bfs.test.employeeserv.validation.EmployeeValidator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Optional;

/**
 * @author jmahajan
 */

@RunWith(SpringRunner.class)
public class EmployeeResourceImplTest {

    @InjectMocks
    private EmployeeResourceImpl employeeResource;

    @Mock
    private EmployeeDetailsRepository employeeDetailsRepository;

    @Mock
    EmployeeValidator employeeValidator;

    @Test
    public void employeeSuccessfullyFound() {
        EmployeeDetails employeeDetails = prepareMockEmployeeObject();
        Mockito.when(employeeDetailsRepository.findById(1)).thenReturn(Optional.of(employeeDetails));
        ResponseEntity<Employee> val = employeeResource.employeeGetById("1");
        Assert.assertEquals("Jatin", val.getBody().getFirstName());
    }

    @Test
    public void employeeNotFoundException() {
        Mockito.when(employeeDetailsRepository.findById(1)).thenReturn(Optional.ofNullable(null));
        try {
            employeeResource.employeeGetById("1");
        } catch (Exception e) {
            Assert.assertEquals(EmployeeError.EMPLOYEE_NOT_FOUND.getErrorMessage(), e.getMessage());
        }
    }

    private EmployeeDetails prepareMockEmployeeObject() {
        EmployeeDetails employee = new EmployeeDetails();
        employee.setFirstName("Jatin");
        employee.setLastName("Mahajan");
        employee.setId(1);
        employee.setDob(LocalDate.now());

        employee.setAddressDetails(AddressDetails.builder().
                line1("4th Floor, B-4/7 Krishna Nagar").
                city("Delhi").
                state("Delhi").
                zipCode(110051).
                country("India").
                build());
        return employee;
    }
}
