package com.johnsonlovecode.BankingAppilication.service;

import com.johnsonlovecode.BankingAppilication.dto.AccountDto;
import com.johnsonlovecode.BankingAppilication.entity.Account;
import com.johnsonlovecode.BankingAppilication.exception.AccountException;
import com.johnsonlovecode.BankingAppilication.exception.InsufficientBalanceException;
import com.johnsonlovecode.BankingAppilication.mapper.AccountMapper;
import com.johnsonlovecode.BankingAppilication.respository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService{

    private AccountRepository accountRepository;


    @Override
    public AccountDto createAccount(AccountDto accountDto) {

        Account account = AccountMapper.mapToAccount(accountDto);
        Account savedAccount = accountRepository.save(account);

        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccountBYId(Long id) {

       Account account = accountRepository.findById(id).orElseThrow(
                () -> new AccountException("Account does not exist: " + id)
        );

        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto deposit(Long id, double amount) {
        Account account = accountRepository.findById(id).orElseThrow(
                () -> new AccountException("Account does not exist: " + id)
        );

        double total = account.getBalance() + amount;
        account.setBalance(total);
        Account saveAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(saveAccount);
    }

    @Override
    public AccountDto withdraw(Long id, double amount) {

        Account account = accountRepository.findById(id).orElseThrow(
                () -> new AccountException("Account does not exist: " + id)
        );

        if (account.getBalance() < amount){
            throw new InsufficientBalanceException("Insufficient balance");
        }

        double total = account.getBalance() - amount;
        account.setBalance(total);
        Account saveAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(saveAccount);
    }

    @Override
    public List<AccountDto> getAllAccount() {

        List<Account> account = accountRepository.findAll();
       return account.stream().map(
                 AccountMapper::mapToAccountDto)
                .collect(Collectors.toList());

    }

    @Override
    public void deleteAccount(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(
                () -> new AccountException("Account does not exist: " + id)
        );

        accountRepository.deleteById(id);
    }


}
