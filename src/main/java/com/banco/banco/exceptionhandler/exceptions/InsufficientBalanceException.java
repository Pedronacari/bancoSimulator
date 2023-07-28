package com.banco.banco.exceptionhandler.exceptions;

public class InsufficientBalanceException extends NullPointerException{
    Long balance;
    Long id;
    Long transferValue;

    public InsufficientBalanceException(Long balance,long transferValue, Long id) {
        super("insufficient balance");
        this.balance = balance;
        this.id = id;
        this.transferValue = transferValue;
    }

    public Long getTransferValue() {
        return transferValue;
    }

    public Long getBalance() {
        return balance;
    }

    public Long getId() {
        return id;
    }
}
