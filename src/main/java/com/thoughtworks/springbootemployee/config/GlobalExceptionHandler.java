package com.thoughtworks.springbootemployee.config;

import com.thoughtworks.springbootemployee.exception.ExceptionMessage;
import com.thoughtworks.springbootemployee.exception.IllegalOperationException;
import com.thoughtworks.springbootemployee.exception.NoSuchDataException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @Author Dunka
 * @Description GlobalExceptionHandler
 * @Date 23:21   2020/8/2
 * @ClassName GlobalExceptionHandler
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NoSuchDataException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNoSuchDataException() {
        return ExceptionMessage.NO_SUCH_DATA.getMessage();
    }

    @ExceptionHandler(IllegalOperationException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String handleIllegalOperationException() {
        return ExceptionMessage.ILLEGAL_OPERATION.getMessage();
    }
}
