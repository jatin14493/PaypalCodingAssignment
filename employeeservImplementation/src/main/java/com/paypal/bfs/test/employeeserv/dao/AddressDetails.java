package com.paypal.bfs.test.employeeserv.dao;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author jmahajan
 */

@Entity
@Table(name = "address")
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class AddressDetails {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    @Column(name = "address_id", unique = true, nullable = false)
    private Integer addressId;

    @Column(name = "line1", nullable = false)
    private String line1;

    @Column(name = "line2", nullable = true)
    private String line2;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "state", nullable = false)
    private String state;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "zip_code", nullable = false)
    private Integer zipCode;

//    @ManyToOne
//    @JoinColumn(foreignKey = @ForeignKey(name = "employee_id"))
//    private EmployeeDetails employeeDetails;


}
