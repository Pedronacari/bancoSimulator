package com.banco.banco.exceptionhandler.exceptions;

public class NullUserExceptions extends NullPointerException{
    long userId;

    public NullUserExceptions(long userId) {
        super("user not found");
        this.userId = userId;
    }

    public long getUserId() {
        return userId;
    }
}
