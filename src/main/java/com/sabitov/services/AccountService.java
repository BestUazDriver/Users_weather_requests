package com.sabitov.services;

import com.sabitov.dto.SimpleUserDto;
import com.sabitov.models.Account;
import com.sabitov.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public boolean saveAccount(Account account){
        if (isUnique(account.getEmail())) {
            accountRepository.save(account);
            return true;
        }
        return false;
    }

    public Optional<Account> findByEmail(String email){
        return accountRepository.findAccountByEmail(email);
    }

    private boolean isUnique(String email){
        return !accountRepository.findAccountByEmail(email).isPresent();
    }
    public Optional<Account> isSignUp(String email, String password){
        Optional<Account> accountOptional = accountRepository.findAccountByEmail(email);
        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            if (account.getPassword().equals(password)) {
                return Optional.of(account);
            }
        }
        return Optional.empty();
    }
    public List<Account> findAll(){
        return accountRepository.findAll();
    }
    public SimpleUserDto saveDto(Account account){
        accountRepository.save(account);
        return SimpleUserDto.reverseForm(account);
    }

}
