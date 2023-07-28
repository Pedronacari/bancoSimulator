package com.banco.banco.exceptionhandler.exceptionsdto;

public record InsufficientBalanceDTO(Long balance, long id, String message, Long transferValue) {
}
