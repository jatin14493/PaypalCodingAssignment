//package com.paypal.bfs.test.employeeserv;
//
//import com.paypal.bfs.test.employeeserv.dao.AddressDetails;
//import com.paypal.bfs.test.employeeserv.dao.EmployeeDetails;
//import com.paypal.bfs.test.employeeserv.exception.EmployeeError;
//import com.paypal.bfs.test.employeeserv.impl.EmployeeResourceImpl;
//import com.paypal.bfs.test.employeeserv.repo.AddressDetailsRepository;
//import com.paypal.bfs.test.employeeserv.repo.EmployeeDetailsRepository;
//import entity.Employee1;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.boot.autoconfigure.batch.BatchAutoConfiguration;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.Optional;
//
///**
// * @author jmahajan
// */
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(
//        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@ActiveProfiles("test")
//@EnableAutoConfiguration(exclude = BatchAutoConfiguration.class)
//public class EmployeeResourceImplTest {
//
//    @InjectMocks
//    private EmployeeResourceImpl employeeResource;
//
//    @Mock
//    private EmployeeDetailsRepository employeeDetailsRepository;
//
//    @Mock
//    private AddressDetailsRepository addressDetailsRepository;
//
//
//    @Test
//    public void employeeSuccessfullyFound() {
//        EmployeeDetails employeeDetails = prepareMockEmployeeObject();
//        Mockito.when(employeeDetailsRepository.findById("1")).thenReturn(Optional.of(employeeDetails));
//        ResponseEntity<Employee1> val = employeeResource.employeeGetById("1");
//        Assert.assertEquals("Jatin", val.getBody().getFirstName());
//    }
//
//    @Test
//    public void employeeNotFoundException() {
//        Mockito.when(employeeDetailsRepository.findById("1")).thenReturn(Optional.ofNullable(null));
//        try {
//            employeeResource.employeeGetById("1");
//        } catch (Exception e) {
//            Assert.assertEquals(EmployeeError.EMPLOYEE_NOT_FOUND.getErrorMessage(), e.getMessage());
//        }
//    }
//
//    private EmployeeDetails prepareMockEmployeeObject() {
//        EmployeeDetails employee = new EmployeeDetails();
//        employee.setFirstName("Jatin");
//        employee.setId("1");
//        employee.setAddressDetails(AddressDetails.builder().build());
//        return employee;
//    }
//}
