package com.johnsonlovecode.BankingAppilication.controller;

import com.johnsonlovecode.BankingAppilication.dto.AccountDto;
import com.johnsonlovecode.BankingAppilication.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/accounts")
public class AccountController {

    private AccountService accountService;


    //Add Account Reset Api
    @PostMapping
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto){
        AccountDto savedAccount = accountService.createAccount(accountDto);

        return new ResponseEntity<>(savedAccount, HttpStatus.CREATED);
    }


    // Get Account Rest API
    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable("id") Long accountId){

        AccountDto accountDto = accountService.getAccountBYId(accountId);
        return ResponseEntity.ok(accountDto);

    }

    // Deposit Balance Rest API
    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDto> depositAccount(@PathVariable  Long id,
                                                    @RequestBody Map<String, Double> request){
       AccountDto accountDto = accountService.deposit(id, request.get("amount"));
        return ResponseEntity.ok(accountDto);
    }

    //Withdraw Balance Rest API
    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDto> withdraw(@PathVariable Long id, @RequestBody Map<String, Double> request ){

        double amount = request.get("amount");
        AccountDto accountDto = accountService.withdraw(id, amount);
        return ResponseEntity.ok(accountDto);
    }

    //Get All Account Rest APi
    @GetMapping
    public ResponseEntity<List<AccountDto>> getAllAccount(){

        List<AccountDto> accounts = accountService.getAllAccount();
        return ResponseEntity.ok(accounts);
    }

    // Delete Account Rest Api
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id){
        accountService.deleteAccount(id);

        return ResponseEntity.ok("Account Deleted Successfully!!!");
    }
}
