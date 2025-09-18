package com.johnsonlovecode.BankingAppilication.exception;


public class InsufficientBalanceException extends RuntimeException{

    private String  message;

    public InsufficientBalanceException(String message){
        super(message);
    }
}
