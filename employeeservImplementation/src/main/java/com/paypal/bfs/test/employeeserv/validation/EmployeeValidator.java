package com.paypal.bfs.test.employeeserv.validation;

import com.paypal.bfs.test.employeeserv.api.model.Address;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.exception.EmployeeError;
import com.paypal.bfs.test.employeeserv.exception.EmployeeVerify;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
 * @author jmahajan
 * Custom Validator class for Employee Entity
 */

@Service
public class EmployeeValidator {

    public void validate(Employee employee) {

        checkIsnull(employee);
        validateFields(employee);
    }

    private void validateFields(Employee employee) {
        //checking first name
        int len = employee.getFirstName().length();
        boolean validName = (len >= 0 && len <= 255) && !isDigit(employee.getFirstName());
        EmployeeVerify.verify(!validName, EmployeeError.INVALID_EMPLOYEE_FIRST_NAME_RANGE);

        len = employee.getLastName().length();
        validName = (len >= 0 && len <= 255) && !isDigit(employee.getLastName());
        EmployeeVerify.verify(!validName, EmployeeError.INVALID_EMPLOYEE_LAST_NAME_RANGE);


        validateAddress(employee.getAddress());
    }


    private void validateAddress(Address address) {
        EmployeeVerify.verify(ObjectUtils.isEmpty(address.getLine1()), EmployeeError.INVALID_ADDRESS_LINE1);
        int len = address.getLine1().length();
        boolean validName = (len >= 0 && len <= 255);
        EmployeeVerify.verify(!validName, EmployeeError.INVALID_ADDRESS_LINE1);

        EmployeeVerify.verify(ObjectUtils.isEmpty(address.getZipCode()), EmployeeError.INVALID_ZIP_CODE);
        len = String.valueOf(address.getZipCode()).length();
        EmployeeVerify.verify(len != 6, EmployeeError.INVALID_ZIP_CODE);

        EmployeeVerify.verify(ObjectUtils.isEmpty(address.getCity()), EmployeeError.INVALID_CITY_NAME);
        len = address.getCity().length();
        validName = (len >= 1 && len <= 100);
        EmployeeVerify.verify(!validName, EmployeeError.INVALID_CITY_NAME);

        EmployeeVerify.verify(ObjectUtils.isEmpty(address.getState()), EmployeeError.INVALID_STATE_NAME);
        len = address.getState().length();
        validName = (len >= 1 && len <= 100);
        EmployeeVerify.verify(!validName, EmployeeError.INVALID_STATE_NAME);

        EmployeeVerify.verify(ObjectUtils.isEmpty(address.getCountry()), EmployeeError.INVALID_COUNTRY_NAME);
        len = address.getCountry().length();
        validName = (len >= 1 && len <= 100);
        EmployeeVerify.verify(!validName, EmployeeError.INVALID_COUNTRY_NAME);


    }

    private void checkIsnull(Employee employee) {
        // Testing null employee object
        EmployeeVerify.verify(ObjectUtils.isEmpty(employee), EmployeeError.INVALID_EMPLOYEE_ERROR_INPUT);

        // Testing First Name : Name is null
        EmployeeVerify.verify(ObjectUtils.isEmpty(employee.getFirstName()), EmployeeError.INVALID_EMPLOYEE_FIRST_NAME);

        // Testing Last Name : Name is null
        EmployeeVerify.verify(ObjectUtils.isEmpty(employee.getLastName()), EmployeeError.INVALID_EMPLOYEE_LAST_NAME);

        // Testing Date of Birth is null
        EmployeeVerify.verify(ObjectUtils.isEmpty(employee.getDob()), EmployeeError.INVALID_DATE_OF_BIRTH);

        // Testing Address is null
        EmployeeVerify.verify(ObjectUtils.isEmpty(employee.getDob()), EmployeeError.INVALID_ADDRESS);
    }


    /**
     * @param sample : Sample Input String
     * @return : True if there exists a digit in a string
     */
    private boolean isDigit(String sample) {
        char[] chars = sample.toCharArray();
        for (char c : chars) {
            if (Character.isDigit(c))
                return true;
        }
        return false;
    }

}
