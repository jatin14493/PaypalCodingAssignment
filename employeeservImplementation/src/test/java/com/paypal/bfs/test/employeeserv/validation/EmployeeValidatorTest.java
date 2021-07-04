package com.paypal.bfs.test.employeeserv.validation;

import com.paypal.bfs.test.employeeserv.api.model.Address;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.exception.EmployeeError;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @author jmahajan
 * <p>
 * //TODO : UTs for all validations can be added subsequently.
 */

@RunWith(SpringRunner.class)
public class EmployeeValidatorTest {

    @InjectMocks
    private EmployeeValidator employeeValidator;

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();


    @Test
    public void testNullEmployee() {
        expectedException.expectMessage(EmployeeError.INVALID_EMPLOYEE_ERROR_INPUT.getErrorMessage());
        employeeValidator.validate(null);
    }

    @Test
    public void invalidFirstName() {
        Employee employee = new Employee();
        employee.setFirstName(null);
        employee.setLastName("Set");
        employee.setAddress(new Address());
        employee.setDob(new Date());
        expectedException.expectMessage(EmployeeError.INVALID_EMPLOYEE_FIRST_NAME.getErrorDescription());
        employeeValidator.validate(employee);

    }

    @Test
    public void invalidLastName() {
        Employee employee = new Employee();
        employee.setFirstName("Set");
        employee.setLastName(null);
        employee.setAddress(new Address());
        employee.setDob(new Date());
        expectedException.expectMessage(EmployeeError.INVALID_EMPLOYEE_LAST_NAME.getErrorDescription());
        employeeValidator.validate(employee);

    }

    @Test
    public void invalidFirstNameRangeInvalidCharacter() {
        Employee employee = new Employee();
        employee.setFirstName("1");
        employee.setLastName("Set");
        employee.setAddress(new Address());
        employee.setDob(new Date());
        expectedException.expectMessage(EmployeeError.INVALID_EMPLOYEE_FIRST_NAME_RANGE.getErrorMessage());
        employeeValidator.validate(employee);
    }

    @Test
    public void invalidLastNameRangeInvalidCharacter() {
        Employee employee = new Employee();
        employee.setFirstName("Set");
        employee.setLastName("1");
        employee.setAddress(new Address());
        employee.setDob(new Date());
        expectedException.expectMessage(EmployeeError.INVALID_EMPLOYEE_LAST_NAME_RANGE.getErrorMessage());
        employeeValidator.validate(employee);
    }
}
