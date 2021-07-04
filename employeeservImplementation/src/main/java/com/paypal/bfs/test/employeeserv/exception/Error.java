package com.paypal.bfs.test.employeeserv.exception;

import lombok.Builder;

import javax.validation.constraints.AssertFalse;

/**
 * @author jmahajan
 */
@Builder
public class Error {

    protected String code;
    protected Error.ErrorTypeEnum type;
    protected String message;
    protected String fieldName;

    public enum ErrorTypeEnum {

        INVALID_REQUEST("INVALID_REQUEST"),
        SYSTEM_ERROR("SYSTEM_ERROR"),
        AUTHENTICATION_ERROR("AUTHENTICATION_ERROR"),
        AUTHORIZATION_ERROR("AUTHORIZATION_ERROR"),
        NOT_FOUND_ERROR("NOT_FOUND_ERROR"),
        NOT_ACCEPTABLE_ERROR("NOT_ACCEPTABLE_ERROR"),
        UNSUPPORTED_MEDIA_TYPE_ERROR("UNSUPPORTED_MEDIA_TYPE_ERROR"),
        VALIDATION_ERROR("VALIDATION_ERROR"),
        STALE_STATE_ERROR("STALE_STATE_ERROR");
        private final String value;

        private ErrorTypeEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }

        @Override
        public String toString() {
            return value();
        }

        public static Error.ErrorTypeEnum fromValue(String value) {
            for (Error.ErrorTypeEnum e : values()) {
                if (e.value().equals(value)) {
                    return e;
                }
            }
            throw new IllegalArgumentException(("Illegal value: " + value));
        }

    }

}
