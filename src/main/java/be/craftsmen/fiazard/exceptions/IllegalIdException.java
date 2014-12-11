package be.craftsmen.fiazard.exceptions;

import be.craftsmen.fiazard.common.error.AppErrorCode;

public class IllegalIdException extends FiazardException {

    public IllegalIdException(String id) {
        super(String.format("id invalid: %s", id));
    }

    @Override
    public AppErrorCode getErrorCode() {
        return AppErrorCode.ILLEGAL_ID;
    }
}