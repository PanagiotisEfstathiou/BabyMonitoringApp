package com.edu.babymonitoringapp.service;

import com.edu.babymonitoringapp.dto.AccountDto;
import com.edu.babymonitoringapp.model.Account;
import com.edu.babymonitoringapp.model.Role;
import com.edu.babymonitoringapp.repositoy.AccountRepository;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AccountServiceImpl implements AccountService{
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account createAccount(AccountDto accountDto) {
        Account account = accountDto.toAccount();
        return accountRepository.save(account);
    }
    @Override
    public Optional<Account> getAccountById(Long id) {
        return accountRepository.findById(id);
    }
    @Override
    public List<AccountDto> getAllAccounts() {
       List<Account> accounts = accountRepository.findAll();
         return AccountDto.toListAccountDto(accounts);
    }
    @Override
    public Account updateAccount(Long id, AccountDto accountDto) {
        Optional<Account> account = accountRepository.findById(id);
        if (account.isPresent()) {
            Account account1 = account.get();
            account1.setUsername(accountDto.getUsername());
            account1.setPassword(accountDto.getPassword());
            account1.setRole(Role.valueOf(accountDto.getRole()));
            return accountRepository.save(account1);
        }
        else {
            return null;
        }
    }
    @Override
    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }

    @Override
    public boolean login(AccountDto accountDto) {
        Optional<Account> account = accountRepository.findByUsername(accountDto.getUsername());
        if (account.isPresent()) {
            Account account1 = account.get();
            if (account1.getPassword().equals(accountDto.getPassword())) {
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }
}
