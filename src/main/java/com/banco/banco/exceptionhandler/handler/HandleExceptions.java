package com.banco.banco.exceptionhandler.handler;

import com.banco.banco.exceptionhandler.exceptions.InsufficientBalanceException;
import com.banco.banco.exceptionhandler.exceptions.NullUserExceptions;
import com.banco.banco.exceptionhandler.exceptions.ShopkeeperException;
import com.banco.banco.exceptionhandler.exceptionsdto.InsufficientBalanceDTO;
import com.banco.banco.exceptionhandler.exceptionsdto.NullUserDTO;
import com.banco.banco.exceptionhandler.exceptionsdto.ShopkeeperExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class HandleExceptions extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NullUserExceptions.class)
    public ResponseEntity nullUser(NullUserExceptions userExceptions){
        NullUserDTO userDTO = new NullUserDTO(
                userExceptions.getMessage(),
                userExceptions.getUserId()
        );
        return new ResponseEntity(userDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity RuntimeError(){
        return new ResponseEntity("error", HttpStatus.BAD_GATEWAY);
    }

    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity InsufficientBalanceHandler(InsufficientBalanceException balanceException){
        InsufficientBalanceDTO balanceDTO = new
                InsufficientBalanceDTO(
                        balanceException.getBalance(),
                        balanceException.getId(),
                        balanceException.getMessage(),
                        balanceException.getTransferValue()
                );

        return new ResponseEntity(balanceDTO, HttpStatus.BAD_GATEWAY);
    }

    @ExceptionHandler(ShopkeeperException.class)
    public ResponseEntity shopkeeperExceptionHandler(ShopkeeperException shopkeeperException){
        ShopkeeperExceptionDTO shopkeeperExceptionDTO = new
                ShopkeeperExceptionDTO(
                        shopkeeperException.getMessage(),
                        shopkeeperException.getId());

        return new ResponseEntity(shopkeeperExceptionDTO, HttpStatus.NOT_ACCEPTABLE);
    }
}
