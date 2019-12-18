package com.j7arsen.filenavigator.utils.error;

public class ErrorData {

    public static final int ERROR_FILE_READ = 100001;
    public static final int ERROR_FILE_CLOSE = 100002;
    public static final int ERROR_JSON_PARSER = 100003;
    public static final int ERROR_UNEXPECTED = 100004;

    private int errorType;
    private String errorMessage;

    public ErrorData(int errorType, String errorMessage) {
        this.errorType = errorType;
        this.errorMessage = errorMessage;
    }

    public int getErrorType() {
        return errorType;
    }

    public void setErrorType(int errorType) {
        this.errorType = errorType;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
