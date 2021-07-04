package com.paypal.bfs.test.employeeserv.exception;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author jmahajan
 * <p>This class is responsible for evaluating input expression and
 * throwing @EmployeeException with the @EmployeeError if condition satisfies.
 * <p>These methods should be used instead of if else blocks for verifying conditions.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EmployeeVerify {

    /**
     * Replaces %s arguments in the input error message of employeeError and
     * replaces with the employeeErrorMessageArgs.
     *
     * @param expression    input expression evaluation flag
     * @param employeeError Employee error to be thrown
     * @throws EmployeeException if expression evaluates to true.
     */
    public static void verify(
            boolean expression, EmployeeError employeeError, Object... employeeErrorMessageArgs) {
        verifyNull(employeeError, null);
        if (expression) {
            throw new EmployeeException(employeeError, employeeErrorMessageArgs);
        }
    }

    /**
     * checks if given input reference is null and throws @EmployeeException with INVALID_INPUT
     * if input @EmployeeError is null else returns @EmployeeException with the given input
     * error.
     *
     * @param reference     input reference object
     * @param employeeError employee error to be thrown
     * @throws EmployeeException if reference is null
     */
    public static <T> void verifyNull(
            T reference, EmployeeError employeeError, Object... employeeErrorMessageArgs) {
        if (null == reference) {
            EmployeeError error = fetchEmployeeError(employeeError);
            throw new EmployeeException(error, employeeErrorMessageArgs);
        }
    }


    /**
     * @param employeeError Employee error to be thrown
     * @return {@link EmployeeError}
     */
    private static EmployeeError fetchEmployeeError(EmployeeError employeeError) {
        return null == employeeError ? EmployeeError.INVALID_EMPLOYEE_ERROR_INPUT : employeeError;
    }
}
