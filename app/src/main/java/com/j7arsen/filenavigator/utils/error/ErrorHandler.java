package com.j7arsen.filenavigator.utils.error;

import com.j7arsen.data.exception.FileCloseException;
import com.j7arsen.data.exception.FileReadException;
import com.j7arsen.data.exception.JsonParseException;
import com.j7arsen.filenavigator.R;
import com.j7arsen.filenavigator.utils.ResUtils;

public class ErrorHandler {

    private String defaultMessage;
    private ResUtils resUtils;

    public ErrorHandler(ResUtils resUtils) {
        this.resUtils = resUtils;
        this.defaultMessage = resUtils.getString(R.string.message_universal);
    }

    public ErrorData getError(Throwable throwable) {
        if (throwable instanceof FileCloseException) {
            return new ErrorData(ErrorData.ERROR_FILE_CLOSE, ((FileCloseException) throwable).getMessage());
        } else if (throwable instanceof FileReadException) {
            return new ErrorData(ErrorData.ERROR_FILE_CLOSE, ((FileReadException) throwable).getMessage());
        } else if (throwable instanceof JsonParseException) {
            return new ErrorData(ErrorData.ERROR_FILE_CLOSE, ((JsonParseException) throwable).getMessage());
        } else {
            return new ErrorData(ErrorData.ERROR_UNEXPECTED, defaultMessage);
        }
    }

}
