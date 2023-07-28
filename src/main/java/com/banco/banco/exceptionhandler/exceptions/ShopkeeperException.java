package com.banco.banco.exceptionhandler.exceptions;

public class ShopkeeperException extends RuntimeException{
    Long id;

    public ShopkeeperException(Long id) {
        super("The payer is a shopkeeper and cannot perform transfers");
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
