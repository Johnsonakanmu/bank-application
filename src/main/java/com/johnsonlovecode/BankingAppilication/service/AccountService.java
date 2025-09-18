package com.johnsonlovecode.BankingAppilication.service;

import com.johnsonlovecode.BankingAppilication.dto.AccountDto;

import java.util.List;

public interface AccountService {

    public AccountDto createAccount(AccountDto accountDto);

    public AccountDto getAccountBYId(Long id);

    public AccountDto deposit(Long id, double amount);

    public AccountDto withdraw(Long id, double amount);

    List<AccountDto> getAllAccount();

    void deleteAccount(Long id);
}
