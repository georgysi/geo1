package com.example.error;

/**
 * ErrorCode
 * Error Code for http response
 *
 * @author Binyamin (Dima) Pyanin
 * @version POC
 * @since April 24,2023
 */
public enum ErrorCode {

    GENERAL_ERROR("1999"),
    MISSING_PARAM_ERROR("7000"),
    UNSUPPORTED_MEDIA_TYPE_ERROR("7001"),
    VALIDATION_ERROR("7002"),
    MALFORMED_JSON_ERROR("7003"),
    WRITE_JSON_ERROR("7004"),
    NO_HANDLER_FOUND_ERROR("7005"),
    DB_ENTITY_NOT_FOUND_ERROR("7006"),
    GENERAL_DB_ERROR("7007"),
    METHOD_ARGS_TYPE_MISMATCH_ERROR("7008"),
    UNAUTHORIZED_ERROR("7009"),
    CSV_EXPORT_ERROR("7010");

    private String code;

    ErrorCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
