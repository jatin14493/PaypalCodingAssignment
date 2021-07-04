package com.paypal.bfs.test.employeeserv.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author jmahajan
 */

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeErrorDetails {
    private Date timestamp;
    private String errorCode;
    private String errorMessage;
    private String errorDescription;
}
