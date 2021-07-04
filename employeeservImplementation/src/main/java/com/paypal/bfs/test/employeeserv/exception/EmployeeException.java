package com.paypal.bfs.test.employeeserv.exception;

import lombok.Getter;
import org.apache.commons.lang.ArrayUtils;

/***
 * @author jmahajan
 */

@Getter
public class EmployeeException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    @Getter
    protected transient Error error;
    private EmployeeError employeeError;


    public EmployeeException(EmployeeError workflowError, Object... workflowErrorMessageArgs) {

        super(formatErrorMessage(workflowError, workflowErrorMessageArgs));
        this.employeeError = workflowError;
        initError(workflowError.name());
    }

    /**
     * replaces the employee error message with the message arguments.Input @EmployeeError should not
     * be null.
     *
     * @param employeeError            employee error
     * @param employeeErrorMessageArgs employee error message arguments
     * @return EmployeeError with formatted error message if required.
     * @throws NullPointerException if employeeError is null.
     */
    private static String formatErrorMessage(
            EmployeeError employeeError, Object... employeeErrorMessageArgs) {

        if (ArrayUtils.isEmpty(employeeErrorMessageArgs)) {
            return employeeError.getErrorMessage();
        }
        return String.format(employeeError.getErrorMessage(), employeeErrorMessageArgs);
    }

    private void initError(String name) {

        this.error =
                Error.builder()
                        .code(this.employeeError.getErrorMessage())
                        .message(name)
                        .type(Error.ErrorTypeEnum.SYSTEM_ERROR).build();
    }
}

