package com.paypal.bfs.test.employeeserv.exception;

import com.paypal.bfs.test.employeeserv.response.EmployeeErrorDetails;
import com.paypal.bfs.test.employeeserv.response.EmployeeGenericResponse;
import com.paypal.bfs.test.employeeserv.response.ResponseStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Objects;
import java.util.Optional;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles all type of Exceptions
     *
     * @param exception
     * @param webRequest
     * @return
     */

    @ExceptionHandler
    public ResponseEntity<Object> handleAll(Exception exception, WebRequest webRequest) {
        log.error("Exception Stack Trace=", exception);

        HttpHeaders headers = new HttpHeaders();
        EmployeeGenericResponse response =
                EmployeeGenericResponse.builder()
                        .status(ResponseStatus.FAILURE)
                        .errorDetails(
                                EmployeeErrorDetails.builder().errorMessage(exception.getMessage()).build())
                        .build();
        return new ResponseEntity<>(
                response, headers, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(Exception exception, WebRequest webRequest) {
        HttpHeaders headers = new HttpHeaders();
        EmployeeGenericResponse response =
                EmployeeGenericResponse.builder()
                        .status(ResponseStatus.FAILURE)
                        .errorDetails(
                                EmployeeErrorDetails.builder().errorMessage(exception.getMessage()).build())
                        .build();
        return new ResponseEntity<>(
                response, headers, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handles idempotent challenges
     *
     * @param exception
     * @param webRequest
     * @return
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDuplicateCreation(Exception exception, WebRequest webRequest) {
        boolean isUnique = exception.getMessage().contains("UNIQUE_EMPLOYEE_INDEX");
        HttpHeaders headers = new HttpHeaders();
        EmployeeGenericResponse response =
                EmployeeGenericResponse.builder()
                        .status(ResponseStatus.FAILURE)
                        .errorDetails(isUnique ?
                                EmployeeErrorDetails.builder().errorMessage(EmployeeError.EMPLOYEE_ALREADY_EXISTS.getErrorMessage()).build() : EmployeeErrorDetails.builder().errorMessage(exception.getMessage()).build())
                        .build();
        return new ResponseEntity<>(
                response, headers, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EmployeeException.class)
    public ResponseEntity<Object> handleEmployeeGeneralException(
            Exception exception, WebRequest webRequest) {

        if (exception instanceof EmployeeException) {
            EmployeeException ex = ((EmployeeException) exception);
            Optional<EmployeeError> employeeErrorOptional =
                    Optional.ofNullable(ex.getEmployeeError()).filter(Objects::nonNull);

        }

        log.error("Exception Stack Trace=", exception);

        HttpHeaders headers = new HttpHeaders();

        EmployeeError employeeError = fetchEmployeeError(exception);
        EmployeeErrorDetails errorDetails = populateErrorDetails(employeeError);
        EmployeeGenericResponse response =
                EmployeeGenericResponse.builder().build().builder()
                        .status(ResponseStatus.FAILURE)
                        .errorDetails(errorDetails)
                        .build();
        return new ResponseEntity<>(response, headers, employeeError.getStatus());
    }

    private EmployeeError fetchEmployeeError(Exception exception) {
        if (exception instanceof EmployeeException) {
            EmployeeException employeeException = (EmployeeException) exception;
            return employeeException.getEmployeeError();
        }
        return EmployeeError.INTERNAL_EXCEPTION;
    }

    private EmployeeErrorDetails populateErrorDetails(EmployeeError employeeError) {
        return EmployeeErrorDetails.builder()
                .errorCode(employeeError.getErrorCode())
                .errorMessage(employeeError.getErrorMessage())
                .errorDescription(employeeError.getErrorDescription())
                .build();
    }
}
