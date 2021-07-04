package com.paypal.bfs.test.employeeserv.repo;

import com.paypal.bfs.test.employeeserv.dao.AddressDetails;
import com.paypal.bfs.test.employeeserv.dao.EmployeeDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressDetailsRepository extends JpaRepository<AddressDetails, Integer> {
}
