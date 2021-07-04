package com.paypal.bfs.test.employeeserv.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author jmahajan
 */

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeGenericResponse {
    @Builder.Default
    private ResponseStatus status = ResponseStatus.SUCCESS;

    private Object response;

    @JsonProperty("error")
    private EmployeeErrorDetails errorDetails;
}
