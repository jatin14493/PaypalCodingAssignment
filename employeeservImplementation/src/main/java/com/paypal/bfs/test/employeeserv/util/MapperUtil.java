package com.paypal.bfs.test.employeeserv.util;

import com.paypal.bfs.test.employeeserv.api.model.Address;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.dao.AddressDetails;
import com.paypal.bfs.test.employeeserv.dao.EmployeeDetails;
import lombok.experimental.UtilityClass;

import java.time.ZoneId;

/**
 * @author jmahajan
 */

@UtilityClass
public class MapperUtil {

    /**
     * Helper Method to covert DAO Entity to DTO Enttity
     *
     * @param employeeDetails
     * @return Employee Object to be returned.
     */
    public Employee prepareEmployeeObject(EmployeeDetails employeeDetails) {
        Employee employee = new Employee();
        employee.setFirstName(employeeDetails.getFirstName());
        employee.setLastName(employeeDetails.getLastName());
        employee.setAddress(prepareAddressObject(employeeDetails.getAddressDetails()));
        employee.setId(employeeDetails.getId());
        System.out.println("java.sql.Date.valueOf(employeeDetails.getDob())" + java.sql.Date.valueOf(employeeDetails.getDob()));
        employee.setDob(java.sql.Date.valueOf(employeeDetails.getDob()));
        return employee;
    }

    /**
     * Helper Method to covert DAO Entity to DTO Enttity
     *
     * @param addressDetails
     * @return Address Object to be returned.
     */
    public Address prepareAddressObject(AddressDetails addressDetails) {
        Address address = new Address();
        address.setCity(addressDetails.getCity());
        address.setState(addressDetails.getState());
        address.setCountry(addressDetails.getCountry());
        address.setId(addressDetails.getAddressId());
        address.setLine1(addressDetails.getLine1());
        address.setLine2(addressDetails.getLine2());
        address.setZipCode(addressDetails.getZipCode());
        return address;
    }

    /**
     * @param employee : Takes in an employee Object as input
     * @return : EmployeeDetails (DAO) object
     */
    public EmployeeDetails prepareEmployeeDetailsObjectFromEmployeeEntity(Employee employee) {
        return EmployeeDetails.builder().
                dob(employee.getDob().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()).
                firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .addressDetails(prepareAddressDetailsObjectFromAddressEntity(employee.getAddress()))
                .build();
    }


    public AddressDetails prepareAddressDetailsObjectFromAddressEntity(Address address) {

        return AddressDetails.builder().city(address.getCity()).
                country(address.getCountry()).
                zipCode(address.getZipCode()).
                line1(address.getLine1()).
                line2(address.getLine2()).
                state(address.getState()).
                build();
    }
}
