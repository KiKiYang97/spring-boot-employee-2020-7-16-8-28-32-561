package com.thoughtworks.springbootemployee.exception;

/**
 * @Author Dunka
 * @Description ExceptionMessage
 * @Date 23:22   2020/8/2
 * @ClassName ExceptionMessage
 */
public enum ExceptionMessage {
    NO_SUCH_DATA, ILLEGAL_OPERATION;
    private String message;

    ExceptionMessage() {

    }

    ExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
