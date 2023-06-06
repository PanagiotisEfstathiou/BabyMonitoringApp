package com.edu.babymonitoringapp.service;

import com.edu.babymonitoringapp.dto.AccountDto;
import com.edu.babymonitoringapp.model.Account;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public interface AccountService {
    Account createAccount(AccountDto accountDto);
    Optional<Account> getAccountById(Long id);
    List<AccountDto> getAllAccounts();
    Account updateAccount(Long id,AccountDto accountDto);
    void deleteAccount(Long id);

    boolean login(AccountDto accountDto);

}
