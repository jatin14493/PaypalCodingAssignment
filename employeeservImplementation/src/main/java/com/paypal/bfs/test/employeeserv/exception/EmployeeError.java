package com.paypal.bfs.test.employeeserv.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jmahajan
 * Employee Error enum for custom error codes
 */
@Getter
public enum EmployeeError {

    OTHER_TEST("ETESTXXX", "Some Error Occurred due to %s", "Some unknown error has occurred", HttpStatus.INTERNAL_SERVER_ERROR),
    UNKNOWN_ERROR("E001", "Some unknown error occured", "some unknown error occured",
            HttpStatus.BAD_REQUEST),
    INTERNAL_EXCEPTION("E002", "Some internal exception Occurred",
            "Some internal exception has occurred", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_EMPLOYEE_ERROR_INPUT("E003", "Employee Error name cannot be null or empty",
            "Employee Error cannot be null or empty", HttpStatus.BAD_REQUEST),
    EMPLOYEE_NOT_FOUND("E004", "Employee Not Found", "Employee Not Found for id=%s", HttpStatus.INTERNAL_SERVER_ERROR),
    EMPLOYEE_ALREADY_EXISTS("E005", "Employee Already Exists", "Employee Already Exists Error", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_EMPLOYEE_FIRST_NAME("E006", "First Name can't be empty", "First Name can't be empty", HttpStatus.BAD_REQUEST),
    INVALID_EMPLOYEE_LAST_NAME("E007", "Last Name can't be empty", "Last Name can't be empty", HttpStatus.BAD_REQUEST),
    INVALID_EMPLOYEE_FIRST_NAME_RANGE("E008", "First Name should be between 1 to 255 Characters", "First Name should be between 1 to 255 Characters", HttpStatus.BAD_REQUEST),
    INVALID_EMPLOYEE_LAST_NAME_RANGE("E009", "Last Name should be between 1 to 255 Characters", "Last Name should be between 1 to 255 Characters", HttpStatus.BAD_REQUEST),
    INVALID_DATE_OF_BIRTH("E010", "Date of Birth is mandatory", "Date of Birth can't be Empty", HttpStatus.BAD_REQUEST),
    INVALID_ADDRESS("E011", "Address is mandatory", "Address can't be null", HttpStatus.BAD_REQUEST),
    INVALID_ADDRESS_LINE1("E012", "Line 1 is mandatory", "Line 1 is mandatory", HttpStatus.BAD_REQUEST),
    INVALID_ZIP_CODE("E013", "Zip Code is mandatory", "Zip Code is mandatory", HttpStatus.BAD_REQUEST),
    INVALID_CITY_NAME("E014", "City is mandatory", "City is mandatory", HttpStatus.BAD_REQUEST),
    INVALID_STATE_NAME("E015", "State is mandatory", "State is mandatory", HttpStatus.BAD_REQUEST),
    INVALID_COUNTRY_NAME("E016", "Country is mandatory", "Country is mandatory", HttpStatus.BAD_REQUEST);

    private static final Map<String, EmployeeError> employeeErrorMap = getEmployeeErrorMap();
    private final String errorCode;
    private final String errorDescription;
    private final HttpStatus status;
    @Setter
    private String errorMessage;

    EmployeeError(
            final String errorCode,
            final String errorMessage,
            final String errorDescription,
            final HttpStatus status) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.errorDescription = errorDescription;
        this.status = status;
    }

    private static Map<String, EmployeeError> getEmployeeErrorMap() {

        final Map<String, EmployeeError> possibleEmployeeErrorMap = new HashMap<>();
        for (final EmployeeError employeeError : values()) {
            possibleEmployeeErrorMap.put(employeeError.name(), employeeError);
        }
        return possibleEmployeeErrorMap;
    }

    public static EmployeeError value(final String type) {
        return employeeErrorMap.getOrDefault(type, EmployeeError.UNKNOWN_ERROR);
    }

}
